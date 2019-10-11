package org.tony.transaction.tcc.core.recovery;

import java.util.List;
import org.tony.transaction.tcc.core.AtomContext;
import org.tony.transaction.tcc.core.Composite;
import org.tony.transaction.tcc.core.CompositeTransaction;
import org.tony.transaction.tcc.core.PersistenceManager;

public class RecoveryExecutor  {

    private PersistenceManager persistenceManager;

    public RecoveryExecutor(PersistenceManager persistenceManager) {
        super();
        this.persistenceManager = persistenceManager;
    }

    public void recovery(Composite composite, List<AtomContext> atomContexts) {
        CompositeTransaction compositeTransaction = new CompositeTransaction(composite, atomContexts, persistenceManager);
        try {
            compositeTransaction.submit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
