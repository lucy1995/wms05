package com._520it.wms.web.action;

import com._520it.wms.domain.SystemMenu;
import com._520it.wms.domain.RequiredPermission;
import com._520it.wms.query.QueryObject;
import com._520it.wms.query.SystemMenuQueryObject;
import com._520it.wms.service.ISystemMenuService;
import com.alibaba.fastjson.JSON;
import lombok.Getter;
import lombok.Setter;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/9/30.
 */
@Controller
@Scope("prototype")
public class SystemMenuAction extends BaseAction {
    @Autowired
    private ISystemMenuService systemMenuService;
    @Getter
    private SystemMenu systemMenu = new SystemMenu();
    @Getter
    private SystemMenuQueryObject qo = new SystemMenuQueryObject();

    @RequiredPermission("系统菜单列表")
    public String execute(){
        try {
            List<SystemMenu> menus =systemMenuService.getPrentMenus(qo.getParentId());
            putContext("menus",menus);
            putContext("pageResult",systemMenuService.query(qo));
        } catch (Exception e) {
            e.printStackTrace();
            addActionError(e.getMessage());
        }
        return LIST;
    }
    @RequiredPermission("系统菜单编辑")
    public String input(){
        try {
            if (qo.getParentId()>0){
                putContext("parentName",systemMenuService.selectByPrimaryKey(qo.getParentId()).getName());
            }else {
                putContext("parentName","根目录");
            }
            if (systemMenu.getId()!=null){
                systemMenu = systemMenuService.selectByPrimaryKey(systemMenu.getId());
            }
        } catch (Exception e) {
            e.printStackTrace();
            addActionError(e.getMessage());
        }
        return INPUT;
    }

    @RequiredPermission("系统菜单保存或修改")
    public String saveOrUpdate(){
        try {
            if (qo.getParentId()>0){
                SystemMenu parent = new SystemMenu();
                parent.setId(qo.getParentId());
                systemMenu.setParent(parent);
            }
            if (systemMenu.getId()!=null){
                systemMenuService.updateByPrimaryKey(systemMenu);
                addActionMessage("修改成功");
            }else {
                systemMenuService.insert(systemMenu);
                addActionMessage("保存成功");
            }
        } catch (Exception e) {
            e.printStackTrace();
            addActionError("修改或者保存失败:"+e.getMessage());
        }
        return  SUCCESS;
    }

    @RequiredPermission("系统菜单删除")
    public void delete(){
        try {
            if (systemMenu.getId()!=null){
                systemMenuService.deleteByPrimaryKey(systemMenu.getId());
                putMsg("删除成功");
            }
        } catch (Exception e) {
            e.printStackTrace();
            putMsg("删除失败:"+e.getMessage());
        }
    }

    public void queryMenuBySn(){
        try {
            List<Map<String,Object>> menuList = systemMenuService.queryMenuBySn(qo.getParentSn());
            ServletActionContext.getResponse().setContentType("text/json;charset=utf-8");
            ServletActionContext.getResponse().getWriter().print(JSON.toJSONString(menuList));
        } catch (IOException e) {
            e.printStackTrace();
            addActionError(e.getMessage());
        }
    }
}
