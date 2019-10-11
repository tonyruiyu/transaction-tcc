package org.tony.transaction.tcc.test.stock.api;

import java.util.List;

import org.tony.transaction.tcc.test.stock.bean.OrderInfo;
import org.tony.transaction.tcc.test.stock.bean.UrUnitReservedStock;
import org.tony.transaction.tcc.test.stock.bean.WarehLockedUnitStock;
import org.tony.transaction.tcc.test.stock.bean.WarehProdLockedStock;
import org.tony.transaction.tcc.test.stock.bean.WarehProdStock;

public interface StockApi {

    public boolean change(List<WarehProdStock> wp, List<WarehProdLockedStock> locked, List<UrUnitReservedStock> uurs, List<WarehLockedUnitStock> wlus, OrderInfo order);
    
    public boolean change(List<WarehProdStock> wp, OrderInfo order);


}
