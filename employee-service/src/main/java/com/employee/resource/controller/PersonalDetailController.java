package com.employee.resource.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.employee.resource.service.IEmployeeService;
import com.employee.resource.service.IPersonalDetailService;
import com.resource.common.controller.AdminController;
import com.resource.common.model.Employee;
import com.resource.common.model.PersonalDetail;
import com.resource.common.model.Profile;
import com.resource.common.routes.AdminPath;
import com.resource.common.validation.GroupPersonalInfo;
import com.resource.common.view.EnableTags;
import com.resource.common.view.SelectedTab;

@Controller
@EnableTags(value = {"personal_settings", "employee_settings"}, selected = "personal_settings")
public class PersonalDetailController extends AdminController {
	
	@Autowired
	private IEmployeeService employeeService;

	@Autowired
	private IPersonalDetailService personalService;
	
	@Autowired
	private Validator validator;
	
	@SelectedTab("personal")
	@GetMapping(value = "/employee/{id}/personal")
	public String show(ModelMap map, @PathVariable("id") String code) {
		Employee employee = employeeService.findByCode(code);
		map.addAttribute("employee", employee);
		map.addAttribute("employeeProfile", employee.getProfile());
		
		if (employee.getProfile() == null)
			map.addAttribute("employeeProfile", new Profile());
		
		map.addAttribute("personal", employee.getPersonalDetail());
		if (employee.getPersonalDetail() == null)
			map.addAttribute("personal", new PersonalDetail());
		
		return AdminPath.employee_personal_show.partial();
	}
	
	@SelectedTab("personal")
	@GetMapping(value = "/employee/{id}/personal/edit")
	public String edit(ModelMap map, @PathVariable("id") String code) {
		List<String> errors = new ArrayList<String>();
		Employee employee = employeeService.findByCode(code);
		if (employee == null)
			errors.add("Employee not present in the system");
		
		if(errors.isEmpty()) {
			map.addAttribute("employee", employee);
			map.addAttribute("employeeProfile", employee.getProfile());
			if (employee.getProfile() == null)
				map.addAttribute("employeeProfile", new Profile());
			
			map.addAttribute("personal", employee.getPersonalDetail());
			if (employee.getPersonalDetail() == null)
				map.addAttribute("personal", new PersonalDetail());
		}
		map.addAttribute("errors", errors);
		return AdminPath.employee_personal_edit.partial();
	}
	
	@SelectedTab("personal")
	@PostMapping(value = "/employee/{id}/personal/edit")
	public String update(ModelMap map, @PathVariable("id") String code, Employee employee) {
		List<String> errors = new ArrayList<String>();
		Employee existingEmployee = employeeService.findByCode(code);
		if (existingEmployee == null)
			errors.add("Employee not present in the system");
		
		Set<ConstraintViolation<Employee>> employeeViolations = validator.validate(employee, GroupPersonalInfo.class);
			
		if (employeeViolations.size() > 0) {
			employeeViolations.stream().forEach(e -> errors.add(e.getMessage()));
		}
		
		Set<ConstraintViolation<PersonalDetail>> personalDetailViolations = validator.validate(employee.getPersonalDetail());
		if (personalDetailViolations.size() > 0) {
			personalDetailViolations.stream().forEach(e -> errors.add(e.getMessage()));
		}
		
		if(!errors.isEmpty()) {
			map.addAttribute("errors", errors);
			map.addAttribute("employeeProfile", employee.getProfile());
			if (employee.getProfile() == null)
				map.addAttribute("employeeProfile", new Profile());
			
			map.addAttribute("personal", employee.getPersonalDetail());
			if (employee.getPersonalDetail() == null)
				map.addAttribute("personal", new PersonalDetail());
			
			return AdminPath.employee_personal_edit.partial();
		}

		map.addAttribute("employee", existingEmployee);
		
		PersonalDetail updatedPersonalDetail = employee.getPersonalDetail();
		PersonalDetail existingPersonalDetail = existingEmployee.getPersonalDetail();
		employee.setPersonalDetail(null);
		mapper.setExceptFields("employeeCode");
		existingEmployee = mapper.merge(existingEmployee, employee);
		employeeService.update(existingEmployee);
		
		existingPersonalDetail = mapper.merge(existingPersonalDetail, updatedPersonalDetail);
		personalService.update(existingPersonalDetail);
		
		// Without this the view attributes will shown in the url 
		// https://stackoverflow.com/a/26175415
		map.clear();
		return AdminPath.employee_personal_show.redirect();
	}
}
