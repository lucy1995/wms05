package com._520it.wms.mapper;

import com._520it.wms.domain.Supplier;
import com._520it.wms.query.QueryObject;

import java.util.List;

public interface SupplierMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Supplier record);

    Supplier selectByPrimaryKey(Long id);

    List<Supplier> selectAll();

    int updateByPrimaryKey(Supplier record);

    int query4count(QueryObject qo);

    List<Supplier> query4list(QueryObject qo);
}