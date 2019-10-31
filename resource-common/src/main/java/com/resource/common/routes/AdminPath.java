package com.resource.common.routes;

public enum AdminPath {

	employee_list("/admin/employees"), 
	employee_add("/admin/employee/new"), 
	employee_show("/admin/employee/{id}"),
	employee_delete("/admin/employee/{id}/delete"),
	employee_profile("/admin/employee/{id}/profile"),
	
	employee_personal_show("/admin/employee/{id}/personal/show"),
	employee_personal_edit("/admin/employee/{id}/personal/edit"),
	
	employee_address_show("/admin/employee/{id}/address/show"),
	employee_address_edit("/admin/employee/{id}/address/{addressId}/edit"),
	
	employee_dependent_show("/admin/employee/{id}/dependent/show"),
	employee_dependent_edit("/admin/employee/{id}/dependent/{dependentId}/edit"),
	
	employee_contact_show("/admin/employee/{id}/contact/show"),
	employee_contact_edit("/admin/employee/{id}/contact/{contactId}/edit"),
	
	employee_employment_show("/admin/employee/{id}/employment/show"),
	employee_employment_edit("/admin/employee/{id}/employment/{relationshipId}/edit"),

	company_list("/admin/companies"), 
	company_add("/admin/company/new"),
	company_search("/admin/company/search"),
	company_edit("/admin/company/{id}/edit"),
	company_delete("/admin/company/{id}/delete"),
	
	employee_type_list("/admin/company/employee_types/list"),
	employee_type_add("/admin/company/employee_types/new"),
	employee_type_hr("/admin/company/employee_types/{id}/hr_settings"),
	employee_type_work_time("/admin/company/employee_types/{id}/work_time_settings"),
	employee_type_employees("/admin/company/employee_types/employees"),
	employee_type_work_time_edit("/admin/company/employee_types/{id}/work_time_settings/edit"),
	employee_type_hr_edit("/admin/company/employee_types/{id}/hr_settings/edit"),
	
	team_list("/admin/company/teams");
	
	

	AdminPath(String path) {
		this.path = path;
	}

	private String path;

	private static final String URL_REGEX = "/\\{(.*?)\\}";

	public String path() {
		return path.replaceAll("show", "")
				   .replaceAll("list", "");
	}

	public String url() {
		return path.replaceFirst(URL_REGEX, "").replaceAll("show", "");
	}
	
	public String partial() {
		return path.replaceAll(URL_REGEX, "")
				   .substring(1);
	}

	public String redirect() {
		return "redirect:" + path
				.replaceAll("show", "")
				.replaceAll("list", "");
	}

}