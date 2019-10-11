package org.tony.transaction.tcc.core;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.util.SerializationUtils;

/**
 * 组合事务
 * 
 * @author tonyruiyu
 *
 */
public final class CompositeTransaction {

    private Composite          composite;

    private int                reTryCnt = 3;

    private int                sort     = 1;
    private boolean            isSave   = false;

    private List<AtomContext>  atoms    = new ArrayList<AtomContext>();

    private PersistenceManager persistenceManager;

    PersistenceManager getPersistenceManager() {
        return persistenceManager;
    }

    void setPersistenceManager(PersistenceManager persistenceManager) {
        this.persistenceManager = persistenceManager;
    }

    public Composite getComposite() {
        return composite;
    }

    // 注册原子事务
    public void regeditAtom(AtomTransaction atomTransaction, Object[] args) {
        Atom atom = createAtom(args, atomTransaction.getAtomCode(), atomTransaction.getClass().getName());
        AtomContext atomContext = new AtomContext();
        atomContext.setAtomTransaction(atomTransaction);
        atomContext.setComposite(composite);
        atomContext.setArgs(args);
        atomContext.setAtom(atom);
        atoms.add(atomContext);
    }

    public void regeditAtom(Object target, Object[] args) {
        if (target instanceof AtomTransaction) {
            regeditAtom((AtomTransaction) target, args);
            return;
        }

        DefaultAtomTransaction dat = new DefaultAtomTransaction(target);

        Atom atom = createAtom(args, dat.getAtomCode(), target.getClass().getName());
        AtomContext atomContext = new AtomContext();
        atomContext.setAtomTransaction(dat);
        atomContext.setComposite(composite);
        atomContext.setArgs(args);
        atomContext.setAtom(atom);
        atoms.add(atomContext);
    }

    // 创建事务
    private Atom createAtom(Object[] args, String code, String iocCode) {
        Atom atom = new Atom();
        atom.setArgs(args);
        atom.setArgsContent(SerializationUtils.serialize(args));
        atom.setAtomId(UUID.randomUUID().toString());
        atom.setCode(code);
        atom.setIocId(iocCode);
        atom.setCompositeId(composite.getId());
        atom.setCreateDate(new Date());
        atom.setCurrentRetryCnt(0);
        atom.setCurrentStatus(AtomStatus.CRT.name());
        atom.setCurrentTime(atom.getCreateDate());
        atom.setLastStatus(AtomStatus.CRT.name());
        atom.setAtomSort(sort++);
        persistenceManager.insertAtom(atom);
        return atom;
    }

    public void submit() throws Exception {
        try {
            if (atoms == null || atoms.isEmpty()) {
                nextCompositeStatus(composite, CompositeStatus.FINISH);
                throw new TccTransactionException("没有需要执行的原子事务");
            }
            UndoException tune = null;
            
            try {
                if (CompositeStatus.CRT.equals(getCurrCompsoteStatus())) {
                    // CRT --> TRY
                    nextCompositeStatus(composite, getCurrCompsoteStatus().success());
                }
                if (getCurrCompsoteStatus().isExecTry()) {
                    execTry();
                    nextCompositeStatus(composite, getCurrCompsoteStatus().success());
                }

            } catch (UndoException e) {
                // 立即回滚
                nextCompositeStatus(composite, getCurrCompsoteStatus().error());
                tune = e;
            } catch (Exception e) {
                nextCompositeStatus(composite, getCurrCompsoteStatus().unknow());
                throw e;
            }

            if (isUndo(getCurrCompsoteStatus())) {
                try {
                    rollback();
                    nextCompositeStatus(composite, getCurrCompsoteStatus().success());
                } catch (TccManualTransactionException e1) {
                    nextCompositeStatus(composite, CompositeStatus.MANUAL);
                    throw e1;
                } catch (Exception e1) {
                    nextCompositeStatus(composite, getCurrCompsoteStatus().error());
                    throw e1;
                }

                if (tune != null) {
                    throw tune;
                }

                return;
            }

            if (getCurrCompsoteStatus().isCfm()) {
                try {
                    comfirm();
                    nextCompositeStatus(composite, getCurrCompsoteStatus().success());
                } catch (TccManualTransactionException e) {
                    nextCompositeStatus(composite, CompositeStatus.MANUAL);
                    throw e;
                } catch (Exception e) {
                    nextCompositeStatus(composite, getCurrCompsoteStatus().error());
                    throw e;
                }
            }
        } finally {
            TransactionContext.clean();
            if (!isSave) {
                persistenceManager.nextCompositeStatus(composite.getId(), composite.getCurrentStatus(), composite.getLastStatus());
                for (int i = 0; i < atoms.size(); i++) {
                    Atom a = atoms.get(i).getAtom();
                    persistenceManager.nextAtomStatus(a.getAtomId(), a.getCurrentStatus(), a.getLastStatus());
                }
            }
            persistenceManager.releaseCompositeByStatus(composite.getId());
        }

    }

