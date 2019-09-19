package com.resource.common.constants;

public class EmployeesPaths {

	private static EmployeesPaths instance;

	private EmployeesPaths() {}

	public static EmployeesPaths getInstance() {
		if (instance == null) {
			synchronized (EmployeesPaths.class) {
				if (instance == null) {
					instance = new EmployeesPaths();// instance will be created at request time
				}
			}
		}
		return instance;
	}

	public String list() {
		return "/admin/employees";
	}

	public String add() {
		return "/admin/employee/new";
	}
	
	public String edit(Integer id) {
		return "/admin/employee/" + id + "/edit";
	}
	
	public String delete(Integer id) {
		return "/admin/employee/" + id + "/delete";
	}
}
