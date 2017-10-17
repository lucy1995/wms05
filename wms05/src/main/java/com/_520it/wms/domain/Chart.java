package com._520it.wms.domain;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

/**
 * Created by Administrator on 2017/10/7.
 */
@Setter
@Getter
public class Chart extends BaseDomain {
    private String groupType;
    //采购总数量
    private BigDecimal costNumber;
    //采购总金额
    private BigDecimal costAmount;
    //销售总数量
    private BigDecimal saleNumber;
    //销售总金额
    private BigDecimal saleAmount;

    //毛利润
    public BigDecimal getGrossPrift(){
        return saleAmount.subtract(costAmount);
    }

    @Override
    public String toString() {
        return "Chart{" +
                "groupType='" + groupType + '\'' +
                ", costNumber=" + costNumber +
                ", costAmount=" + costAmount +
                ", saleNumber=" + saleNumber +
                ", saleAmount=" + saleAmount +
                '}';
    }
}
