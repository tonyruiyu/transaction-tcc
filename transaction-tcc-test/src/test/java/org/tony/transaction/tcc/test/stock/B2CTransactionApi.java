package org.tony.transaction.tcc.test.stock;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.tony.transaction.tcc.core.AtomStatus;

import org.tony.transaction.tcc.core.AtomTransaction;
import org.tony.transaction.tcc.test.QueryAtomStatysMock;

@Service("b2cTransactionApi" )
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class B2CTransactionApi extends MockBase implements AtomTransaction {

    
    @Override
    public Object tryMethod(Object... args) {
        System.out.println("执行 b2c try");
        saveStatus(getAtomCode()); 
        tryMockException();
        return null;
    }

    @Override
    public Object confirm(Object... args) {
        System.out.println("执行 b2c confirm");

        if (confirmError) {
            throw new RuntimeException("mock");
        }

        return null;
    }

    @Override
    public Object cancel(Object... args) {
        // TODO Auto-generated method stub
        System.out.println("执行 b2c cancel");
        if (cancelError) {
            throw new RuntimeException("mock");
        }

        return null;
    }

    @Override
    public String getAtomCode() {
        return "B2C";
    }

    @Override
    public AtomStatus query(String atomId) {
        return QueryAtomStatysMock.getAtomStatus(atomId);
    }

}
