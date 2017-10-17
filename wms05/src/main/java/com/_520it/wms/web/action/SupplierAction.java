package com._520it.wms.web.action;

import com._520it.wms.domain.Supplier;
import com._520it.wms.domain.RequiredPermission;
import com._520it.wms.page.PageResult;
import com._520it.wms.query.QueryObject;
import com._520it.wms.service.ISupplierService;
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
public class SupplierAction extends BaseAction {

    @Autowired
    private ISupplierService supplierService;
    @Getter
    Supplier supplier = new Supplier();
    @Getter
    QueryObject qo = new QueryObject();

    @RequiredPermission("供应商列表")
    @InputConfig(methodName = "input")
    public String execute()  {
        try {
            PageResult pageResult = supplierService.query(qo);
            putContext("pageResult",pageResult);
        } catch (Exception e) {
            e.printStackTrace();
            addActionError(e.getMessage());
        }
        return LIST;
    }

    @RequiredPermission("供应商编辑")
    public String input() {
        try {
            if (supplier.getId()!=null){
                supplier = supplierService.get(supplier.getId());
            }
            int i = 1/0;
        } catch (Exception e) {
            e.printStackTrace();
            addActionError(e.getMessage());
        }
        return INPUT;
    }

    @RequiredPermission("供应商删除")
    public String delete() {
        try {
            supplierService.delete(supplier.getId());
            putMsg("删除成功");
        } catch (Exception e) {
            e.printStackTrace();
            putMsg("删除失败:"+e.getMessage());
        }
        return NONE;
    }

    @RequiredPermission("供应商更新")
    public String saveOrUpdate() {
        try {
            if (supplier.getId()!=null){
                supplierService.update(supplier);
                addActionMessage("更新成功");
            }else {
                supplierService.save(supplier);
                addActionMessage("保存成功");
            }
        } catch (Exception e) {
            e.printStackTrace();
            addActionError("更新失败:"+e.getMessage());
        }
        return SUCCESS;
    }
}
