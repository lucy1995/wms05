package com._520it.wms.service;

import com._520it.wms.domain.StockOutcomeBill;
import com._520it.wms.page.PageResult;
import com._520it.wms.query.QueryObject;
import com._520it.wms.query.StockOutcomeBillQueryObject;

import java.util.List;

/**
 * Created by Administrator on 2017/9/30.
 */
public interface IStockOutcomeBillService {
    int insert(StockOutcomeBill record);

    int deleteByPrimaryKey(Long id);

    int updateByPrimaryKey(StockOutcomeBill record);

    StockOutcomeBill selectByPrimaryKey(Long id);

    List<StockOutcomeBill> selectAll();

    PageResult query(StockOutcomeBillQueryObject qo);

    void audit(Long bill_id);
}
