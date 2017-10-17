package com._520it.wms.service.impl;

import com._520it.wms.domain.*;
import com._520it.wms.mapper.StockOutcomeBillMapper;
import com._520it.wms.page.PageResult;
import com._520it.wms.query.StockOutcomeBillQueryObject;
import com._520it.wms.service.IProductStockService;
import com._520it.wms.service.ISaleAccountService;
import com._520it.wms.service.IStockOutcomeBillService;
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
public class StockOutcomeBillServiceImpl implements IStockOutcomeBillService {
    @Autowired
    private StockOutcomeBillMapper stockOutcomeBillMapper;
    @Autowired
    private IProductStockService productStockService;
    @Autowired
    private ISaleAccountService saleAccountService;

    @Override
    public int insert(StockOutcomeBill stockOutcomeBill) {
        stockOutcomeBill.setStatus(StockOutcomeBill.NORMAL);
        stockOutcomeBill.setInputUser(UserContext.getEmployeeInSession());
        stockOutcomeBill.setInputTime(new Date());
        BigDecimal totalNumber = BigDecimal.ZERO;
        BigDecimal totalAmount = BigDecimal.ZERO;
        List<StockOutcomeBillItem> items = stockOutcomeBill.getItems();
        for (StockOutcomeBillItem item : items) {
            BigDecimal number = item.getNumber();
            BigDecimal salePrice = item.getSalePrice();
            BigDecimal amount = number.multiply(salePrice).setScale(2,BigDecimal.ROUND_HALF_UP);
            item.setAmount(amount);

            totalNumber = totalNumber.add(number);
            totalAmount = totalAmount.add(amount);
        }
        stockOutcomeBill.setTotalNumber(totalNumber);
        stockOutcomeBill.setTotalAmount(totalAmount);
        stockOutcomeBillMapper.insert(stockOutcomeBill);
        for (StockOutcomeBillItem item : items) {
            item.setBill(stockOutcomeBill);
            stockOutcomeBillMapper.insertItem(item);
        }
        return 0;
    }

    @Override
    public int deleteByPrimaryKey(Long id) {
        StockOutcomeBill stockOutcomeBill = stockOutcomeBillMapper.selectByPrimaryKey(id);
        if (stockOutcomeBill.getStatus()==StockOutcomeBill.AUDIT){
            throw new RuntimeException("已审核的订单不能被删除");
        }
        stockOutcomeBillMapper.deleteItemByBillId(id);
        stockOutcomeBillMapper.deleteByPrimaryKey(id);
        return 0;
    }

    @Override
    public int updateByPrimaryKey(StockOutcomeBill stockOutcomeBill) {
        if (stockOutcomeBill.getStatus()==StockOutcomeBill.AUDIT){
            throw new RuntimeException("已审核的订单无法修改");
        }
        stockOutcomeBillMapper.deleteItemByBillId(stockOutcomeBill.getId());
        BigDecimal totalNumber = BigDecimal.ZERO;
        BigDecimal totalAmount = BigDecimal.ZERO;
        List<StockOutcomeBillItem> items = stockOutcomeBill.getItems();
        for (StockOutcomeBillItem item : items) {
            BigDecimal number = item.getNumber();
            BigDecimal salePrice = item.getSalePrice();
            BigDecimal amount = number.multiply(salePrice).setScale(2,BigDecimal.ROUND_HALF_UP);
            item.setAmount(amount);
            item.setBill(stockOutcomeBill);
            stockOutcomeBillMapper.insertItem(item);

            totalNumber = totalNumber.add(number);
            totalAmount = totalAmount.add(amount);
        }
        stockOutcomeBillMapper.updateByPrimaryKey(stockOutcomeBill);
        return 0;
    }

    @Override
    public StockOutcomeBill selectByPrimaryKey(Long id) {
        return stockOutcomeBillMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<StockOutcomeBill> selectAll() {
        return stockOutcomeBillMapper.selectAll();
    }

    @Override
    public PageResult query(StockOutcomeBillQueryObject qo) {
        int count = stockOutcomeBillMapper.query4count(qo);
        if (count==0) {return PageResult.emptyResult(qo.getPageSize());}
        List<StockOutcomeBill> listData = stockOutcomeBillMapper.query4list(qo);
        return new PageResult(qo.getCurrentPage(),qo.getPageSize(),count,listData);
    }

    @Override
    public void audit(Long bill_id) {
        StockOutcomeBill stockOutcomeBill = stockOutcomeBillMapper.selectByPrimaryKey(bill_id);
        stockOutcomeBill.setAuditTime(new Date());
        stockOutcomeBill.setAuditor(UserContext.getEmployeeInSession());
        stockOutcomeBill.setStatus(StockOutcomeBill.AUDIT);
        List<StockOutcomeBillItem> items = stockOutcomeBill.getItems();
        Depot depot = stockOutcomeBill.getDepot();
        for (StockOutcomeBillItem item : items) {
            BigDecimal costPrice = productStockService.outcome(depot, item);
            SaleAccount sc = new SaleAccount();
            sc.setVdate(stockOutcomeBill.getVdate());
            sc.setNumber(item.getNumber());
            sc.setCostPrice(costPrice);
            sc.setCostAmount(costPrice.multiply(item.getNumber()).setScale(2,BigDecimal.ROUND_HALF_UP));
            sc.setSalePrice(item.getSalePrice());
            sc.setSaleAmount(item.getSalePrice().multiply(item.getNumber()).setScale(2,BigDecimal.ROUND_HALF_UP));
            sc.setProduct(item.getProduct());
            sc.setSaleman(UserContext.getEmployeeInSession());
            sc.setClient(stockOutcomeBill.getClient());
            saleAccountService.save(sc);
        }
        stockOutcomeBillMapper.audit(stockOutcomeBill);
    }
}
