package com._520it.wms.domain;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

/**
 * Created by Administrator on 2017/9/19.
 */
//订单明细
@Getter
@Setter
//采购入库单明细
public class StockIncomeBillItem extends BaseDomain {
    //采购成本价格")
    private BigDecimal costPrice;
    //采购数量")
    private BigDecimal number;
    //金额小计")
    private BigDecimal amount;
    //备注")
    private String remark;
    //商品")
    private Product product;
    //采购rukudan ")
    //一个订单多个订单明细,一个订单明细属于一个订单
    private StockIncomeBill bill;

    @Override
    public String toString() {
        return "StockIncomeBillItem{" +
                "costPrice=" + costPrice +
                ", totalNumber=" + number +
                ", totalAmount=" + amount +
                ", remark='" + remark + '\'' +
                ", product=" + product +
//                ", bill=" + bill +
                '}';
    }
}
