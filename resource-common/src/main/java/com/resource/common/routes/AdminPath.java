package com.resource.common.routes;

public enum AdminPath {

	employee_list("/admin/employees"), 
	employee_add("/admin/employee/new"), 
	employee_edit("/admin/employee/{id}/edit"),
	employee_show("/admin/employee/{id}"),
	employee_delete("/admin/employee/{id}/delete"),
	employee_profile("/admin/employee/{id}/profile"),
	
	
	employee_personal_edit("/admin/employee/{id}/personal/edit"),
	employee_address_edit("/admin/employee/{id}/address/{addressId}/edit"),
	employee_dependent_edit("/admin/employee/{id}/dependent/{dependentId}/edit"),
	employee_contact_edit("/admin/employee/{id}/contact/{contactId}/edit"),

	company_list("/admin/companies"), 
	company_add("/admin/company/new"),
	company_search("/admin/company/search"),
	company_edit("/admin/company/{id}/edit"),
	company_delete("/admin/company/{id}/delete");

	AdminPath(String path) {
		this.path = path;
	}

	private String path;

	private static final String URL_REGEX = "/\\{(.*?)\\}";

	public String path() {
		return path;
	}

	public String partial() {
		return path.replaceAll(URL_REGEX, "").substring(1);
	}

	public String redirect() {
		return "redirect:" + path;
	}

}