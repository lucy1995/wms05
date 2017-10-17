package com._520it.wms.service;

import com._520it.wms.domain.StockIncomeBill;
import com._520it.wms.page.PageResult;
import com._520it.wms.query.QueryObject;

import java.util.List;

/**
 * Created by Administrator on 2017/9/30.
 */
public interface IStockIncomeBillService {
    int insert(StockIncomeBill record);

    int deleteByPrimaryKey(Long id);

    int updateByPrimaryKey(StockIncomeBill record);

    StockIncomeBill selectByPrimaryKey(Long id);

    List<StockIncomeBill> selectAll();

    PageResult query(QueryObject qo);

    void audit(Long bill_id);
}
