package com._520it.wms.domain;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
public class Employee extends BaseDomain {
	private String name;
	private String password;
	private String email;
	private Integer age;
	private boolean admin;
	private Department dept;
	private List<Role> roles = new ArrayList<>();

	@Override
	public String toString() {
		return "Employee{" +
				"name='" + name + '\'' +
				", password='" + password + '\'' +
				", email='" + email + '\'' +
				", age=" + age +
				", admin=" + admin +
				'}';
	}
}
