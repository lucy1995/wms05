package com._520it.wms.web.action;

import com._520it.wms.domain.Depot;
import com._520it.wms.domain.RequiredPermission;
import com._520it.wms.page.PageResult;
import com._520it.wms.query.QueryObject;
import com._520it.wms.service.IDepotService;
import com.opensymphony.xwork2.interceptor.annotations.InputConfig;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

/**
 * Created by Administrator on 2017/9/11.
 */
@Controller
@Scope("prototype")
public class DepotAction extends BaseAction {

    @Autowired
    private IDepotService depotService;
    @Getter
    Depot depot = new Depot();
    @Getter
    QueryObject qo = new QueryObject();

    @RequiredPermission("仓库列表")
    @InputConfig(methodName = "input")
    public String execute()  {
        try {
            PageResult pageResult = depotService.query(qo);
            putContext("pageResult",pageResult);
        } catch (Exception e) {
            e.printStackTrace();
            addActionError(e.getMessage());
        }
        return LIST;
    }

    @RequiredPermission("仓库编辑")
    public String input() {
        try {
            if (depot.getId()!=null){
                depot = depotService.get(depot.getId());
            }
            int i = 1/0;
        } catch (Exception e) {
            e.printStackTrace();
            addActionError(e.getMessage());
        }
        return INPUT;
    }

    @RequiredPermission("仓库删除")
    public String delete() {
        try {
            depotService.delete(depot.getId());
            putMsg("删除成功");
        } catch (Exception e) {
            e.printStackTrace();
            putMsg("删除失败:"+e.getMessage());
        }
        return NONE;
    }

    @RequiredPermission("仓库更新")
    public String saveOrUpdate() {
        try {
            if (depot.getId()!=null){
                depotService.update(depot);
                addActionMessage("更新成功");
            }else {
                depotService.save(depot);
                addActionMessage("保存成功");
            }
        } catch (Exception e) {
            e.printStackTrace();
            addActionError("更新失败:"+e.getMessage());
        }
        return SUCCESS;
    }
}
