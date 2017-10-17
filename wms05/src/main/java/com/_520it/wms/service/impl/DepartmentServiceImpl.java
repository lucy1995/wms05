package com._520it.wms.service.impl;

import com._520it.wms.domain.Department;
import com._520it.wms.mapper.DepartmentMapper;
import com._520it.wms.page.PageResult;
import com._520it.wms.query.QueryObject;
import com._520it.wms.service.IDepartmentService;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Administrator on 2017/9/30.
 */
@Service
public class DepartmentServiceImpl implements IDepartmentService {
    @Autowired
    private DepartmentMapper departmentMapper;

    @Override
    public int insert(Department record) {
        departmentMapper.insert(record);
        return 0;
    }

    @Override
    public int deleteByPrimaryKey(Long id) {
        departmentMapper.deleteByPrimaryKey(id);
        return 0;
    }

    @Override
    public int updateByPrimaryKey(Department record) {
        departmentMapper.updateByPrimaryKey(record);
        return 0;
    }

    @Override
    public Department selectByPrimaryKey(Long id) {
        return departmentMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Department> selectAll() {
        return departmentMapper.selectAll();
    }

    @Override
    public PageResult query(QueryObject qo) {
        int count = departmentMapper.query4count(qo);
        if (count==0) {return PageResult.emptyResult(qo.getPageSize());}
        List<Department> listData = departmentMapper.query4list(qo);
        return new PageResult(qo.getCurrentPage(),qo.getPageSize(),count,listData);
    }
}
