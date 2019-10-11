package org.tony.transaction.tcc.test.tcc;

import java.util.List;

import com.alibaba.fastjson.JSON;
import org.tony.transaction.tcc.core.Atom;
import org.tony.transaction.tcc.core.Composite;
import org.tony.transaction.tcc.core.PersistenceManager;
public class PersistenceManagerImpl implements  PersistenceManager {

    @Override
    public void insertAtom(Atom atom) {
        System.out.println("插入原子单日志记录 : " + JSON.toJSONString(atom));
    }

    @Override
    public void insertComposite(Composite composite) {
        System.out.println("插入组合单日志记录 : " + JSON.toJSONString(composite) );

    }

    @Override
    public void nextAtomStatus(String atomId, String currentStatus, String lastStatus) {
        System.out.println("更新原子单状态 : 原子id" + atomId + " 当前状态：" + currentStatus + " 上次状态：" +  lastStatus);
    }

    @Override
    public void nextCompositeStatus(String id, String currentStatus, String lastStatus) {
        System.out.println("更新组合单状态 : 组合id" + id + " 当前状态：" + currentStatus + " 上次状态：" +  lastStatus);

    }

    @Override
    public List<Atom> loadAtomsByComposite(String cid) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public boolean obtainCompositeLock(String id, long timeout) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public Composite findComposite(String id) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<Composite> loadCompositeByStatus(String code, List<String> status, long timeout) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public boolean releaseCompositeByStatus(String cid) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public void clearCompostie(String code,int saveDay) { 
        // TODO Auto-generated method stub
        
    }


}
