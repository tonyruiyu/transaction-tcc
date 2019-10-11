package org.tony.transaction.tcc.core;

import java.util.Date;
import java.util.UUID;

public class TccTransactionManager {

    private PersistenceManager                       persistenceManager;

    /**
     * 创建组合事务
     * 
     * @param code
     * @return
     */
    public CompositeTransaction createTransaction(String code) {
        Composite composite = createComposite(code);
        CompositeTransaction compositeTransaction = new CompositeTransaction(composite, persistenceManager);
        return compositeTransaction;
    }

    private Composite createComposite(String code) {
        Composite composite = new Composite();
        composite.setCode(code);
        composite.setCreateDate(new Date());
        composite.setCurrentStatus(CompositeStatus.CRT.name());
        composite.setCurrentTime(composite.getCreateDate());
        composite.setId(UUID.randomUUID().toString());
        composite.setLastStatus(composite.getCurrentStatus());
        persistenceManager.insertComposite(composite);
        return composite;
    }

    public PersistenceManager getPersistenceManager() {
        return persistenceManager;
    }

    public void setPersistenceManager(PersistenceManager persistenceManager) {
        this.persistenceManager = persistenceManager;
    }
    
}
