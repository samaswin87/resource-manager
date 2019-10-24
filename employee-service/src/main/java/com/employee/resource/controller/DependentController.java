package com.employee.resource.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.employee.resource.service.IDependentService;
import com.employee.resource.service.IEmployeeService;
import com.resource.common.controller.AdminController;
import com.resource.common.model.Dependent;
import com.resource.common.model.Employee;
import com.resource.common.model.Profile;
import com.resource.common.routes.AdminPath;
import com.resource.common.view.EnableTags;
import com.resource.common.view.SelectedTab;

@EnableTags(value = {"personal_settings", "employee_settings"}, selected = "personal_settings")
@Controller
public class DependentController extends AdminController {

	@Autowired
	private IEmployeeService employeeService;
	
	@Autowired
	private IDependentService dependentService;
	
	@Autowired
	private Validator validator;
	
	@SelectedTab("dependent")
	@GetMapping(value = "/employee/{id}/dependent")
	public String show(ModelMap map, @PathVariable("id") String code) {
		Employee employee = employeeService.findByCode(code);
		map.addAttribute("employee", employee);
		
		map.addAttribute("employeeProfile", employee.getProfile());
		if (employee.getProfile() == null)
			map.addAttribute("employeeProfile", new Profile());
		
		List<Dependent> dependents = employee.getDependents();
		List<Dependent> currentDependents = dependents.stream()
												   	  .filter(a -> a.isCurrent() && a.getEndDate() == null)
												      .collect(Collectors.toList());
		map.addAttribute("currentDependents", currentDependents);
		List<Dependent> pastDependents = dependents.stream()
												   .filter(a -> !a.isCurrent())
												   .collect(Collectors.toList());
		map.addAttribute("dependents", pastDependents);
		return AdminPath.employee_dependent_show.partial();
	}
	
	@SelectedTab("dependent")
	@GetMapping(value = "/employee/{id}/dependent/{dependentId}/edit")
	public String edit(ModelMap map, @PathVariable("id") String code, @PathVariable("dependentId") Integer dependentId) {
		List<String> errors = new ArrayList<String>();
		Employee employee = employeeService.findByCode(code);
		if (employee == null)
			errors.add("Employee not present in the system");
		
		if(!errors.isEmpty())
			map.addAttribute("errors", errors);
		
		map.addAttribute("employee", employee);
		map.addAttribute("dependent", employee.getDependent(dependentId));
		return AdminPath.employee_dependent_edit.partial();
	}
	
	@SelectedTab("dependent")
	@PostMapping(value = "/employee/{id}/dependent/{dependentId}/edit")
	public String update(ModelMap map, @PathVariable("id") String code, @PathVariable("dependentId") Integer dependentId, Dependent dependent) {
		List<String> errors = new ArrayList<String>();
		Employee employee = employeeService.findByCode(code);
		if (employee == null)
			errors.add("Employee not present in the system");
		
		Dependent existingDependent = employee.getDependent(dependentId);
		
		Set<ConstraintViolation<Dependent>> addressViolations = validator.validate(dependent);
		if (addressViolations.size() > 0) {
			addressViolations.stream().forEach(e -> errors.add(e.getMessage()));
		}

		map.addAttribute("employee", employee);
		if(!errors.isEmpty()) {
			map.addAttribute("errors", errors);
			map.addAttribute("dependent", dependent);
			return AdminPath.employee_dependent_edit.partial();
		}

		mapper.setExceptFields("statusId", "current");
		existingDependent = mapper.merge(existingDependent, dependent);
		dependentService.update(existingDependent);
		map.clear();
		return AdminPath.employee_dependent_show.redirect();
	}
}
