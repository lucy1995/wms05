package com._520it.wms.service;

import com._520it.wms.domain.Product;
import com._520it.wms.page.PageResult;
import com._520it.wms.query.ProductQueryObject;
import com._520it.wms.query.QueryObject;

import java.util.List;

/**
 * Created by Administrator on 2017/9/11.
 */
public interface IProductService {
    void save(Product product);

    void delete(Long id);

    void update(Product product);

    Product get(Long id);

    List<Product> listAll();

    PageResult query(ProductQueryObject qo);
}
