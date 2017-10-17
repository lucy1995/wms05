package com._520it.wms.domain;

import com._520it.wms.util.FileUploadUtil;
import com.alibaba.fastjson.JSON;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

//商品管理
@Getter
@Setter
public class Product extends BaseDomain{
    private String name;
    private String sn;
    //采购价格
    private BigDecimal costPrice;
    //销售价格 指导价格
    private BigDecimal salePrice;
    private String imagePath;
    private String intro;
    //品牌多对一
    private Brand brand;

    @Override
    public String toString() {
        return "Product{" +
                "name='" + name + '\'' +
                ", sn='" + sn + '\'' +
                ", costPrice=" + costPrice +
                ", salePrice=" + salePrice +
                ", imagePath='" + imagePath + '\'' +
                ", intro='" + intro + '\'' +
                ", brand=" + brand +
                '}';
    }

    public String getSmallImagePath(){
        if (StringUtils.isEmpty(imagePath)){
            return null;
        }
        int index = imagePath.lastIndexOf(".");
        return imagePath.substring(0,index)+"_small"+imagePath.substring(index);
    }

    public String getProductJson(){
        Map<String,Object> map = new HashMap<>();
        map.put("pId",getId());
        map.put("name",name);
        map.put("costPrice",costPrice);
        map.put("salePrice",salePrice);
        map.put("brandName",brand==null?"":brand.getName());
        return JSON.toJSONString(map);
    }
}
