package org.tony.transaction.tcc.test.testcase;

import java.util.List;

import javax.annotation.Resource;

import com.google.common.collect.Lists;
import org.tony.transaction.tcc.core.AtomStatus;
import org.tony.transaction.tcc.core.CompositeTransaction;
import org.tony.transaction.tcc.core.TccTransactionManager;
import org.tony.transaction.tcc.test.stock.B2CTransactionApi;
import org.tony.transaction.tcc.test.stock.StockTransactionApi;
import org.tony.transaction.tcc.test.stock.bean.OrderInfo;
import org.tony.transaction.tcc.test.stock.bean.WarehProdStock;
/**
 * 
 * 测试通过tcc txmanger 方式实现方式
 * 
 * 实现测试用例
 * 1.B2C正常,stock正常
 * 2.B2C 发生undo异常 库存接口不被调用
 * 3.B2C正常,stock undo
 * 4.B2C unknow,stock不被调用
 * 5.b2c正常，stock unknow
 * 
 * 
 * 恢复事务
 * 
 */
public class TestB2CStockTccInterfaceCase extends StockB2cTransactionBase {

    @Resource
    TccTransactionManager tccTransactionManager;
    @Resource
    StockTransactionApi   stockTransactionApiImpl;
    @Resource
    B2CTransactionApi     b2cTransactionApi;
    
    protected CompositeTransaction submit() {
        
        Object[] args = buildArgs();

        //开启一个组合事务，传入事务类型
        CompositeTransaction compositeTransaction = tccTransactionManager.createTransaction("b2cstock");
        StockB2cTransactionBase.ctx.set(compositeTransaction);
        //注册b2c原子事务
        compositeTransaction.regeditAtom(b2cTransactionApi, new Object[] {});
        //注册库存原子事务
        compositeTransaction.regeditAtom(stockTransactionApiImpl, args);
        //提交事务
        try {
            compositeTransaction.submit();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return compositeTransaction;
    }

    private Object[] buildArgs() {
        Object[] args = new Object[5];
        List<WarehProdStock> wps = Lists.newArrayList();
        WarehProdStock twp = new WarehProdStock();
        twp.setProdCode("52980030011");
        twp.setWarehCode("HQO1W500");
        twp.setQtyCommitted(3.0d);
        wps.add(twp);
        OrderInfo order = new OrderInfo();
        order.setCitNumber("20199888200989893");
        order.setType("aaa");
        args[0] = wps;
        args[4] = order;
        return args;
    }

    @Override
    public void setB2CMockData(boolean isTryError, boolean isUndo) {
        b2cTransactionApi.setTryError(isTryError);
        b2cTransactionApi.setUndo(isUndo);
    }

    @Override
    public void setStockMockData(boolean isTryError, boolean isUndo) {
        stockTransactionApiImpl.setTryError(isTryError);
        stockTransactionApiImpl.setUndo(isUndo);
    }

    @Override
    public void setB2CMockData(boolean isTryError, boolean isUndo, AtomStatus atomStatus) {
        b2cTransactionApi.setTryError(isTryError);
        b2cTransactionApi.setUndo(isUndo);
        b2cTransactionApi.setQueryResult(atomStatus);
    }

    @Override
    public void setStockMockData(boolean isTryError, boolean isUndo, AtomStatus atomStatus) {
        stockTransactionApiImpl.setTryError(isTryError);
        stockTransactionApiImpl.setUndo(isUndo);
        stockTransactionApiImpl.setQueryResult(atomStatus);
    }

}
