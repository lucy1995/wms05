package com._520it.wms.web.action;

import com._520it.wms.domain.Department;
import com._520it.wms.domain.RequiredPermission;
import com._520it.wms.query.QueryObject;
import com._520it.wms.service.IDepartmentService;
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
public class DepartmentAction extends BaseAction {
    @Autowired
    private IDepartmentService departmentService;
    @Getter
    private Department department = new Department();
    @Getter
    private QueryObject qo = new QueryObject();

    @RequiredPermission("部门列表")
    public String execute(){
        try {
            putContext("pageResult",departmentService.query(qo));
        } catch (Exception e) {
            e.printStackTrace();
            addActionError(e.getMessage());
        }
        return LIST;
    }
    @RequiredPermission("部门编辑")
    public String input(){
        try {
            if (department.getId()!=null){
                department = departmentService.selectByPrimaryKey(department.getId());
            }
        } catch (Exception e) {
            e.printStackTrace();
            addActionError(e.getMessage());
        }
        return INPUT;
    }

    @RequiredPermission("部门保存或修改")
    public String saveOrUpdate(){
        if (department.getId()!=null){
            departmentService.updateByPrimaryKey(department);
            addActionMessage("修改成功");
        }else {
            departmentService.insert(department);
            addActionMessage("保存成功");
        }
        return  SUCCESS;
    }

    @RequiredPermission("部门删除")
    public void delete(){
        try {
            if (department.getId()!=null){
                departmentService.deleteByPrimaryKey(department.getId());
                putMsg("删除成功");
            }
        } catch (Exception e) {
            e.printStackTrace();
            putMsg("删除失败:"+e.getMessage());
        }
    }
}
