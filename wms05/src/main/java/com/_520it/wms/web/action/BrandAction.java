package com._520it.wms.web.action;

import com._520it.wms.domain.Brand;
import com._520it.wms.domain.RequiredPermission;
import com._520it.wms.page.PageResult;
import com._520it.wms.query.QueryObject;
import com._520it.wms.service.IBrandService;
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
public class BrandAction extends BaseAction {

    @Autowired
    private IBrandService brandService;
    @Getter
    Brand brand = new Brand();
    @Getter
    QueryObject qo = new QueryObject();

    @RequiredPermission("品牌列表")
    @InputConfig(methodName = "input")
    public String execute()  {
        try {
            PageResult pageResult = brandService.query(qo);
            putContext("pageResult",pageResult);
//            int i = 1/0;
        } catch (Exception e) {
            e.printStackTrace();
            addActionError(e.getMessage());
        }
        return LIST;
    }

    @RequiredPermission("品牌编辑")
    public String input() {
        try {
            if (brand.getId()!=null){
                brand = brandService.get(brand.getId());
            }
            int i = 1/0;
        } catch (Exception e) {
            e.printStackTrace();
            addActionError(e.getMessage());
        }
        return INPUT;
    }

    @RequiredPermission("品牌删除")
    public String delete() {
        try {
            brandService.delete(brand.getId());
            putMsg("删除成功");
        } catch (Exception e) {
            e.printStackTrace();
            putMsg("删除失败:"+e.getMessage());
        }
        return NONE;
    }

    @RequiredPermission("品牌更新")
    public String saveOrUpdate() {
        try {
            if (brand.getId()!=null){
                brandService.update(brand);
                addActionMessage("更新成功");
            }else {
                brandService.save(brand);
                addActionMessage("保存成功");
            }
        } catch (Exception e) {
            e.printStackTrace();
            addActionError("更新失败:"+e.getMessage());
        }
        return SUCCESS;
    }
}
