package com._520it.wms.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Created by Administrator on 2017/9/11.
 */
@Getter
@Setter
public class Department extends  BaseDomain {
    private  String name;
    private  String sn;

    @Override
    public String toString() {
        return "Department{" +
                "name='" + name + '\'' +
                ", sn='" + sn + '\'' +
                '}';
    }
}