    private boolean isUndo(CompositeStatus currCompsoteStatus) {
        return CompositeStatus.UNDO.equals(currCompsoteStatus);
    }

    private CompositeStatus getCurrCompsoteStatus() {
        return CompositeStatus.valueOf(composite.getCurrentStatus());
    }

    private boolean rollback() {
        boolean flag = false;

        for (int i = 0; i < atoms.size(); i++) {
            AtomContext atomContext = atoms.get(i);
            Atom atom = atomContext.getAtom();
            AtomTransaction atomTransaction = atomContext.getAtomTransaction();
            if (checkRollback(atom)) {
                if (AtomStatus.CFM.equals(getCurrAtomStatus(atom))) {
                    nextAtomStatus(atom, AtomStatus.UNDO);
                }
                flag = true;
                try {
                    atomTransaction.cancel(atomContext.getArgs());
                    nextAtomStatus(atom, getCurrAtomStatus(atom).success());
                    // 记录成功日志
                } catch (Throwable e) {
                    if (atom.getCurrentRetryCnt() + 1 > reTryCnt) {
                        nextAtomStatus(atom, AtomStatus.MANUAL);
                        throw new TccManualTransactionException(e.getMessage(), e);
                    } else {
                        nextAtomStatus(atom, getCurrAtomStatus(atom).error());
                        throw new TccTransactionException(e.getMessage(), e);
                    }
                }
            }
        }
        // TODO 重试+退出机制
        return flag;
    }

    private boolean checkRollback(Atom atom) {
        AtomStatus status = getCurrAtomStatus(atom);
        return status.isExecRollback();
    }

    private void comfirm() {
        for (int i = 0; i < atoms.size(); i++) {
            AtomContext atomContext = atoms.get(i);
            Atom atom = atomContext.getAtom();
            AtomTransaction atomTransaction = atomContext.getAtomTransaction();
            AtomStatus atomStatus = getCurrAtomStatus(atom);
            try {
                atomTransaction.confirm(atomContext.getArgs());
                nextAtomStatus(atom, atomStatus.success());
                // 记录成功日志
            } catch (Throwable e) {
                if (atom.getCurrentRetryCnt() + 1 > reTryCnt) {
                    nextAtomStatus(atom, AtomStatus.MANUAL);
                    throw new TccManualTransactionException(e.getMessage(), e);
                } else {
                    // retry cnt ++ and update atom status to RETRY
                    nextAtomStatus(atom, atomStatus.error());
                    throw new TccTransactionException(e.getMessage(), e);
                }

            }
        }
    }

