package org.tony.transaction.tcc.test.testcase;

import javax.annotation.Resource;

import org.junit.Before;
import org.junit.Test;
import org.tony.transaction.tcc.core.AtomStatus;
import org.tony.transaction.tcc.core.CompositeTransaction;
import org.tony.transaction.tcc.core.recovery.CompositeTransactionRecoveryManager;
import org.tony.transaction.tcc.spring.AtomTransactionAspect;
import org.tony.transaction.tcc.test.stock.BusinessService;


public class TestB2CStockAnnotationCase extends StockB2cTransactionBase {
    
    @Resource
    BusinessService businessService;

    @Before
    public void bef() {
        businessService = CompositeTransactionRecoveryManager.applicationContext.getBean(BusinessService.class);
    }

    protected CompositeTransaction submit() {
        try {
            businessService.run();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return StockB2cTransactionBase.ctx.get();
    }

    @Override 
    public void setB2CMockData(boolean isTryError, boolean isUndo) {
        super.setB2CMockData(isTryError, isUndo);
        businessService.getB2CTransactionApiObject().setTryError(isTryError);
        businessService.getB2CTransactionApiObject().setUndo(isUndo);
    }

    @Override
    public void setStockMockData(boolean isTryError, boolean isUndo) {
        super.setStockMockData(isTryError, isUndo);
        businessService.getStockTransactionApiObj().setTryError(isTryError);
        businessService.getStockTransactionApiObj().setUndo(isUndo);
    }

    @Override
    public void setB2CMockData(boolean isTryError, boolean isUndo, AtomStatus atomStatus) {
        super.setB2CMockData(isTryError, isUndo, atomStatus);
        businessService.getB2CTransactionApiObject().setTryError(isTryError);
        businessService.getB2CTransactionApiObject().setUndo(isUndo);
        businessService.getB2CTransactionApiObject().setQueryResult(atomStatus);
    }

    @Override
    public void setStockMockData(boolean isTryError, boolean isUndo, AtomStatus atomStatus) {
        super.setStockMockData(isTryError, isUndo, atomStatus);
        businessService.getStockTransactionApiObj().setTryError(isTryError);
        businessService.getStockTransactionApiObj().setUndo(isUndo);
        businessService.getStockTransactionApiObj().setQueryResult(atomStatus);
    }
    
}
