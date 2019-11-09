package com.employee.resource.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.employee.resource.service.IEmployeeService;
import com.resource.common.controller.AdminController;
import com.resource.common.model.Employee;
import com.resource.common.model.Profile;
import com.resource.common.routes.AdminPath;

@Controller
public class TimeSheetController extends AdminController{

	@Autowired
	private IEmployeeService employeeService;
	
	@GetMapping(value = "/employee/{id}/timesheet")
	public String show(ModelMap map, @PathVariable("id") String code) {
		Employee employee = employeeService.findByCode(code);
		map.addAttribute("employee", employee);
		
		map.addAttribute("employeeProfile", employee.getProfile());
		if (employee.getProfile() == null)
			map.addAttribute("employeeProfile", new Profile());
		
		return AdminPath.employee_timesheet.partial();
	}
}
