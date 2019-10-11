package org.tony.transaction.tcc.test.stock;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;
import org.tony.transaction.tcc.core.annotation.TccTransaction;
import org.tony.transaction.tcc.core.annotation.TryMethod;
import org.tony.transaction.tcc.test.stock.bean.OrderInfo;
import org.tony.transaction.tcc.test.stock.bean.WarehProdStock;

@Service
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class BusinessService  {

    @Autowired
    B2CTransactionApiObject b2CTransactionApiObject;
    @Autowired
    StockTransactionApiObj stockTransactionApiObj;
    @Autowired
    B2CTransactionApiObjectThread b2CTransactionApiObjectThread;
    @Autowired
    StockTransactionApiObjThread stockTransactionApiObjThread;

    
    @TccTransaction("b2cstock")
    public void run() {
        b2CTransactionApiObject.tryMethod(new Object[] {});
        stockTransactionApiObj.tryMethod(buildArgs());
    }

    @TccTransaction("b2cstock")
    public void runThread() {
        b2CTransactionApiObjectThread.tryMethod(new Object[] {});
        stockTransactionApiObjThread.tryMethod(new Object[] {});
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

    public B2CTransactionApiObject getB2CTransactionApiObject() {
        return b2CTransactionApiObject;
    }

    public void setB2CTransactionApiObject(B2CTransactionApiObject b2cTransactionApiObject) {
        b2CTransactionApiObject = b2cTransactionApiObject;
    }

    public StockTransactionApiObj getStockTransactionApiObj() {
        return stockTransactionApiObj;
    }

    public void setStockTransactionApiObj(StockTransactionApiObj stockTransactionApiObj) {
        this.stockTransactionApiObj = stockTransactionApiObj;
    }

    
    @TryMethod(atomCode="aa")
    public void test() {
        System.out.println("test");
        // TODO Auto-generated method stub
        
    }
}
