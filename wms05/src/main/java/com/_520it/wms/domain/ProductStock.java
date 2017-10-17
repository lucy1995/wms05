package com._520it.wms.domain;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

/**
 * Created by Administrator on 2017/9/22.
 */
@Getter
@Setter
public class ProductStock extends BaseDomain {

    //库存价格,移动甲醛平均计算
    private BigDecimal price;
    //库存数量
    private BigDecimal storeNumber;
    //总金额
    private BigDecimal amount;
    //商品
    private Product product;
    //仓库:通过仓库+商品只能找到一条唯一
    private Depot depot;

    @Override
    public String toString() {
        return "ProductStock{" +
                "price=" + price +
                ", storeNumber=" + storeNumber +
                ", amount=" + amount +
                ", product=" + product +
                ", depot=" + depot +
                '}';
    }
}
