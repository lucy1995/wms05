package com._520it.wms.service;

import com._520it.wms.domain.Depot;
import com._520it.wms.domain.ProductStock;
import com._520it.wms.domain.StockIncomeBillItem;
import com._520it.wms.domain.StockOutcomeBillItem;
import com._520it.wms.page.PageResult;
import com._520it.wms.query.ProductStockQueryObject;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by Administrator on 2017/9/11.
 */
public interface IProductStockService {
    ProductStock getItemByDepotIdAndProductId(Long depotId,Long productId);

    void income(Depot depot ,StockIncomeBillItem item);

    BigDecimal outcome(Depot depot, StockOutcomeBillItem item);

    PageResult query(ProductStockQueryObject qo);
}
