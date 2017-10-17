package com._520it.wms.query;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmployeeQueryObject extends QueryObject {
	private String keyword;
	private Long dept_id =-1L;
}
