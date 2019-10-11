package org.tony.transaction.tcc.test.stock;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.tony.transaction.tcc.core.AtomStatus;
import org.tony.transaction.tcc.core.AtomTransaction;
import org.tony.transaction.tcc.test.QueryAtomStatysMock;


@Service("stockTransactionApiThread")
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class StockTransactionApiThread  implements AtomTransaction {

    @Override
    public Object tryMethod(Object... args) {
        return null;
    }

    @Override
    public Object confirm(Object... args) {
        return null;
    }

    @Override
    public Object cancel(Object... args) {
        return null;
    }

    @Override
    public String getAtomCode() {
        return "stock";
    }

    @Override
    public AtomStatus query(String atomId) {
        return QueryAtomStatysMock.getAtomStatus(atomId);
    }
}
