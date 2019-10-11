package org.tony.transaction.tcc.test.stock;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.tony.transaction.tcc.core.AtomStatus;
import org.tony.transaction.tcc.core.AtomTransaction;
import org.tony.transaction.tcc.test.QueryAtomStatysMock;

import org.tony.transaction.tcc.test.stock.api.StockApi;
import org.tony.transaction.tcc.test.stock.bean.OrderInfo;
import org.tony.transaction.tcc.test.stock.bean.UrUnitReservedStock;
import org.tony.transaction.tcc.test.stock.bean.WarehLockedUnitStock;
import org.tony.transaction.tcc.test.stock.bean.WarehProdLockedStock;
import org.tony.transaction.tcc.test.stock.bean.WarehProdStock;

@Service("stockTransactionApi")
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class StockTransactionApi extends MockBase implements AtomTransaction {

    @Resource
    StockApi          stockApiImpl;
    @Override
    public Object tryMethod(Object... args) {
        System.out.println("执行 stock try");

        saveStatus(getAtomCode());
        
        tryMockException();

        List<WarehProdStock> wp = (List<WarehProdStock>) args[0];
        List<WarehProdLockedStock> locked = (List<WarehProdLockedStock>) args[1];
        List<UrUnitReservedStock> uurs = (List<UrUnitReservedStock>) args[2];
        List<WarehLockedUnitStock> wlus = (List<WarehLockedUnitStock>) args[3];
        OrderInfo order = (OrderInfo) args[4];
        return stockApiImpl.change(wp, locked, uurs, wlus, order);

    }

    @Override
    public Object confirm(Object... args) {
        System.out.println("执行 stock confirm");
        if (confirmError) {
            throw new RuntimeException("mock");
        }
        return null;
    }

    @Override
    public Object cancel(Object... args) {
        System.out.println("执行 stock cancel");
        if (cancelError) {
            throw new RuntimeException("mock");
        }
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

    public StockApi getStockApiImpl() {
        return stockApiImpl;
    }

    public void setStockApiImpl(StockApi stockApiImpl) {
        this.stockApiImpl = stockApiImpl;
    }

}
