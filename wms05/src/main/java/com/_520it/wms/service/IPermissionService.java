package com._520it.wms.service;

import com._520it.wms.domain.Permission;
import com._520it.wms.page.PageResult;
import com._520it.wms.query.QueryObject;

import java.util.List;

/**
 * Created by Administrator on 2017/9/30.
 */
public interface IPermissionService {
    void reload();

    int deleteByPrimaryKey(Long id);

    Permission selectByPrimaryKey(Long id);

    List<Permission> selectAll();

    PageResult query(QueryObject qo);
}
