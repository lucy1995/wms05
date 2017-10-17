package com._520it.wms.domain;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by Administrator on 2017/9/19.
 */
@Getter
@Setter
public class Supplier extends BaseDomain {
    private String name;
    private String phone;
    private String address;

    @Override
    public String toString() {
        return "Supplier{" +
                "name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
