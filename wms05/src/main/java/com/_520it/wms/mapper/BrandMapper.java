package com._520it.wms.mapper;

import com._520it.wms.domain.Brand;
import com._520it.wms.query.QueryObject;

import java.util.List;

public interface BrandMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Brand record);

    Brand selectByPrimaryKey(Long id);

    List<Brand> selectAll();

    int updateByPrimaryKey(Brand record);

    int query4count(QueryObject qo);

    List<Brand> query4list(QueryObject qo);
}