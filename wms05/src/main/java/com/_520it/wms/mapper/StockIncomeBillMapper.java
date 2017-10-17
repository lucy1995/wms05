package com._520it.wms.mapper;

import com._520it.wms.domain.StockIncomeBill;
import com._520it.wms.domain.StockIncomeBillItem;
import com._520it.wms.query.QueryObject;

import java.util.List;

public interface StockIncomeBillMapper {
    int deleteByPrimaryKey(Long id);

    int insert(StockIncomeBill record);

    StockIncomeBill selectByPrimaryKey(Long id);

    List<StockIncomeBill> selectAll();

    int updateByPrimaryKey(StockIncomeBill record);

    int query4count(QueryObject qo);

    List<StockIncomeBill> query4list(QueryObject qo);

    void insertItem(StockIncomeBillItem item);

    void deleteItemByBillId(Long id);

    void audit(StockIncomeBill orderBill);
}