package com.company.resource.admin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

import com.company.resource.service.ICompanyEmployeeTypeService;
import com.resource.common.controller.AdminController;
import com.resource.common.routes.AdminPath;

@Controller
public class EmployeeTypeController extends AdminController {
	
	@Autowired
	ICompanyEmployeeTypeService service;
	
	@GetMapping(value = "/company/employee_types")
	public String list(ModelMap map) {
		map.addAttribute("employeeTypes", service.list());
		return AdminPath.employee_type_list.partial();
	}

}
