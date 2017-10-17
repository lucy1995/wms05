package com._520it.wms.service.impl;

import com._520it.wms.domain.OrderBill;
import com._520it.wms.domain.OrderBillItem;
import com._520it.wms.mapper.OrderBillMapper;
import com._520it.wms.page.PageResult;
import com._520it.wms.query.OrderBillQueryObject;
import com._520it.wms.query.QueryObject;
import com._520it.wms.service.IOrderBillService;
import com._520it.wms.util.UserContext;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2017/9/30.
 */
@Service
public class OrderBillServiceImpl implements IOrderBillService {
    @Autowired
    private OrderBillMapper orderBillMapper;

    @Override
    public int insert(OrderBill orderBill) {
        orderBill.setStatus(OrderBill.NORMAL);
        orderBill.setInputUser(UserContext.getEmployeeInSession());
        orderBill.setInputTime(new Date());
        BigDecimal totalNumber = BigDecimal.ZERO;
        BigDecimal totalAmount = BigDecimal.ZERO;
        List<OrderBillItem> items = orderBill.getItems();
        for (OrderBillItem item : items) {
            BigDecimal number = item.getNumber();
            BigDecimal costPrice = item.getCostPrice();
            BigDecimal amount = number.multiply(costPrice).setScale(2,BigDecimal.ROUND_HALF_UP);
            item.setAmount(amount);

            totalNumber = totalNumber.add(number);
            totalAmount = totalAmount.add(amount);
        }
        orderBill.setTotalNumber(totalNumber);
        orderBill.setTotalAmount(totalAmount);
        orderBillMapper.insert(orderBill);
        for (OrderBillItem item : items) {
            item.setBill(orderBill);
            orderBillMapper.insertItem(item);
        }
        return 0;
    }

    @Override
    public int deleteByPrimaryKey(Long id) {
        OrderBill orderBill = orderBillMapper.selectByPrimaryKey(id);
        if (orderBill.getStatus()==OrderBill.AUDIT){
            throw new RuntimeException("已审核的订单不能被删除");
        }
        orderBillMapper.deleteItemByBillId(id);
        orderBillMapper.deleteByPrimaryKey(id);
        return 0;
    }

    @Override
    public int updateByPrimaryKey(OrderBill orderBill) {
        if (orderBill.getStatus()==OrderBill.AUDIT){
            throw new RuntimeException("已审核的订单无法修改");
        }
        orderBillMapper.deleteItemByBillId(orderBill.getId());
        BigDecimal totalNumber = BigDecimal.ZERO;
        BigDecimal totalAmount = BigDecimal.ZERO;
        List<OrderBillItem> items = orderBill.getItems();
        for (OrderBillItem item : items) {
            BigDecimal number = item.getNumber();
            BigDecimal costPrice = item.getCostPrice();
            BigDecimal amount = number.multiply(costPrice).setScale(2,BigDecimal.ROUND_HALF_UP);
            item.setAmount(amount);
            item.setBill(orderBill);
            orderBillMapper.insertItem(item);

            totalNumber = totalNumber.add(number);
            totalAmount = totalAmount.add(amount);
        }
        orderBillMapper.updateByPrimaryKey(orderBill);
        return 0;
    }

    @Override
    public OrderBill selectByPrimaryKey(Long id) {
        return orderBillMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<OrderBill> selectAll() {
        return orderBillMapper.selectAll();
    }

    @Override
    public PageResult query(OrderBillQueryObject qo) {
        int count = orderBillMapper.query4count(qo);
        if (count==0) {return PageResult.emptyResult(qo.getPageSize());}
        List<OrderBill> listData = orderBillMapper.query4list(qo);
        return new PageResult(qo.getCurrentPage(),qo.getPageSize(),count,listData);
    }

    @Override
    public void audit(Long bill_id) {
        OrderBill orderBill = new OrderBill();
        orderBill.setId(bill_id);
        orderBill.setAuditTime(new Date());
        orderBill.setAuditor(UserContext.getEmployeeInSession());
        orderBill.setStatus(OrderBill.AUDIT);
        orderBillMapper.audit(orderBill);
    }
}
