package com._520it.wms.domain;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by Administrator on 2017/9/25.
 */
//销售帐
@Getter
@Setter
public class SaleAccount extends BaseDomain {
    private Date vdate;
    //销售日期
    private BigDecimal number;
    //销售数量
    private BigDecimal costPrice;
    private BigDecimal costAmount;
    private BigDecimal salePrice;
    private BigDecimal saleAmount;
    //产品
    private Product product;
    //销售人员
    private Employee saleman;
    //客户
    private Client client;

    @Override
    public String toString() {
        return "SaleAccount{" +
                "vdate=" + vdate +
                ", number=" + number +
                ", costPrice=" + costPrice +
                ", costAmount=" + costAmount +
                ", salePrice=" + salePrice +
                ", saleAmount=" + saleAmount +
                ", product=" + product +
                ", saleman=" + saleman +
                ", client=" + client +
                '}';
    }
}
