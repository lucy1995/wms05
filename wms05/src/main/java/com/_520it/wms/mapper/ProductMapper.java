package com._520it.wms.mapper;

import com._520it.wms.domain.Brand;
import com._520it.wms.domain.Product;
import com._520it.wms.query.ProductQueryObject;
import com._520it.wms.query.QueryObject;

import java.util.List;

public interface ProductMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Product record);

    Product selectByPrimaryKey(Long id);

    List<Product> selectAll();

    int updateByPrimaryKey(Product record);

    int query4count(ProductQueryObject qo);

    List<Product> query4list(ProductQueryObject qo);
}