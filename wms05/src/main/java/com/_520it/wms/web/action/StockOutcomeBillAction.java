package com._520it.wms.web.action;

import com._520it.wms.domain.RequiredPermission;
import com._520it.wms.domain.StockOutcomeBill;
import com._520it.wms.domain.StockOutcomeBillItem;
import com._520it.wms.page.PageResult;
import com._520it.wms.query.QueryObject;
import com._520it.wms.query.StockOutcomeBillQueryObject;
import com._520it.wms.service.IClientService;
import com._520it.wms.service.IDepotService;
import com._520it.wms.service.IStockOutcomeBillService;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import java.util.List;

/**
 * Created by Administrator on 2017/9/30.
 */
@Controller
@Scope("prototype")
public class StockOutcomeBillAction extends BaseAction {
    @Autowired
    private IStockOutcomeBillService stockOutcomeBillService;
    @Autowired
    private IDepotService depotService;
    @Autowired
    private IClientService clientService;
    @Getter
    private StockOutcomeBill stockOutcomeBill = new StockOutcomeBill();
    @Getter
    private StockOutcomeBillQueryObject qo = new StockOutcomeBillQueryObject();

    @RequiredPermission("出库单列表")
    public String execute(){
        try {
            PageResult pageResult = stockOutcomeBillService.query(qo);
            putContext("pageResult",pageResult);
            System.out.println(1);
        } catch (Exception e) {
            e.printStackTrace();
            addActionError(e.getMessage());
        }
        return LIST;
    }
    @RequiredPermission("出库单编辑")
    public String input(){
        try {
            putContext("depots",depotService.listAll());
            putContext("clients",clientService.listAll());
            if (stockOutcomeBill.getId()!=null){
                stockOutcomeBill = stockOutcomeBillService.selectByPrimaryKey(stockOutcomeBill.getId());
                List<StockOutcomeBillItem> items = stockOutcomeBill.getItems();
                putContext("items",items);
            }
        } catch (Exception e) {
            e.printStackTrace();
            addActionError(e.getMessage());
        }
        return INPUT;
    }

    public String show(){
        try {
            stockOutcomeBill = stockOutcomeBillService.selectByPrimaryKey(stockOutcomeBill.getId());
            List<StockOutcomeBillItem> items = stockOutcomeBill.getItems();
            putContext("items",items);
        } catch (Exception e) {
            e.printStackTrace();
            addActionError(e.getMessage());
        }
        return "show";
    }

    @RequiredPermission("出库单保存或修改")
    public String saveOrUpdate(){
        if (stockOutcomeBill.getId()!=null){
            stockOutcomeBillService.updateByPrimaryKey(stockOutcomeBill);
            addActionMessage("修改成功");
        }else {
            stockOutcomeBillService.insert(stockOutcomeBill);
            addActionMessage("保存成功");
        }
        return  SUCCESS;
    }

    @RequiredPermission("出库单删除")
    public void delete(){
        try {
            if (stockOutcomeBill.getId()!=null){
                stockOutcomeBillService.deleteByPrimaryKey(stockOutcomeBill.getId());
                putMsg("删除成功");
            }
        } catch (Exception e) {
            e.printStackTrace();
            putMsg("删除失败:"+e.getMessage());
        }
    }
    @RequiredPermission("出库单审核")
    public void audit(){
        try {
            stockOutcomeBillService.audit(stockOutcomeBill.getId());
            putMsg("审核成功");
        } catch (Exception e) {
            e.printStackTrace();
            putMsg("审核失败:"+e.getMessage());
        }
    }
}
