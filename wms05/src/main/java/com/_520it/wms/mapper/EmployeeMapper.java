package com._520it.wms.mapper;

import com._520it.wms.domain.Employee;
import com._520it.wms.query.EmployeeQueryObject;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface EmployeeMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Employee record);

    Employee selectByPrimaryKey(Long id);

    List<Employee> selectAll();

    int updateByPrimaryKey(Employee record);

    int query4count(EmployeeQueryObject qo);

    List<Employee> query4list(EmployeeQueryObject qo);

    void deleteRelation(Long id);

    void addRelation(@Param("employee_id") Long employee_id, @Param("role_id") Long role_id);

    Employee checkLogin(@Param("username") String username, @Param("password") String password);

    void batchDelete(int[] ids);
}