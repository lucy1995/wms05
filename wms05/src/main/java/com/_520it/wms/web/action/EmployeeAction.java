package com._520it.wms.web.action;

import com._520it.wms.domain.Employee;
import com._520it.wms.domain.RequiredPermission;
import com._520it.wms.query.EmployeeQueryObject;
import com._520it.wms.query.QueryObject;
import com._520it.wms.service.IDepartmentService;
import com._520it.wms.service.IEmployeeService;
import com._520it.wms.service.IRoleService;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

/**
 * Created by Administrator on 2017/9/30.
 */
@Controller
@Scope("prototype")
public class EmployeeAction extends BaseAction {
    @Autowired
    private IEmployeeService employeeService;
    @Autowired
    private IRoleService roleService;
    @Autowired
    private IDepartmentService departmentService;
    @Getter
    private Employee employee = new Employee();
    @Getter
    private EmployeeQueryObject qo = new EmployeeQueryObject();
    @Setter
    private int[] ids;

    @RequiredPermission("员工列表")
    public String execute(){
        try {
            putContext("depts",departmentService.selectAll());
            putContext("pageResult",employeeService.query(qo));
        } catch (Exception e) {
            e.printStackTrace();
            addActionError(e.getMessage());
        }
        return LIST;
    }
    @RequiredPermission("员工编辑")
    public String input(){
        try {
            putContext("roles",roleService.selectAll());
            putContext("depts",departmentService.selectAll());
            if (employee.getId()!=null){
                employee = employeeService.selectByPrimaryKey(employee.getId());
            }
        } catch (Exception e) {
            e.printStackTrace();
            addActionError(e.getMessage());
        }
        return INPUT;
    }

    @RequiredPermission("员工保存或修改")
    public String saveOrUpdate(){
        if (employee.getId()!=null){
            employeeService.updateByPrimaryKey(employee);
            addActionMessage("修改成功");
        }else {
            employeeService.insert(employee);
            addActionMessage("保存成功");
        }
        return  SUCCESS;
    }

    @RequiredPermission("员工删除")
    public void delete(){
        try {
            if (employee.getId()!=null){
                employeeService.deleteByPrimaryKey(employee.getId());
                putMsg("删除成功");
            }
        } catch (Exception e) {
            e.printStackTrace();
            putMsg("删除失败:"+e.getMessage());
        }
    }

    @RequiredPermission("员工批量删除")
    public void batchDelete(){
        try {
            if (ids.length>0){
                employeeService.batchDelete(ids);
                putMsg("批量删除成功");
            }
        } catch (Exception e) {
            e.printStackTrace();
            putMsg("批量删除失败:"+e.getMessage());
        }
    }
}
