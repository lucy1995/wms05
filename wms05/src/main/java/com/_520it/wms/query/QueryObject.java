package com._520it.wms.query;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class QueryObject {
	public Integer currentPage= 1;
	public Integer pageSize=5;
	public Integer getBeginIndex(){
		return (currentPage-1) *pageSize;
	}
}
