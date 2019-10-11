package org.tony.transaction.tcc.core.recovery;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.util.SerializationUtils;
import org.tony.transaction.tcc.core.Atom;
import org.tony.transaction.tcc.core.AtomContext;
import org.tony.transaction.tcc.core.AtomTransaction;
import org.tony.transaction.tcc.core.Composite;
import org.tony.transaction.tcc.core.CompositeStatus;
import org.tony.transaction.tcc.core.DefaultAtomTransaction;
import org.tony.transaction.tcc.core.PersistenceManager;
import org.tony.transaction.tcc.core.TccTransactionException;

public class CompositeTransactionRecoveryManager implements ApplicationContextAware {

    private final List<String>       status  = loadStatus();

    private PersistenceManager       persistenceManager;

    private long                     timeout = 5 * 60;

    private String[]                 codes;

    public static ApplicationContext applicationContext;

    private List<Composite> loadComposite(String code) {
        return persistenceManager.loadCompositeByStatus(code, status, timeout);
    }

    public Composite loadCompositeByUd(String id) {
        return persistenceManager.findComposite(id);
    }

    private List<String> loadStatus() {
        List<String> status = new ArrayList<String>();
        for (CompositeStatus composite : CompositeStatus.values()) {
            if (!composite.isEndPoint()) {
                status.add(composite.name());
            }
        }
        return status;
    }

    public void recoveryTransaction() {

        final CountDownLatch cdl = new CountDownLatch(codes.length);

        for (int i = 0; i < codes.length; i++) {
            final String code = codes[i];
            new Thread(new Runnable() {
                public void run() {
                    try {
                        List<Composite> composites = loadComposite(code);
                        if (composites == null || composites.isEmpty()) {
                            return;
                        }
                        for (int i = 0; i < composites.size(); i++) {
                            recoveryComposite(composites.get(i));
                        }
                    } finally {
                        cdl.countDown();
                    }
                }

            }).start();

        }
        
        try {
            cdl.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public boolean recoveryComposite(Composite composite) {
        if (!obtainLock(composite)) {
            return false;
        }
        composite = reloadComposite(composite.getId());
        List<AtomContext> atomContexts = loadAtomsByComposite(composite);

        RecoveryExecutor recoveryExecutor = new RecoveryExecutor(persistenceManager);
        recoveryExecutor.recovery(composite, atomContexts);
        return true;
    }

    public Composite reloadComposite(String id) {
        return persistenceManager.findComposite(id);
    }

    private boolean obtainLock(Composite composite) {
        return persistenceManager.obtainCompositeLock(composite.getId(), timeout);
    }

    public List<AtomContext> loadAtomsByComposite(Composite composite) {
        List<Atom> atoms = persistenceManager.loadAtomsByComposite(composite.getId());
        List<AtomContext> atomContexts = new ArrayList<AtomContext>();
        if (atoms == null || atoms.isEmpty()) {
            return atomContexts;
        }
        for (int i = 0; i < atoms.size(); i++) {
            Atom atom = atoms.get(i);
            AtomContext atomContext = buildAtomContext(composite, atom);
            atomContexts.add(atomContext);
        }
        return atomContexts;
    }

    /**
     * 保留天使
     * 
     * @param saveDay
     */
    public void clearCompostie(int saveDay) {
        for (int i = 0; i < codes.length; i++) {
            persistenceManager.clearCompostie(codes[i], saveDay);
        }
    }

    private AtomContext buildAtomContext(Composite composite, Atom atom) {
        Class<?> requiredType = buildRequiredType(atom);
        Object obj = applicationContext.getBean(requiredType);
        AtomTransaction atomTransaction = null;
        if (obj instanceof AtomTransaction) {
            atomTransaction = (AtomTransaction) obj;
        } else {
            atomTransaction = new DefaultAtomTransaction(obj);
        }
        AtomContext atomContext = new AtomContext();
        atomContext.setAtomTransaction(atomTransaction);
        atomContext.setAtom(atom);
        atomContext.setComposite(composite);
        buildArgs(atom);
        atomContext.setArgs(atom.getArgs());
        return atomContext;
    }

    @SuppressWarnings("unchecked")
    private Class<? extends AtomTransaction> buildRequiredType(Atom atom) {
        Class<? extends AtomTransaction> requiredType = null;

        try {
            requiredType = (Class<AtomTransaction>) Class.forName(atom.getIocId());
        } catch (ClassNotFoundException e) {
            throw new TccTransactionException(e.getMessage(), e);
        }

        if (requiredType == null) {
            throw new TccTransactionException("requiredType error type : " + atom.getIocId());
        }
        return requiredType;
    }

    private void buildArgs(Atom atom) {
        byte[] buf = atom.getArgsContent();
        if (buf == null || buf.length < 1) {
            atom.setArgs(new Object[] {});
        } else {
            Object[] args = (Object[]) SerializationUtils.deserialize(buf);
            atom.setArgs(args);
        }
    }

    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        CompositeTransactionRecoveryManager.applicationContext = applicationContext;
    }

    public PersistenceManager getPersistenceManager() {
        return persistenceManager;
    }

    public void setPersistenceManager(PersistenceManager persistenceManager) {
        this.persistenceManager = persistenceManager;
    }

    public long getTimeout() {
        return timeout;
    }

    public void setTimeout(long timeout) {
        this.timeout = timeout;
    }

    public String[] getCodes() {
        return codes;
    }

    public void setCodes(String[] codes) {
        this.codes = codes;
    }

}
