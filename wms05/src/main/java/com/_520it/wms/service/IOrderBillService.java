package com._520it.wms.service;

import com._520it.wms.domain.OrderBill;
import com._520it.wms.page.PageResult;
import com._520it.wms.query.OrderBillQueryObject;
import com._520it.wms.query.QueryObject;

import java.util.List;

/**
 * Created by Administrator on 2017/9/30.
 */
public interface IOrderBillService {
    int insert(OrderBill record);

    int deleteByPrimaryKey(Long id);

    int updateByPrimaryKey(OrderBill record);

    OrderBill selectByPrimaryKey(Long id);

    List<OrderBill> selectAll();

    PageResult query(OrderBillQueryObject qo);

    void audit(Long bill_id);
}
