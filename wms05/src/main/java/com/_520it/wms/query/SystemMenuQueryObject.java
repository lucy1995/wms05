package com._520it.wms.query;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SystemMenuQueryObject extends  QueryObject {
    private  Long parentId = -1L;
    private  String parentSn ;
}
