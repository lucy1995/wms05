package com._520it.wms.web.action;

import com._520it.wms.domain.Permission;
import com._520it.wms.domain.RequiredPermission;
import com._520it.wms.query.QueryObject;
import com._520it.wms.service.IPermissionService;
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
public class PermissionAction extends BaseAction {
    @Autowired
    private IPermissionService permissionService;
    @Getter
    private Permission permission = new Permission();
    @Getter
    private QueryObject qo = new QueryObject();

    @RequiredPermission("权限列表")
    public String execute(){
        try {
            putContext("pageResult",permissionService.query(qo));
        } catch (Exception e) {
            e.printStackTrace();
            addActionError(e.getMessage());
        }
        return LIST;
    }
    @RequiredPermission("权限编辑")
    public String input(){
        try {
            if (permission.getId()!=null){
                permission = permissionService.selectByPrimaryKey(permission.getId());
            }
        } catch (Exception e) {
            e.printStackTrace();
            addActionError(e.getMessage());
        }
        return INPUT;
    }

    @RequiredPermission("权限删除")
    public void delete(){
        try {
            if (permission.getId()!=null){
                permissionService.deleteByPrimaryKey(permission.getId());
                putMsg("删除成功");
            }
        } catch (Exception e) {
            e.printStackTrace();
            putMsg("删除失败:"+e.getMessage());
        }
    }
    @RequiredPermission("权限加载")
    public void reload(){
        try {
            permissionService.reload();
            putMsg("重新加载成功");
        } catch (Exception e) {
            e.printStackTrace();
            putMsg("重新加载失败:"+e.getMessage());
        }
    }
}
