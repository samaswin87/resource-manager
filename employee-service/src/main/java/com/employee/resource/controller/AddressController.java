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

import com.employee.resource.service.IAddressService;
import com.employee.resource.service.IEmployeeService;
import com.resource.common.controller.AdminController;
import com.resource.common.model.Address;
import com.resource.common.model.Employee;
import com.resource.common.model.Profile;
import com.resource.common.routes.AdminPath;
import com.resource.common.view.EnableTags;
import com.resource.common.view.SelectedTab;

@EnableTags(value = {"personal_settings", "employee_settings"}, selected = "personal_settings")
@Controller
public class AddressController extends AdminController {

	@Autowired
	private IEmployeeService employeeService;
	
	@Autowired
	private IAddressService addressService;
	
	@Autowired
	private Validator validator;
	
	@SelectedTab("address")
	@GetMapping(value = "/employee/{id}/address")
	public String show(ModelMap map, @PathVariable("id") String code) {
		Employee employee = employeeService.findByCode(code);
		map.addAttribute("employee", employee);
		
		map.addAttribute("employeeProfile", employee.getProfile());
		if (employee.getProfile() == null)
			map.addAttribute("employeeProfile", new Profile());
		
		map.addAttribute("currentAddress", employee.getCurrentAddress());
		map.addAttribute("addresses", employee.getAddressHistory());
		
		return AdminPath.employee_address_show.partial();
	}
	
	@SelectedTab("address")
	@GetMapping(value = "/employee/{id}/address/{addressId}/edit")
	public String edit(ModelMap map, @PathVariable("id") String code, @PathVariable("addressId") Integer addressId) {
		List<String> errors = new ArrayList<String>();
		Employee employee = employeeService.findByCode(code);
		if (employee == null)
			errors.add("Employee not present in the system");
		
		if(errors.isEmpty()) {
			map.addAttribute("errors", errors);
		}
		map.addAttribute("employee", employee);
		map.addAttribute("address", employee.getAddress(addressId));
		return AdminPath.employee_address_edit.partial();
	}
	
	@SelectedTab("address")
	@PostMapping(value = "/employee/{id}/address/{addressId}/edit")
	public String update(ModelMap map, @PathVariable("id") String code, @PathVariable("addressId") Integer addressId, Address address) {
		List<String> errors = new ArrayList<String>();
		Employee employee = employeeService.findByCode(code);
		if (employee == null)
			errors.add("Employee not present in the system");
		
		Address existingAddress = employee.getAddress(addressId);
		
		Set<ConstraintViolation<Address>> addressViolations = validator.validate(address);
		if (addressViolations.size() > 0) {
			addressViolations.stream().forEach(e -> errors.add(e.getMessage()));
		}
		
		map.addAttribute("employee", employee);
		map.addAttribute("address", employee.getAddress(addressId));
		
		if(!errors.isEmpty()) {
			map.addAttribute("errors", errors);
			return AdminPath.employee_address_edit.partial();
		}
		mapper.setExceptFields("statusId", "current");
		existingAddress = mapper.merge(existingAddress, address);
		addressService.update(existingAddress);
		map.clear();
		return AdminPath.employee_address_show.redirect();
	}
}
