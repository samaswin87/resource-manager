package com.employee.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.resource.common.model.Employee;
import com.employee.api.service.EmployeeService;

@RestController
@RequestMapping(value = { "/employees" })
public class EmployeeController {

	@Autowired
	EmployeeService employeeService;

	@GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Employee> get(@PathVariable("id") int id) {
		Employee employee = employeeService.findById(id);
		if (employee == null) {
			return new ResponseEntity<Employee>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Employee>(employee, HttpStatus.OK);
	}

	@PostMapping(value = "/create", headers = "Accept=application/json")
	public ResponseEntity<Void> create(@RequestBody Employee employee, UriComponentsBuilder ucBuilder) {
		employeeService.create(employee);
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucBuilder.path("/employees/{id}").buildAndExpand(employee.getId()).toUri());
		return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	}

	@GetMapping(value = "/", headers = "Accept=application/json")
	public List<Employee> getAll() {
		List<Employee> employees = employeeService.get();
		return employees;

	}

	@PutMapping(value = "{id}/edit", headers = "Accept=application/json")
	public ResponseEntity<String> update(@PathVariable("id") int id, @RequestBody Employee currentEmployee) {
		Employee employee = employeeService.findById(id);
		if (employee == null) {
			return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
		}
		employeeService.update(currentEmployee, id);
		return new ResponseEntity<String>(HttpStatus.OK);
	}

	@DeleteMapping(value = "/{id}", headers = "Accept=application/json")
	public ResponseEntity<Employee> delete(@PathVariable("id") int id) {
		Employee employee = employeeService.findById(id);
		if (employee == null) {
			return new ResponseEntity<Employee>(HttpStatus.NOT_FOUND);
		}
		employeeService.delete(id);
		return new ResponseEntity<Employee>(HttpStatus.NO_CONTENT);
	}

}
