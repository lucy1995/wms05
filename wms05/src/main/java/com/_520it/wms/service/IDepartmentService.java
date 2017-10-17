package com._520it.wms.service;

import com._520it.wms.domain.Department;
import com._520it.wms.page.PageResult;
import com._520it.wms.query.QueryObject;

import java.util.List;

/**
 * Created by Administrator on 2017/9/30.
 */
public interface IDepartmentService {
    int insert(Department record);

    int deleteByPrimaryKey(Long id);

    int updateByPrimaryKey(Department record);

    Department selectByPrimaryKey(Long id);

    List<Department> selectAll();

    PageResult query(QueryObject qo);
}
