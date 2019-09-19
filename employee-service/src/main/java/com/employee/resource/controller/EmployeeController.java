package com.employee.resource.controller;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.employee.resource.service.IEmployeeService;
import com.resource.common.model.Employee;
import com.resource.common.view.PaginatedList;

@Controller
public class EmployeeController extends AdminController {


	@Autowired
	private IEmployeeService employeeService;
	

	@GetMapping(value = "/{id}")
	public ResponseEntity<Employee> get(@PathVariable("id") int id) {
		Employee employee = employeeService.findById(id);
		if (employee == null) {
			return new ResponseEntity<Employee>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Employee>(employee, HttpStatus.OK);
	}

	@GetMapping(value = "/employee/new")
	public String newForm(Employee employee, ModelMap map) {
		return "/admin/employees/new.html";
	}
	
	@PostMapping(value = "/employee/new")
	public ModelAndView create(@Valid Employee employee, ModelMap map) {
		if (employeeService.create(employee) == null) {
			return new ModelAndView("/admin/employees/new.html");
		}
		return new ModelAndView("redirect:/admin/employees", map);
	}
	
	@PaginatedList(name="employees", service="employeeService")
	@RequestMapping(value = "/employees", method = RequestMethod.GET)
	public String employees(@RequestParam("page") Optional<Integer> pageIndex, ModelMap map) {
		return "/admin/employees.html";
	}

	@PutMapping(value = "{id}/edit")
	public ResponseEntity<String> update(@PathVariable("id") int id, @RequestBody Employee currentEmployee) {
		Employee employee = employeeService.findById(id);
		if (employee == null) {
			return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
		}
		employeeService.update(currentEmployee);
		return new ResponseEntity<String>(HttpStatus.OK);
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Employee> delete(@PathVariable("id") int id) {
		Employee employee = employeeService.findById(id);
		if (employee == null) {
			return new ResponseEntity<Employee>(HttpStatus.NOT_FOUND);
		}
		employeeService.delete(id);
		return new ResponseEntity<Employee>(HttpStatus.NO_CONTENT);
	}

}
