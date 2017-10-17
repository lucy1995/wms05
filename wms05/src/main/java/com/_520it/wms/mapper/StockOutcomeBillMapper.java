package com._520it.wms.mapper;

import com._520it.wms.domain.StockOutcomeBill;
import com._520it.wms.domain.StockOutcomeBill;
import com._520it.wms.domain.StockOutcomeBillItem;
import com._520it.wms.query.StockOutcomeBillQueryObject;

import java.util.List;

public interface StockOutcomeBillMapper {
    int deleteByPrimaryKey(Long id);

    int insert(StockOutcomeBill record);

    StockOutcomeBill selectByPrimaryKey(Long id);

    List<StockOutcomeBill> selectAll();

    int updateByPrimaryKey(StockOutcomeBill record);

    int query4count(StockOutcomeBillQueryObject qo);

    List<StockOutcomeBill> query4list(StockOutcomeBillQueryObject qo);

    void insertItem(StockOutcomeBillItem item);

    void deleteItemByBillId(Long id);

    void audit(StockOutcomeBill stockOutcomeBill);
}