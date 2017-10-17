package com._520it.wms.page;

import java.util.Collections;
import java.util.List;

import lombok.Getter;
import lombok.ToString;
@Getter
@ToString
public class PageResult {
	private Integer currentPage;
	private Integer pageSize;
	private Integer totalCount;
	private Integer prevPage;
	private Integer nextPage;
	private Integer totalPage;
	private List<?> listData;
	public PageResult() {	}
	public PageResult(Integer currentPage, Integer pageSize, Integer totalCount, List<?> listData) {
		super();
		this.currentPage = currentPage;
		this.pageSize = pageSize;
		this.totalCount = totalCount;
		this.listData = listData;
		
		totalPage = totalCount % pageSize ==0 ?totalCount /pageSize :totalCount/pageSize +1;
		prevPage = currentPage -1 >=1 ? currentPage -1 :1;
		nextPage = currentPage+1 <= totalPage ? currentPage+1 :totalPage;
	}
	
	public static PageResult emptyResult(Integer pageSize){
		return new PageResult(1, pageSize, 0, Collections.EMPTY_LIST);
	}
}