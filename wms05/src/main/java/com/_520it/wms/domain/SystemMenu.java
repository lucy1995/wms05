package com._520it.wms.domain;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/9/16.
 */
@Getter
@Setter
public class SystemMenu extends  BaseDomain {

    private String name;
    private String url;
    private String sn;
    private SystemMenu parent;

    public String getParentName(){
        if (parent==null){
            return "根目录";
        }
        return parent.getName();
    }
}
