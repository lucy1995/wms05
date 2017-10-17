package com._520it.wms.service;

import com._520it.wms.domain.Role;
import com._520it.wms.page.PageResult;
import com._520it.wms.query.QueryObject;

import java.util.List;

/**
 * Created by Administrator on 2017/9/30.
 */
public interface IRoleService {
    int insert(Role record);

    int deleteByPrimaryKey(Long id);

    int updateByPrimaryKey(Role record);

    Role selectByPrimaryKey(Long id);

    List<Role> selectAll();

    PageResult query(QueryObject qo);
}
