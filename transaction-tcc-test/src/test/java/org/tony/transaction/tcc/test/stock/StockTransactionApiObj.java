package org.tony.transaction.tcc.test.stock;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.tony.transaction.tcc.core.AtomStatus;
import org.tony.transaction.tcc.core.annotation.CancelMethod;
import org.tony.transaction.tcc.core.annotation.ConfirmMethod;
import org.tony.transaction.tcc.core.annotation.QueryMethod;
import org.tony.transaction.tcc.core.annotation.TryMethod;
import org.tony.transaction.tcc.test.QueryAtomStatysMock;

import org.tony.transaction.tcc.test.stock.api.StockApi;
import org.tony.transaction.tcc.test.stock.bean.OrderInfo;
import org.tony.transaction.tcc.test.stock.bean.UrUnitReservedStock;
import org.tony.transaction.tcc.test.stock.bean.WarehLockedUnitStock;
import org.tony.transaction.tcc.test.stock.bean.WarehProdLockedStock;
import org.tony.transaction.tcc.test.stock.bean.WarehProdStock;

@Service("stockTransactionApiObj")
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class StockTransactionApiObj extends MockBase {

    @Resource
    StockApi          stockApiImpl;

    @TryMethod(atomCode = "stock")
    public Object tryMethod(Object... args) {
        System.out.println("执行 stock try" + " tryError : " + tryError + " isUndo : " + isUndo);
        saveStatus("stock");
        tryMockException();
        List<WarehProdStock> wp = (List<WarehProdStock>) args[0];
        List<WarehProdLockedStock> locked = (List<WarehProdLockedStock>) args[1];
        List<UrUnitReservedStock> uurs = (List<UrUnitReservedStock>) args[2];
        List<WarehLockedUnitStock> wlus = (List<WarehLockedUnitStock>) args[3];
        OrderInfo order = (OrderInfo) args[4];
        return stockApiImpl.change(wp, locked, uurs, wlus, order);
    }

    @ConfirmMethod
    public Object confirm(Object... args) {
        System.out.println("执行 stock confirm");
        if (confirmError) {
            throw new RuntimeException("mock");
        }
        return null;
    }

    @CancelMethod
    public Object cancel(Object... args) {
        System.out.println("执行 stock cancel");
        if (cancelError) {
            throw new RuntimeException("mock");
        }
        return null;
    }

    @QueryMethod
    public AtomStatus query(String atomId) {
        return QueryAtomStatysMock.getAtomStatus(atomId);
    }

    public StockApi getStockApiImpl() {
        return stockApiImpl;
    }

    public void setStockApiImpl(StockApi stockApiImpl) {
        this.stockApiImpl = stockApiImpl;
    }
}
