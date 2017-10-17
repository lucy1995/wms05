package com._520it.wms.service.impl;

import com._520it.wms.domain.Employee;
import com._520it.wms.domain.Permission;
import com._520it.wms.domain.Role;
import com._520it.wms.mapper.EmployeeMapper;
import com._520it.wms.page.PageResult;
import com._520it.wms.query.EmployeeQueryObject;
import com._520it.wms.query.QueryObject;
import com._520it.wms.service.IEmployeeService;
import com._520it.wms.util.UserContext;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by Administrator on 2017/9/30.
 */
@Service
public class EmployeeServiceImpl implements IEmployeeService {
    @Autowired
    private EmployeeMapper employeeMapper;

    @Override
    public int insert(Employee record) {
        employeeMapper.insert(record);
        List<Role> roles = record.getRoles();
        for (Role role : roles) {
            employeeMapper.addRelation(record.getId(),role.getId());
        }
        return 0;
    }

    @Override
    public int deleteByPrimaryKey(Long id) {
        employeeMapper.deleteRelation(id);
        employeeMapper.deleteByPrimaryKey(id);
        return 0;
    }

    @Override
    public int updateByPrimaryKey(Employee record) {
        employeeMapper.deleteRelation(record.getId());
        List<Role> roles = record.getRoles();
        for (Role role : roles) {
            employeeMapper.addRelation(record.getId(),role.getId());
        }
        employeeMapper.updateByPrimaryKey(record);
        return 0;
    }

    @Override
    public Employee selectByPrimaryKey(Long id) {
        return employeeMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Employee> selectAll() {
        return employeeMapper.selectAll();
    }

    @Override
    public PageResult query(EmployeeQueryObject qo) {
        int count = employeeMapper.query4count(qo);
        if (count==0) {return PageResult.emptyResult(qo.getPageSize());}
        List<Employee> listData = employeeMapper.query4list(qo);
        return new PageResult(qo.getCurrentPage(),qo.getPageSize(),count,listData);
    }

    @Override
    public Employee checkLogin(String username, String password) {
        Employee e =employeeMapper.checkLogin(username,password);
        Set<String> expressions = new HashSet<>();
        if (e!=null){
            List<Role> roles = e.getRoles();
            for (Role role : roles) {
                List<Permission> permissions = role.getPermissions();
                for (Permission permission : permissions) {
                    expressions.add(permission.getExpression());
                }
            }
            UserContext.putEmployeeExpressionsSession(expressions);
            UserContext.putEmployeeSession(e);
        }
        return e;
    }

    @Override
    public void batchDelete(int[] ids) {
        employeeMapper.batchDelete(ids);
    }
}