    /**
     * 执行try方法
     * 
     * @param atoms
     */
    private void execTry() {
        for (int i = 0; i < atoms.size(); i++) {
            AtomContext atomContext = atoms.get(i);
            Atom atom = atomContext.getAtom();
            AtomTransaction atomTransaction = atomContext.getAtomTransaction();

            if (checkTry(atom)) {

                if (AtomStatus.CRT.equals(getCurrAtomStatus(atom))) {
                    nextAtomStatus(atom, getCurrAtomStatus(atom).success());
                }

                try {
                    atomTransaction.tryMethod(atomContext.getArgs());
                    nextAtomStatus(atom, getCurrAtomStatus(atom).success());
                } catch (UndoException e) {
                    // 明确需要回滚
                    nextAtomStatus(atom, getCurrAtomStatus(atom).error());
                    throw e;
                } catch (Throwable e) {
                    // 未知异常需要查询
                    nextAtomStatus(atom, getCurrAtomStatus(atom).query());
                    throw new TccTransactionException(e.getMessage(), e);
                }
            } else if (isQuery(atom)) {
                // 需要查询
                if (AtomStatus.TRY.equals(getCurrAtomStatus(atom))) {
                    // TRY -> query
                    nextAtomStatus(atom, getCurrAtomStatus(atom).query());
                }

                // Query 逻辑
                AtomStatus atomStatus = atomTransaction.query(atom.getAtomId());
                if (AtomStatus.CFM.equals(atomStatus)) {
                    // QUERY --> CFM
                    nextAtomStatus(atom, getCurrAtomStatus(atom).success());
                }

                if (AtomStatus.UNDO.equals(atomStatus)) {
                    // QUERY --> UNDO
                    nextAtomStatus(atom, getCurrAtomStatus(atom).error());
                    throw new UndoException("remote undo exception");
                }

                if (AtomStatus.QUERY.equals(atomStatus)) {
                    try {
                        atomTransaction.tryMethod(atomContext.getArgs());
                        nextAtomStatus(atom, getCurrAtomStatus(atom).success());
                    } catch (UndoException e) {
                        // 明确需要回滚
                        nextAtomStatus(atom, getCurrAtomStatus(atom).error());
                        throw e;
                    } catch (Throwable e) {
                        // 未知异常需要查询
                        nextAtomStatus(atom, getCurrAtomStatus(atom).query());
                        throw new TccTransactionException(e.getMessage(), e);
                    }
// QUERY
//                  throw new TccTransactionException("remote unknow exception ");
                }
            } else if (AtomStatus.UNDO.equals(getCurrAtomStatus(atom))) {
                throw new UndoException("recovery undo exception");
            }
        }
    }

    private boolean isQuery(Atom atom) {
        AtomStatus as = getCurrAtomStatus(atom);
        return AtomStatus.QUERY.equals(as) || AtomStatus.TRY.equals(as);
    }

    private AtomStatus getCurrAtomStatus(Atom atom) {
        return AtomStatus.valueOf(atom.getCurrentStatus());
    }

    /**
     * 更新原子单状态
     * 
     * @param atom
     *            原子单
     * @param nextStatus
     *            下一个状态
     */
    private void nextAtomStatus(Atom atom, AtomStatus nextStatus) {
        nextAtomStatus(atom, nextStatus, isSave);
    }

    /**
     * 更新原子单状态
     * 
     * @param atom
     *            原子单
     * @param nextStatus
     *            下一个状态
     */
    private void nextAtomStatus(Atom atom, AtomStatus nextStatus, boolean isP) {
        if (isP) {
            persistenceManager.nextAtomStatus(atom.getAtomId(), nextStatus.toString(), atom.getCurrentStatus());
        }
        atom.setLastStatus(atom.getCurrentStatus());
        atom.setCurrentStatus(nextStatus.toString());
    }

    /**
     * 更新组合单状态
     * 
     * @param composite
     *            组合单
     * @param compositeStatus
     *            组合单状态
     */
    private void nextCompositeStatus(Composite composite, CompositeStatus compositeStatus) {
        nextCompositeStatus(composite, compositeStatus, isSave);
    }

    /**
     * 更新组合单状态
     * 
     * @param composite
     *            组合单
     * @param compositeStatus
     *            组合单状态
     */
    private void nextCompositeStatus(Composite composite, CompositeStatus compositeStatus, boolean isP) {
        if (isP) {
            persistenceManager.nextCompositeStatus(composite.getId(), compositeStatus.toString(), composite.getCurrentStatus());
        }
        composite.setLastStatus(composite.getCurrentStatus());
        composite.setCurrentStatus(compositeStatus.toString());
    }

    private boolean checkTry(Atom atom) {
        AtomStatus status = getCurrAtomStatus(atom);
        return status.isExecTry();
    }

    public List<AtomContext> getAtoms() {
        return atoms;
    }

    public void setAtoms(List<AtomContext> atoms) {
        this.atoms = atoms;
    }

    public void setComposite(Composite composite) {
        this.composite = composite;
    }

    public CompositeTransaction(Composite composite, PersistenceManager persistenceManager) {
        super();
        this.composite = composite;
        this.persistenceManager = persistenceManager;
    }

    public CompositeTransaction(Composite composite, List<AtomContext> atoms, PersistenceManager persistenceManager) {
        super();
        this.composite = composite;
        this.atoms = atoms;
        this.persistenceManager = persistenceManager;
    }

}
