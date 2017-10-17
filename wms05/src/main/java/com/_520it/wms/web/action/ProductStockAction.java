package com._520it.wms.web.action;

import com._520it.wms.domain.ProductStock;
import com._520it.wms.domain.RequiredPermission;
import com._520it.wms.page.PageResult;
import com._520it.wms.query.ProductStockQueryObject;
import com._520it.wms.service.IBrandService;
import com._520it.wms.service.IDepotService;
import com._520it.wms.service.IProductStockService;
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
public class ProductStockAction extends BaseAction {

    @Autowired
    private IProductStockService productStockService;
    @Autowired
    private IDepotService depotService;
    @Autowired
    private IBrandService brandService;
    @Getter
    ProductStock productStock = new ProductStock();
    @Getter
    ProductStockQueryObject qo = new ProductStockQueryObject();

    @RequiredPermission("库存列表")
    @InputConfig(methodName = "input")
    public String execute()  {
        try {
            PageResult pageResult = productStockService.query(qo);
            putContext("pageResult",pageResult);
            System.out.println(1);
            putContext("depots",depotService.listAll());
            putContext("brands",brandService.listAll());
        } catch (Exception e) {
            e.printStackTrace();
            addActionError(e.getMessage());
        }
        return LIST;
    }
}
