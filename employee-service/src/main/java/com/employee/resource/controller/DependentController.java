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

import com.employee.resource.service.IDependentService;
import com.employee.resource.service.IEmployeeService;
import com.resource.common.controller.AdminController;
import com.resource.common.model.Dependent;
import com.resource.common.model.Employee;
import com.resource.common.routes.AdminPath;
import com.resource.common.view.EnableTags;

@Controller
public class DependentController extends AdminController {

	@Autowired
	private IEmployeeService employeeService;
	
	@Autowired
	private IDependentService dependentService;
	
	@Autowired
	private Validator validator;
	
	@EnableTags({"employee_menu"})
	@GetMapping(value = "/employee/{id}/depnedent/{dependentId}/edit")
	public String edit(ModelMap map, @PathVariable("id") String code, @PathVariable("depenedentId") Integer dependentId) {
		List<String> errors = new ArrayList<String>();
		Employee employee = employeeService.findByCode(code);
		if (employee == null)
			errors.add("Employee not present in the system");
		
		if(errors.isEmpty()) {
			map.addAttribute("employee", employee);
			map.addAttribute("dependent", employee.getAddress(dependentId));
		}
		map.addAttribute("errors", errors);
		return AdminPath.employee_dependent_edit.partial();
	}
	
	@EnableTags({"employee_menu"})
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
		
		if(errors.isEmpty()) {
			map.addAttribute("errors", errors);
			return AdminPath.employee_dependent_edit.partial();
		}

		map.addAttribute("employee", employee);
		existingDependent = mapper.merge(existingDependent, dependent);
		dependentService.update(existingDependent);
		return AdminPath.employee_show.redirect();
	}
}
