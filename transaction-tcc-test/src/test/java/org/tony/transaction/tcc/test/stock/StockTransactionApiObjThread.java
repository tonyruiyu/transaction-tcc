package org.tony.transaction.tcc.test.stock;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.tony.transaction.tcc.core.AtomStatus;
import org.tony.transaction.tcc.core.annotation.CancelMethod;
import org.tony.transaction.tcc.core.annotation.ConfirmMethod;
import org.tony.transaction.tcc.core.annotation.QueryMethod;
import org.tony.transaction.tcc.core.annotation.TryMethod;

@Service("stockTransactionApiObjT")
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class StockTransactionApiObjThread  {


    @TryMethod(atomCode = "stock")
    public Object tryMethod(Object... args) {
        return null;
    }

    @ConfirmMethod
    public Object confirm(Object... args) {
        return null;
    }

    @CancelMethod
    public Object cancel(Object... args) {
        return null;
    }

    @QueryMethod
    public AtomStatus query(String atomId) {
        return null;
    }

}
