package com._520it.wms.domain;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

/**
 * Created by Administrator on 2017/9/19.
 */
//采购订单明细
@Getter
@Setter
public class OrderBillItem extends BaseDomain {
    //采购成本价格
    private BigDecimal costPrice;
    //采购数量
    private BigDecimal number;
    //金额小计
    private BigDecimal amount;
    //备注
    private String remark;
    //商品
    private Product product;
    //采购订单
    //一个订单多个订单明细,一个订单明细属于一个订单
    private OrderBill bill;

    @Override
    public String toString() {
        return "OrderBillItem{" +
                "costPrice=" + costPrice +
                ", number=" + number +
                ", amount=" + amount +
                ", remark='" + remark + '\'' +
                ", product=" + product +
                ", bill=" + bill +
                '}';
    }
}
