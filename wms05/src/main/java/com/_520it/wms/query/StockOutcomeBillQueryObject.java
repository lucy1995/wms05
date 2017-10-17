package com._520it.wms.query;

import com._520it.wms.util.DateUtil;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
public class StockOutcomeBillQueryObject extends QueryObject {
    private Date beginDate;
    private Date endDate;
    private Long depot_id=-1L;
    private Long client_id=-1L;
    private int status=-1;

    public Date getBeginDate() {
        return DateUtil.getBeginDate(beginDate);
    }
    public Date getEndDate() {
        return DateUtil.getEndDate(endDate);
    }
}
