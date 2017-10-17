package com._520it.wms.web.action;

import com._520it.wms.domain.Client;
import com._520it.wms.domain.RequiredPermission;
import com._520it.wms.page.PageResult;
import com._520it.wms.query.QueryObject;
import com._520it.wms.service.IClientService;
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
public class ClientAction extends BaseAction {

    @Autowired
    private IClientService clientService;
    @Getter
    Client client = new Client();
    @Getter
    QueryObject qo = new QueryObject();

    @RequiredPermission("客户列表")
    @InputConfig(methodName = "input")
    public String execute()  {
        try {
            PageResult pageResult = clientService.query(qo);
            putContext("pageResult",pageResult);
//            int i = 1/0;
        } catch (Exception e) {
            e.printStackTrace();
            addActionError(e.getMessage());
        }
        return LIST;
    }

    @RequiredPermission("客户编辑")
    public String input() {
        try {
            if (client.getId()!=null){
                client = clientService.get(client.getId());
            }
            int i = 1/0;
        } catch (Exception e) {
            e.printStackTrace();
            addActionError(e.getMessage());
        }
        return INPUT;
    }

    @RequiredPermission("客户删除")
    public String delete() {
        try {
            clientService.delete(client.getId());
            putMsg("删除成功");
        } catch (Exception e) {
            e.printStackTrace();
            putMsg("删除失败:"+e.getMessage());
        }
        return NONE;
    }

    @RequiredPermission("客户更新")
    public String saveOrUpdate() {
        try {
            if (client.getId()!=null){
                clientService.update(client);
                addActionMessage("更新成功");
            }else {
                clientService.save(client);
                addActionMessage("保存成功");
            }
        } catch (Exception e) {
            e.printStackTrace();
            addActionError("更新失败:"+e.getMessage());
        }
        return SUCCESS;
    }
}
