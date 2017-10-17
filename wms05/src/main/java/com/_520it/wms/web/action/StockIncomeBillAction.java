package com._520it.wms.web.action;

import com._520it.wms.domain.RequiredPermission;
import com._520it.wms.domain.StockIncomeBill;
import com._520it.wms.domain.StockIncomeBillItem;
import com._520it.wms.page.PageResult;
import com._520it.wms.query.QueryObject;
import com._520it.wms.service.IDepotService;
import com._520it.wms.service.IStockIncomeBillService;
import com._520it.wms.service.ISupplierService;
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
public class StockIncomeBillAction extends BaseAction {
    @Autowired
    private IStockIncomeBillService stockIncomeBillService;
    @Autowired
    private IDepotService depotService;
    @Getter
    private StockIncomeBill stockIncomeBill = new StockIncomeBill();
    @Getter
    private QueryObject qo = new QueryObject();

    @RequiredPermission("订单列表")
    public String execute(){
        try {
            PageResult pageResult = stockIncomeBillService.query(qo);
            putContext("pageResult",pageResult);
            System.out.println(1);
        } catch (Exception e) {
            e.printStackTrace();
            addActionError(e.getMessage());
        }
        return LIST;
    }
    @RequiredPermission("订单编辑")
    public String input(){
        try {
            putContext("depots",depotService.listAll());
            if (stockIncomeBill.getId()!=null){
                stockIncomeBill = stockIncomeBillService.selectByPrimaryKey(stockIncomeBill.getId());
                List<StockIncomeBillItem> items = stockIncomeBill.getItems();
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
            stockIncomeBill = stockIncomeBillService.selectByPrimaryKey(stockIncomeBill.getId());
            List<StockIncomeBillItem> items = stockIncomeBill.getItems();
            putContext("items",items);
        } catch (Exception e) {
            e.printStackTrace();
            addActionError(e.getMessage());
        }
        return "show";
    }

    @RequiredPermission("订单保存或修改")
    public String saveOrUpdate(){
        if (stockIncomeBill.getId()!=null){
            stockIncomeBillService.updateByPrimaryKey(stockIncomeBill);
            addActionMessage("修改成功");
        }else {
            stockIncomeBillService.insert(stockIncomeBill);
            addActionMessage("保存成功");
        }
        return  SUCCESS;
    }

    @RequiredPermission("订单删除")
    public void delete(){
        try {
            if (stockIncomeBill.getId()!=null){
                stockIncomeBillService.deleteByPrimaryKey(stockIncomeBill.getId());
                putMsg("删除成功");
            }
        } catch (Exception e) {
            e.printStackTrace();
            putMsg("删除失败:"+e.getMessage());
        }
    }
    @RequiredPermission("订单审核")
    public void audit(){
        try {
            stockIncomeBillService.audit(stockIncomeBill.getId());
            putMsg("审核成功");
        } catch (Exception e) {
            e.printStackTrace();
            putMsg("审核失败:"+e.getMessage());
        }
    }
}
