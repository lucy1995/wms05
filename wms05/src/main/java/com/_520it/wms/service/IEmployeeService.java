package com._520it.wms.service;

import com._520it.wms.domain.Employee;
import com._520it.wms.page.PageResult;
import com._520it.wms.query.EmployeeQueryObject;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by Administrator on 2017/9/30.
 */
public interface IEmployeeService {
    int insert(Employee record);

    int deleteByPrimaryKey(Long id);

    int updateByPrimaryKey(Employee record);

    Employee selectByPrimaryKey(Long id);

    List<Employee> selectAll();

    PageResult query(EmployeeQueryObject qo);

    Employee checkLogin(String username,String password);

    void batchDelete(int[] ids);
}
