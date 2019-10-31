package com.employee.resource.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.employee.resource.service.IEmployeeService;
import com.resource.common.controller.AdminController;
import com.resource.common.model.Employee;
import com.resource.common.model.EmploymentRelationship;
import com.resource.common.model.Profile;
import com.resource.common.routes.AdminPath;
import com.resource.common.service.IEmployeeTypeService;
import com.resource.common.view.EnableTags;
import com.resource.common.view.SelectedTab;

@EnableTags(value = {"personal_settings", "employee_settings"}, selected = "employee_settings")
@Controller
public class EmploymentSettingsController extends AdminController {

	@Autowired
	private IEmployeeService employeeService;
	
	@Autowired
	private IEmployeeTypeService typeService;
	
	@SelectedTab("employment")
	@GetMapping(value = "/employee/{id}/employment")
	public String show(ModelMap map, @PathVariable("id") String code) {
		Employee employee = employeeService.findByCode(code);
		map.addAttribute("employee", employee);
		
		map.addAttribute("employeeProfile", employee.getProfile());
		if (employee.getProfile() == null)
			map.addAttribute("employeeProfile", new Profile());
		
		map.addAttribute("relationship", employee.getCurrentRelationship());
		map.addAttribute("employeeType", employee.getCurrentRelationship().getEmployeeType());
		return AdminPath.employee_employment_show.partial();
	}
	
	@SelectedTab("employment")
	@GetMapping(value = "/employee/{id}/employment/{relationshipId}/edit")
	public String edit(ModelMap map, @PathVariable("id") String code, @PathVariable("relationshipId") Integer relationshipId) {
		Employee employee = employeeService.findByCode(code);
		map = updateAttributes(employee, map);
		map.addAttribute("relationship", employee.getCurrentRelationship());
		return AdminPath.employee_employment_edit.partial();
	}
	
	@SelectedTab("employment")
	@PostMapping(value = "/employee/{id}/employment/{relationshipId}/edit")
	public String update(ModelMap map, @PathVariable("id") String code, @PathVariable("relationshipId") Integer relationshipId, @Valid @ModelAttribute("relationship") EmploymentRelationship relationship, BindingResult bindingResult) {
		Employee employee = employeeService.findByCode(code);
		if(bindingResult.hasErrors()) {
			map = updateAttributes(employee, map);
			return "admin/employee/employment/edit";
		}
		
		return AdminPath.employee_employment_show.redirect();
	}
	
	private ModelMap updateAttributes(Employee employee, ModelMap map) {
		map.addAttribute("employee", employee);
		
		map.addAttribute("employeeProfile", employee.getProfile());
		if (employee.getProfile() == null)
			map.addAttribute("employeeProfile", new Profile());
		
		map.addAttribute("employeeType", employee.getCurrentRelationship().getEmployeeType());
		map.addAttribute("employeeTypes", typeService.list());
		return map;
	}
}
