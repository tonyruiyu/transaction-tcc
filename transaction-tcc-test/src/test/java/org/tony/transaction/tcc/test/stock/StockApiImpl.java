package org.tony.transaction.tcc.test.stock;

import java.util.List;

import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import org.tony.transaction.tcc.test.stock.api.StockApi;
import org.tony.transaction.tcc.test.stock.bean.OrderInfo;
import org.tony.transaction.tcc.test.stock.bean.UrUnitReservedStock;
import org.tony.transaction.tcc.test.stock.bean.WarehLockedUnitStock;
import org.tony.transaction.tcc.test.stock.bean.WarehProdLockedStock;
import org.tony.transaction.tcc.test.stock.bean.WarehProdStock;

@Service
public class StockApiImpl implements StockApi {

    @Override
    public boolean change(List<WarehProdStock> wp, List<WarehProdLockedStock> locked, List<UrUnitReservedStock> uurs, List<WarehLockedUnitStock> wlus, OrderInfo order) {
        System.out.println("开始调用StockApiImpl.change");
        
        System.out.println("参数：");
        System.out.println(JSON.toJSONString(wp));
        System.out.println(JSON.toJSONString(locked));
        System.out.println(JSON.toJSONString(uurs));
        System.out.println(JSON.toJSONString(wlus));
        System.out.println(JSON.toJSONString(order));

        return true;
    }

    @Override
    public boolean change(List<WarehProdStock> wp, OrderInfo order) {
        return false;
    }


}
