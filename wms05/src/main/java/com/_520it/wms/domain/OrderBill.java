package com._520it.wms.domain;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2017/9/19.
 */
//采购订单
@Getter
@Setter
public class OrderBill extends BaseDomain {
    public static  final int NORMAL=0; //未审核状态
    public static  final int AUDIT=1;
    //订单编码
    private String sn;
    //业务时间
    private Date vdate;
    //供应商
    private Supplier supplier;
    //采购总数量
    private BigDecimal totalNumber;
    //采购总金额
    private BigDecimal totalAmount;
    //录入人
    private Employee inputUser;
    //录入日期
    private Date inputTime;
    //审核人
    private Employee auditor;
    //审核日期
    private Date auditTime;
    //审核状态
    private Integer status=OrderBill.NORMAL;//0正常,1审核
    //订单明细列表
    private List<OrderBillItem> items = new ArrayList<>();

    @Override
    public String toString() {
        return "OrderBill{" +
                "sn='" + sn + '\'' +
                ", vdate=" + vdate +
                ", supplier=" + supplier +
                ", totalNum=" + totalNumber +
                ", totalAmount=" + totalAmount +
                ", inputUser=" + inputUser +
                ", inputTime=" + inputTime +
                ", auditor=" + auditor +
                ", auditTime=" + auditTime +
                ", status=" + status +
                ", items=" + items +
                '}';
    }
}
