package com._520it.wms.mapper;

import com._520it.wms.domain.Permission;
import com._520it.wms.query.QueryObject;

import java.util.List;

public interface PermissionMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Permission record);

    Permission selectByPrimaryKey(Long id);

    List<Permission> selectAll();

    int query4count(QueryObject qo);

    List<Permission> query4list(QueryObject qo);

}