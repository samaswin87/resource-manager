package com.company.resource.admin.controller;

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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.company.resource.service.IShiftTypeService;
import com.resource.common.controller.AdminController;
import com.resource.common.model.EmployeeType;
import com.resource.common.model.ShiftType;
import com.resource.common.routes.AdminPath;
import com.resource.common.service.IEmployeeTypeService;
import com.resource.common.validation.GroupHRSettings;
import com.resource.common.validation.GroupWorkTimeSettings;

@Controller
public class EmployeeTypeController extends AdminController {
	
	@Autowired
	private IEmployeeTypeService service;
	
	@Autowired
	private IShiftTypeService shiftService;
	
	@Autowired
	private Validator validator;
	
	@GetMapping(value = "/company/employee_types")
	public String list(ModelMap map) {
		map.addAttribute("employeeTypes", service.list());
		return AdminPath.employee_type_list.partial();
	}
	
	@GetMapping(value = "/company/employee_types/new")
	public String create(ModelMap map) {
		map.addAttribute("employeeType", new EmployeeType());
		return AdminPath.employee_type_add.partial();
	}
	
	@PostMapping(value = "/company/employee_types/new")
	public String add(ModelMap map, EmployeeType employeeType) {
		
		List<String> errors = new ArrayList<String>();
		
		Set<ConstraintViolation<EmployeeType>> employeeTypeViolations = validator.validate(employeeType, GroupHRSettings.class, GroupWorkTimeSettings.class);
		if (employeeTypeViolations.size() > 0) {
			employeeTypeViolations.stream().forEach(e -> errors.add(e.getMessage()));
		}
		if (!errors.isEmpty()) {
			map.addAttribute("errors", errors);
			map.addAttribute("employeeType", employeeType);
			return AdminPath.employee_type_add.partial();
		}
		employeeType.setCompany(currentCompany());
		service.add(employeeType);
		return AdminPath.employee_type_list.redirect();
	}
	
	@GetMapping(value = "/company/employee_types/{id}/hr_settings")
	public String hrSettings(ModelMap map, @PathVariable("id") Integer id) {
		map.addAttribute("employeeType", service.find(id));
		return AdminPath.employee_type_hr.partial();
	}
	
	@GetMapping(value = "/company/employee_types/{id}/work_time_settings")
	public String workTimeSettings(ModelMap map, @PathVariable("id") Integer id) {
		map.addAttribute("employeeType", service.find(id));
		return AdminPath.employee_type_work_time.partial();
	}
	
	@PutMapping(value = "/company/employee_types/{id}/work_time_settings")
	public String addWorkStyle(ModelMap map, @PathVariable("id") Integer id, @RequestParam("name") String name) {
		List<String> errors = new ArrayList<String>();
		map.addAttribute("employeeType", service.find(id));
		ShiftType type = shiftService.create(name);
		if(type == null) {
			errors.add("Failed to add shift type");
		}
		map.addAttribute("errors", "errors");
		return AdminPath.employee_type_work_time.partial();
	}
	
	@GetMapping(value = "/company/employee_types/{id}/work_time_settings/edit")
	public String editWorkTimeSettings(ModelMap map, @PathVariable("id") Integer id) {
		map.addAttribute("employeeType", service.find(id));
		return AdminPath.employee_type_work_time_edit.partial();
	}
	
	@PostMapping(value = "/company/employee_types/{id}/work_time_settings/edit")
	public String editWorkTimeSettings(ModelMap map, @PathVariable("id") Integer id, EmployeeType employeeType) {
		List<String> errors = new ArrayList<String>();
		
		Set<ConstraintViolation<EmployeeType>> employeeTypeViolations = validator.validate(employeeType, GroupWorkTimeSettings.class);
		if (employeeTypeViolations.size() > 0) {
			employeeTypeViolations.stream().forEach(e -> errors.add(e.getMessage()));
		}
		if (!errors.isEmpty()) {
			map.addAttribute("errors", errors);
			map.addAttribute("employeeType", service.find(id));
			return AdminPath.employee_type_work_time_edit.partial();
		}
		
		employeeType.setId(id);
		service.updateWorTimeSettings(employeeType);
		return AdminPath.employee_type_work_time.redirect();
	}
	
	@GetMapping(value = "/company/employee_types/{id}/hr_settings/edit")
	public String editHRSettings(ModelMap map, @PathVariable("id") Integer id) {
		map.addAttribute("employeeType", service.find(id));
		return AdminPath.employee_type_hr_edit.partial();
	}

	@PostMapping(value = "/company/employee_types/{id}/hr_settings/edit")
	public String editHRSettings(ModelMap map, @PathVariable("id") Integer id, EmployeeType employeeType) {
		List<String> errors = new ArrayList<String>();
		
		Set<ConstraintViolation<EmployeeType>> employeeTypeViolations = validator.validate(employeeType, GroupHRSettings.class);
		if (employeeTypeViolations.size() > 0) {
			employeeTypeViolations.stream().forEach(e -> errors.add(e.getMessage()));
		}
		if (!errors.isEmpty()) {
			map.addAttribute("errors", errors);
			map.addAttribute("employeeType", service.find(id));
			return AdminPath.employee_type_hr_edit.partial();
		}
		
		employeeType.setId(id);
		service.updateHRSettings(employeeType);
		return AdminPath.employee_type_hr.redirect();
	}
}
