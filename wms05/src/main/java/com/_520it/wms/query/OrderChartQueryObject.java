package com._520it.wms.query;

import com._520it.wms.util.DateUtil;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class OrderChartQueryObject extends QueryObject {
	private String groupType = "e.name";
	private String keyword;
	private Date beginDate;
	private Date endDate;
	private Long supplier_id =-1L;
	private Long brand_id = -1L;

	public Date getBeginDate() {
		return DateUtil.getBeginDate(beginDate);
	}

	public Date getEndDate() {
		return DateUtil.getEndDate(endDate);
	}
}
