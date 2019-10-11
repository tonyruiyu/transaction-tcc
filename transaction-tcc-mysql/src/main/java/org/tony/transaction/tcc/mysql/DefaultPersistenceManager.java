package org.tony.transaction.tcc.mysql;

import java.util.List;
import org.tony.transaction.tcc.core.Assert;
import org.tony.transaction.tcc.core.Atom;
import org.tony.transaction.tcc.core.Composite;
import org.tony.transaction.tcc.core.PersistenceManager;
import org.tony.transaction.tcc.core.TccTransactionException;
import org.tony.transaction.tcc.mysql.mapper.AtomMapper;
import org.tony.transaction.tcc.mysql.mapper.CompositeMapper;
import org.tony.transaction.tcc.mysql.mapper.DefineMapper;

public class DefaultPersistenceManager implements PersistenceManager {

    private AtomMapper      atomMapper;

    private CompositeMapper compositeMapper;

    private DefineMapper    defineMapper;

    public void insertAtom(Atom atom) {
        org.tony.transaction.tcc.mysql.po.Atom record = new org.tony.transaction.tcc.mysql.po.Atom();
        record.setAtomId(atom.getAtomId());
        record.setAtomCode(atom.getCode());
        record.setCompositeId(atom.getCompositeId());
        record.setCreateDate(atom.getCreateDate());
        record.setCurrentRetryCnt(atom.getCurrentRetryCnt());
        record.setAtomCurrentStatus(atom.getCurrentStatus());
        record.setAtomCurrentTime(atom.getCurrentTime());
        record.setIocId(atom.getIocId());
        record.setLastStatus(atom.getLastStatus());
        record.setLockStatus(1);
        record.setLockTime(atom.getCurrentTime());
        record.setArgsContent(atom.getArgsContent());
        record.setAtomSort(atom.getAtomSort());
        int i = atomMapper.insertSelective(record);
        Assert.assertTrue(i == 1, "atom save error");
    }

    public void insertComposite(Composite composite) {
        org.tony.transaction.tcc.mysql.po.Composite record = new org.tony.transaction.tcc.mysql.po.Composite();
        record.setcCode(composite.getCode());
        record.setCreateDate(composite.getCreateDate());
        record.setcCurrentStatus(composite.getCurrentStatus());
        record.setcCurrentTime(composite.getCurrentTime());
        record.setId(composite.getId());
        record.setLastStatus(composite.getLastStatus());
        record.setLockStatus(1);
        record.setLockTime(composite.getCurrentTime());
        int i = compositeMapper.insertSelective(record);
        Assert.assertTrue(i == 1, "composite save error ");
    }

    public void nextAtomStatus(String atomId, String currentStatus, String lastStatus) {
        int i = defineMapper.updateAtomStatus(atomId, currentStatus, lastStatus);
        Assert.assertTrue(i == 1, "nextAtomStatus  error atom id : " + atomId + ",currentStatus:" + currentStatus + ",lastStatus:" + lastStatus);
    }

    public void nextCompositeStatus(String id, String currentStatus, String lastStatus) {
        try {
            int i = defineMapper.updateCompositeStatus(id, currentStatus, lastStatus);
            Assert.assertTrue(i == 1, "nextAtomStatus  error composite id : " + id + ",currentStatus:" + currentStatus + ",lastStatus:" + lastStatus);
        } catch (Exception e) {
            e.printStackTrace();
            throw new TccTransactionException(e.getMessage(), e);
        }
    }

    public AtomMapper getAtomMapper() {
        return atomMapper;
    }

    public void setAtomMapper(AtomMapper atomMapper) {
        this.atomMapper = atomMapper;
    }

    public CompositeMapper getCompositeMapper() {
        return compositeMapper;
    }

    public void setCompositeMapper(CompositeMapper compositeMapper) {
        this.compositeMapper = compositeMapper;
    }

    public DefineMapper getDefineMapper() {
        return defineMapper;
    }

    public void setDefineMapper(DefineMapper defineMapper) {
        this.defineMapper = defineMapper;
    }

    public List<Composite> loadCompositeByStatus(String code, List<String> status, long timeout) {
        return defineMapper.loadCompositeByStatus(code, status, timeout);
    }

    public List<Atom> loadAtomsByComposite(String cid) {
        return defineMapper.loadAtomsByComposite(cid);
    }

    public boolean obtainCompositeLock(String id, long timeout) {
        int i = defineMapper.obtainCompositeLock(id, timeout);
        return i == 1;
    }

    public Composite findComposite(String id) {
        org.tony.transaction.tcc.mysql.po.Composite comp = compositeMapper.selectByPrimaryKey(id);
        if (comp == null)
            return null;
        Composite c = new Composite();
        c.setCode(comp.getcCode());
        c.setCreateDate(comp.getCreateDate());
        c.setCurrentStatus(comp.getcCurrentStatus());
        c.setCurrentTime(comp.getcCurrentTime());
        c.setId(comp.getId());
        c.setLastStatus(comp.getLastStatus());
        c.setLockStatus(comp.getLockStatus());
        c.setLockTime(comp.getLockTime());
        return c;
    }

    public boolean releaseCompositeByStatus(String cid) {
        int i = defineMapper.releaseCompositeByStatus(cid);
        return i == 1;
    } 

    public void clearCompostie(String code,int saveDay) {
        defineMapper.clearCompostie(code,saveDay);
        defineMapper.clearAtom(saveDay);
    }

}
