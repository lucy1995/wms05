package com._520it.wms.service.impl;

import com._520it.wms.domain.Depot;
import com._520it.wms.domain.ProductStock;
import com._520it.wms.domain.StockIncomeBill;
import com._520it.wms.domain.StockIncomeBillItem;
import com._520it.wms.mapper.StockIncomeBillMapper;
import com._520it.wms.page.PageResult;
import com._520it.wms.query.QueryObject;
import com._520it.wms.service.IProductStockService;
import com._520it.wms.service.IStockIncomeBillService;
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
public class StockIncomeBillServiceImpl implements IStockIncomeBillService {
    @Autowired
    private StockIncomeBillMapper stockIncomeBillMapper;
    @Autowired
    private IProductStockService productStockService;

    @Override
    public int insert(StockIncomeBill stockIncomeBill) {
        stockIncomeBill.setStatus(StockIncomeBill.NORMAL);
        stockIncomeBill.setInputUser(UserContext.getEmployeeInSession());
        stockIncomeBill.setInputTime(new Date());
        BigDecimal totalNumber = BigDecimal.ZERO;
        BigDecimal totalAmount = BigDecimal.ZERO;
        List<StockIncomeBillItem> items = stockIncomeBill.getItems();
        for (StockIncomeBillItem item : items) {
            BigDecimal number = item.getNumber();
            BigDecimal costPrice = item.getCostPrice();
            BigDecimal amount = number.multiply(costPrice).setScale(2,BigDecimal.ROUND_HALF_UP);
            item.setAmount(amount);

            totalNumber = totalNumber.add(number);
            totalAmount = totalAmount.add(amount);
        }
        stockIncomeBill.setTotalNumber(totalNumber);
        stockIncomeBill.setTotalAmount(totalAmount);
        stockIncomeBillMapper.insert(stockIncomeBill);
        for (StockIncomeBillItem item : items) {
            item.setBill(stockIncomeBill);
            stockIncomeBillMapper.insertItem(item);
        }
        return 0;
    }

    @Override
    public int deleteByPrimaryKey(Long id) {
        StockIncomeBill stockIncomeBill = stockIncomeBillMapper.selectByPrimaryKey(id);
        if (stockIncomeBill.getStatus()==StockIncomeBill.AUDIT){
            throw new RuntimeException("已审核的订单不能被删除");
        }
        stockIncomeBillMapper.deleteItemByBillId(id);
        stockIncomeBillMapper.deleteByPrimaryKey(id);
        return 0;
    }

    @Override
    public int updateByPrimaryKey(StockIncomeBill stockIncomeBill) {
        if (stockIncomeBill.getStatus()==StockIncomeBill.AUDIT){
            throw new RuntimeException("已审核的订单无法修改");
        }
        stockIncomeBillMapper.deleteItemByBillId(stockIncomeBill.getId());
        BigDecimal totalNumber = BigDecimal.ZERO;
        BigDecimal totalAmount = BigDecimal.ZERO;
        List<StockIncomeBillItem> items = stockIncomeBill.getItems();
        for (StockIncomeBillItem item : items) {
            BigDecimal number = item.getNumber();
            BigDecimal costPrice = item.getCostPrice();
            BigDecimal amount = number.multiply(costPrice).setScale(2,BigDecimal.ROUND_HALF_UP);
            item.setAmount(amount);
            item.setBill(stockIncomeBill);
            stockIncomeBillMapper.insertItem(item);

            totalNumber = totalNumber.add(number);
            totalAmount = totalAmount.add(amount);
        }
        stockIncomeBillMapper.updateByPrimaryKey(stockIncomeBill);
        return 0;
    }

    @Override
    public StockIncomeBill selectByPrimaryKey(Long id) {
        return stockIncomeBillMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<StockIncomeBill> selectAll() {
        return stockIncomeBillMapper.selectAll();
    }

    @Override
    public PageResult query(QueryObject qo) {
        int count = stockIncomeBillMapper.query4count(qo);
        if (count==0) {return PageResult.emptyResult(qo.getPageSize());}
        List<StockIncomeBill> listData = stockIncomeBillMapper.query4list(qo);
        return new PageResult(qo.getCurrentPage(),qo.getPageSize(),count,listData);
    }

    @Override
    public void audit(Long bill_id) {
        StockIncomeBill stockIncomeBill = stockIncomeBillMapper.selectByPrimaryKey(bill_id);
        stockIncomeBill.setAuditTime(new Date());
        stockIncomeBill.setAuditor(UserContext.getEmployeeInSession());
        stockIncomeBill.setStatus(StockIncomeBill.AUDIT);
        List<StockIncomeBillItem> items = stockIncomeBill.getItems();
        Depot depot = stockIncomeBill.getDepot();
        for (StockIncomeBillItem item : items) {
            productStockService.income(depot,item);
        }
        stockIncomeBillMapper.audit(stockIncomeBill);
    }
}
