package com.employee.resource.controller;

import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.employee.resource.service.IEmployeeService;
import com.resource.common.controller.AdminController;
import com.resource.common.model.Employee;
import com.resource.common.model.Profile;
import com.resource.common.routes.AdminPath;
import com.resource.common.view.PaginatedList;

@Controller
public class EmployeeController extends AdminController {

	@Autowired
	private IEmployeeService employeeService;

	@GetMapping(value = "/employee/new")
	public String newForm(Employee employee, ModelMap map) {
		return AdminPath.employee_add.partial();
	}

	@PostMapping(value = "/employee/new")
	public String create(@Valid Employee employee, BindingResult bindingResult, ModelMap map) {
		if(!bindingResult.hasErrors()) {
			if (employeeService.create(employee) != null) {
				return AdminPath.employee_list.path();
			}
		}

		return AdminPath.employee_add.path();
	}

	@PaginatedList(name="employees", service="employeeService")
	@RequestMapping(value = "/employees", method = RequestMethod.GET)
	public String employees(@RequestParam("page") Optional<Integer> pageIndex, ModelMap map, HttpServletRequest reuest) {
		return AdminPath.employee_list.path();
	}
	
	@DeleteMapping(value = "/employee/{id}/delete", headers = "Accept=application/json")
	public ResponseEntity<String> delete(@PathVariable("id") String code) {
		Employee employee = employeeService.findByCode(code);
		if (employee == null) {
			return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
		}
		employeeService.delete(employee.getId());
		return new ResponseEntity<String>(AdminPath.employee_list.path(), HttpStatus.OK);
	}
	
	@GetMapping(value = "/employee/{id}/profile", produces = { MediaType.IMAGE_JPEG_VALUE, MediaType.IMAGE_GIF_VALUE, MediaType.IMAGE_PNG_VALUE})
	public ResponseEntity<byte[]> profile(@PathVariable("id") int id) {
		Employee employee = employeeService.findById(id);
		if (employee == null) {
			return null;
		}
		Profile profile = employee.getProfile();
		if (profile == null) {
			return null;
		}
		
		return ResponseEntity
			                .ok()
			                .contentType(profile.getType())
			                .body(profile.getImage());
	}
}
