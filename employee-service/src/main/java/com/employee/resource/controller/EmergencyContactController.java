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

import com.employee.resource.service.IEmergencyContactService;
import com.employee.resource.service.IEmployeeService;
import com.resource.common.controller.AdminController;
import com.resource.common.model.EmergencyContact;
import com.resource.common.model.Employee;
import com.resource.common.model.Profile;
import com.resource.common.routes.AdminPath;
import com.resource.common.view.EnableTags;
import com.resource.common.view.SelectedTab;

@EnableTags(value = {"personal_settings", "employee_settings"}, selected = "personal_settings")
@Controller
public class EmergencyContactController extends AdminController {

	@Autowired
	private IEmployeeService employeeService;

	@Autowired
	private IEmergencyContactService contactService;

	@Autowired
	private Validator validator;
	
	@SelectedTab("contact")
	@GetMapping(value = "/employee/{id}/contact")
	public String show(ModelMap map, @PathVariable("id") String code) {
		Employee employee = employeeService.findByCode(code);
		map.addAttribute("employee", employee);
		
		map.addAttribute("employeeProfile", employee.getProfile());
		if (employee.getProfile() == null)
			map.addAttribute("employeeProfile", new Profile());
		
		map.addAttribute("contacts", employee.getEmergencyContacts());
		return AdminPath.employee_contact_show.partial();
	}
	
	@GetMapping(value = "/employee/{id}/contact/{contactId}/edit")
	public String edit(ModelMap map, @PathVariable("id") String code, @PathVariable("contactId") Integer contactId) {
		List<String> errors = new ArrayList<String>();
		Employee employee = employeeService.findByCode(code);
		if (employee == null)
			errors.add("Employee not present in the system");

		if(errors.isEmpty()) {
			map.addAttribute("employee", employee);
			map.addAttribute("contact", employee.getContact(contactId));
		}
		map.addAttribute("errors", errors);
		return AdminPath.employee_contact_edit.partial();
	}

	@PostMapping(value = "/employee/{id}/contact/{contactId}/edit")
	public String update(ModelMap map, @PathVariable("id") String code, @PathVariable("contactId") Integer contactId, EmergencyContact contact) {
		List<String> errors = new ArrayList<String>();
		Employee employee = employeeService.findByCode(code);
		if (employee == null)
			errors.add("Employee not present in the system");

		EmergencyContact existingContact = employee.getContact(contactId);

		Set<ConstraintViolation<EmergencyContact>> contactViolations = validator.validate(contact);
		if (contactViolations.size() > 0) {
			contactViolations.stream().forEach(e -> errors.add(e.getMessage()));
		}

		if(!errors.isEmpty()) {
			map.addAttribute("errors", errors);
			map.addAttribute("contact", contact);
			return AdminPath.employee_contact_edit.partial();
		}

		map.addAttribute("employee", employee);
		mapper.setExceptFields("statusId");
		existingContact = mapper.merge(existingContact, contact);
		contactService.update(existingContact);
		map.clear();
		return AdminPath.employee_contact_show.redirect();
	}
}
