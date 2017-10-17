package com._520it.wms.domain;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by Administrator on 2017/9/22.
 */
@Getter
@Setter
public class Client extends BaseDomain {
    private String name;
    private String sn;
    private String phone;

    @Override
    public String toString() {
        return "Client{" +
                "name='" + name + '\'' +
                ", sn='" + sn + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }
}
