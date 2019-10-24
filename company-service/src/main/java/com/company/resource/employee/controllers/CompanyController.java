package com.company.resource.employee.controllers;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.server.ResponseStatusException;

import com.company.resource.service.ICompanyService;
import com.resource.common.controller.EmployeeController;
import com.resource.common.model.Company;
import com.resource.common.routes.HomePath;

@Controller("employeeCompanyController")
public class CompanyController extends EmployeeController {

	@Autowired
	ICompanyService companyService;
	
	@GetMapping(value = "/company/change/{id}", headers = "Accept=application/json")
	public ResponseEntity<String> change(@PathVariable("id") Integer id, HttpSession session, HttpServletResponse response) {
		Company company = companyService.findById(id);
		if(company == null) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Unable to find resource");
		}
		session.setAttribute("currentCompany", company);
		return new ResponseEntity<String>(HomePath.home_show.path(), HttpStatus.OK);
	}
}
