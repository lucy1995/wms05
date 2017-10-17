package com._520it.wms.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
public class Permission extends BaseDomain {
	private String name;
	private String expression;

	@Override
	public String toString() {
		return "Permission{" +
				"name='" + name + '\'' +
				", expression='" + expression + '\'' +
				'}';
	}
}
