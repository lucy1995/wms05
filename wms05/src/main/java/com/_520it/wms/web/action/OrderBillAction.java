package com._520it.wms.web.action;

import com._520it.wms.domain.OrderBill;
import com._520it.wms.domain.OrderBillItem;
import com._520it.wms.domain.RequiredPermission;
import com._520it.wms.query.OrderBillQueryObject;
import com._520it.wms.query.QueryObject;
import com._520it.wms.service.IOrderBillService;
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
public class OrderBillAction extends BaseAction {
    @Autowired
    private IOrderBillService orderBillService;
    @Autowired
    private ISupplierService supplierService;
    @Getter
    private OrderBill orderBill = new OrderBill();
    @Getter
    private OrderBillQueryObject qo = new OrderBillQueryObject();

    @RequiredPermission("订单列表")
    public String execute(){
        try {
            putContext("pageResult",orderBillService.query(qo));
            putContext("suppliers",supplierService.listAll());
        } catch (Exception e) {
            e.printStackTrace();
            addActionError(e.getMessage());
        }
        return LIST;
    }
    @RequiredPermission("订单编辑")
    public String input(){
        try {
            putContext("suppliers",supplierService.listAll());
            if (orderBill.getId()!=null){
                orderBill = orderBillService.selectByPrimaryKey(orderBill.getId());
                List<OrderBillItem> items = orderBill.getItems();
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
            orderBill = orderBillService.selectByPrimaryKey(orderBill.getId());
            List<OrderBillItem> items = orderBill.getItems();
            putContext("items",items);
        } catch (Exception e) {
            e.printStackTrace();
            addActionError(e.getMessage());
        }
        return "show";
    }

    @RequiredPermission("订单保存或修改")
    public String saveOrUpdate(){
        if (orderBill.getId()!=null){
            orderBillService.updateByPrimaryKey(orderBill);
            addActionMessage("修改成功");
        }else {
            orderBillService.insert(orderBill);
            addActionMessage("保存成功");
        }
        return  SUCCESS;
    }

    @RequiredPermission("订单删除")
    public void delete(){
        try {
            if (orderBill.getId()!=null){
                orderBillService.deleteByPrimaryKey(orderBill.getId());
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
            orderBillService.audit(orderBill.getId());
            putMsg("审核成功");
        } catch (Exception e) {
            e.printStackTrace();
            putMsg("审核失败:"+e.getMessage());
        }
    }
}
