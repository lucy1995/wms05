package com._520it.wms.mapper;

import com._520it.wms.domain.Brand;
import com._520it.wms.domain.OrderBill;
import com._520it.wms.domain.OrderBillItem;
import com._520it.wms.query.OrderBillQueryObject;
import com._520it.wms.query.OrderChartQueryObject;
import com._520it.wms.query.QueryObject;

import java.util.List;

public interface OrderBillMapper {
    int deleteByPrimaryKey(Long id);

    int insert(OrderBill record);

    OrderBill selectByPrimaryKey(Long id);

    List<OrderBill> selectAll();

    int updateByPrimaryKey(OrderBill record);

    int query4count(OrderBillQueryObject qo);

    List<OrderBill> query4list(OrderBillQueryObject qo);

    void insertItem(OrderBillItem item);

    void deleteItemByBillId(Long id);

    void audit(OrderBill orderBill);
}