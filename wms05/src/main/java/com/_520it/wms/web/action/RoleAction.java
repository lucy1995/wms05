package com._520it.wms.web.action;

import com._520it.wms.domain.Role;
import com._520it.wms.domain.RequiredPermission;
import com._520it.wms.query.QueryObject;
import com._520it.wms.service.IPermissionService;
import com._520it.wms.service.IRoleService;
import com._520it.wms.service.ISystemMenuService;
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
public class RoleAction extends BaseAction {
    @Autowired
    private IRoleService roleService;
    @Autowired
    private IPermissionService permissionService;
    @Autowired
    private ISystemMenuService systemMenuService;
    @Getter
    private Role role = new Role();
    @Getter
    private QueryObject qo = new QueryObject();

    @RequiredPermission("角色列表")
    public String execute(){
        try {
            putContext("pageResult",roleService.query(qo));
        } catch (Exception e) {
            e.printStackTrace();
            addActionError(e.getMessage());
        }
        return LIST;
    }
    @RequiredPermission("角色编辑")
    public String input(){
        try {
            putContext("permissions",permissionService.selectAll());
            putContext("menus",systemMenuService.selectAll());
            if (role.getId()!=null){
                role = roleService.selectByPrimaryKey(role.getId());
            }
        } catch (Exception e) {
            e.printStackTrace();
            addActionError(e.getMessage());
        }
        return INPUT;
    }

    @RequiredPermission("角色保存或修改")
    public String saveOrUpdate(){
        if (role.getId()!=null){
            roleService.updateByPrimaryKey(role);
            addActionMessage("修改成功");
        }else {
            roleService.insert(role);
            addActionMessage("保存成功");
        }
        return  SUCCESS;
    }

    @RequiredPermission("角色删除")
    public void delete(){
        try {
            if (role.getId()!=null){
                roleService.deleteByPrimaryKey(role.getId());
                putMsg("删除成功");
            }
        } catch (Exception e) {
            e.printStackTrace();
            putMsg("删除失败:"+e.getMessage());
        }
    }
}
