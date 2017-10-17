package com._520it.wms.service.impl;

import com._520it.wms.domain.Product;
import com._520it.wms.mapper.ProductMapper;
import com._520it.wms.page.PageResult;
import com._520it.wms.query.ProductQueryObject;
import com._520it.wms.query.QueryObject;
import com._520it.wms.service.IProductService;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Administrator on 2017/9/11.
 */
@Service
public class ProductServiceImpl implements IProductService {
    @Autowired
    private ProductMapper productMapper;
    @Override
    public void save(Product product) {
        productMapper.insert(product);
    }

    @Override
    public void delete(Long id) {
        productMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void update(Product product) {
        productMapper.updateByPrimaryKey(product);
    }

    @Override
    public Product get(Long id) {
        return productMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Product> listAll() {
        return productMapper.selectAll();
    }

    @Override
    public PageResult query(ProductQueryObject qo) {
        int totalCount = productMapper.query4count(qo);
        if (totalCount==0){
            return  new PageResult().emptyResult(qo.getPageSize());
        }
        List<Product> listData = productMapper.query4list(qo);
        return new PageResult(qo.getCurrentPage(),qo.getPageSize(),totalCount,listData);
    }
}
