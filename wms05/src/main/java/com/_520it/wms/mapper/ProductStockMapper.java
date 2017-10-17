package com._520it.wms.mapper;

import com._520it.wms.domain.ProductStock;
import com._520it.wms.query.ProductStockQueryObject;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ProductStockMapper {
    int deleteByPrimaryKey(Long id);

    int insert(ProductStock record);

    ProductStock selectByPrimaryKey(Long id);

    List<ProductStock> selectAll();

    int updateByPrimaryKey(ProductStock record);

    int query4count(ProductStockQueryObject qo);

    List<ProductStock> query4list(ProductStockQueryObject qo);

    ProductStock getItemByDepotIdAndProductId(@Param("depotId") Long depotId,@Param("productId")  Long productId);
}