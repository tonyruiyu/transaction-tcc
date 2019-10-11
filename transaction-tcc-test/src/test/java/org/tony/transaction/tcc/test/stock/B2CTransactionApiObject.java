package org.tony.transaction.tcc.test.stock;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.tony.transaction.tcc.core.AtomStatus;
import org.tony.transaction.tcc.core.UndoException;
import org.tony.transaction.tcc.core.annotation.CancelMethod;
import org.tony.transaction.tcc.core.annotation.ConfirmMethod;
import org.tony.transaction.tcc.core.annotation.QueryMethod;
import org.tony.transaction.tcc.core.annotation.TryMethod;
import org.tony.transaction.tcc.test.QueryAtomStatysMock;


@Service("b2cTransactionApiObject" )
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class B2CTransactionApiObject extends MockBase  {
    
    @TryMethod(atomCode="b2c",rollback=UndoException.class)
    public Object tryMethod(Object... args) {
        System.out.println("执行 b2c try" + " tryError : " + tryError + " isUndo : " +isUndo);
        saveStatus("b2c"); 
        tryMockException();
        return null;
    }

    @ConfirmMethod
    public Object confirm(Object... args) {
        System.out.println("执行 b2c confirm");
        if (confirmError) {
            throw new RuntimeException("mock");
        }
        return null;
    }

    @CancelMethod
    public Object cancel(Object... args) {
        System.out.println("执行 b2c cancel");
        if (cancelError) {
            throw new RuntimeException("mock");
        }
        return null;
    }

    @QueryMethod
    public AtomStatus query(String atomId) {
        return QueryAtomStatysMock.getAtomStatus(atomId);
    }




   
}
