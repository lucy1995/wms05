package com._520it.wms.service.impl;

import com._520it.wms.domain.Depot;
import com._520it.wms.domain.ProductStock;
import com._520it.wms.domain.StockIncomeBillItem;
import com._520it.wms.domain.StockOutcomeBillItem;
import com._520it.wms.mapper.ProductStockMapper;
import com._520it.wms.page.PageResult;
import com._520it.wms.query.ProductStockQueryObject;
import com._520it.wms.service.IProductStockService;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by Administrator on 2017/9/11.
 */
@Service
public class ProductStockServiceImpl implements IProductStockService {
    @Autowired
    private ProductStockMapper productStockMapper;

    @Override
    public ProductStock getItemByDepotIdAndProductId(Long depotId, Long productId) {
        return productStockMapper.getItemByDepotIdAndProductId(depotId,productId);
    }

    @Override
    public void income(Depot depot , StockIncomeBillItem item) {
        ProductStock ps =getItemByDepotIdAndProductId(depot.getId(),item.getProduct().getId());
        if (ps==null){
            ps = new ProductStock();
            ps.setDepot(depot);
            ps.setProduct(item.getProduct());
            ps.setStoreNumber(item.getNumber());
            ps.setAmount(item.getAmount());
            ps.setPrice(ps.getAmount().divide(ps.getStoreNumber(),2, BigDecimal.ROUND_HALF_UP));
            productStockMapper.insert(ps);
        }
        ps.setStoreNumber(ps.getStoreNumber().add(item.getNumber()));
        ps.setAmount(ps.getAmount().add(item.getAmount()));
        ps.setPrice(ps.getAmount().divide(ps.getStoreNumber(),2,BigDecimal.ROUND_HALF_UP));
        productStockMapper.updateByPrimaryKey(ps);
    }

    @Override
    public BigDecimal outcome(Depot depot, StockOutcomeBillItem item) {
        ProductStock ps = getItemByDepotIdAndProductId(depot.getId(), item.getProduct().getId());
        if (ps == null) {
            throw new RuntimeException(depot.getName()+"中不存在"+item.getProduct().getName());
        }
        if (ps.getStoreNumber().compareTo(item.getNumber())<0){
            throw  new RuntimeException(depot.getName()+"中"+item.getProduct().getName()+"仅剩"+ps.getStoreNumber());
        }
        ps.setStoreNumber(ps.getStoreNumber().subtract(item.getNumber()));
        BigDecimal amount = ps.getPrice().multiply(item.getNumber()).setScale(2, BigDecimal.ROUND_HALF_UP);
        ps.setAmount(ps.getAmount().subtract(amount));
        productStockMapper.updateByPrimaryKey(ps);
        return ps.getPrice();
    }

    @Override
    public PageResult query(ProductStockQueryObject qo) {
        int count = productStockMapper.query4count(qo);
        if (count==0){
            return PageResult.emptyResult(qo.getPageSize());
        }
        List<ProductStock> list = productStockMapper.query4list(qo);
        return new PageResult(qo.getCurrentPage(),qo.getPageSize(),count,list);
    }

}
