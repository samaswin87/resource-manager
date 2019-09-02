package com.company.api.controller;

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

import com.company.api.service.CompanyService;
import com.resource.common.model.Company;

@RestController
@RequestMapping(value = { "/companies" })
public class CompanyController {

	@Autowired
	CompanyService companyService;

	@GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Company> get(@PathVariable("id") int id) {
		Company company = companyService.findById(id);
		if (company == null) {
			return new ResponseEntity<Company>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Company>(company, HttpStatus.OK);
	}

	@PostMapping(value = "/create", headers = "Accept=application/json")
	public ResponseEntity<Void> create(@RequestBody Company company, UriComponentsBuilder ucBuilder) {
		companyService.create(company);
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucBuilder.path("/companies/{id}").buildAndExpand(company.getId()).toUri());
		return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	}

	@GetMapping(value = "/", headers = "Accept=application/json")
	public List<Company> getAll() {
		List<Company> companies = companyService.get();
		return companies;
	}

	@PutMapping(value = "{id}/edit", headers = "Accept=application/json")
	public ResponseEntity<String> update(@PathVariable("id") int id, @RequestBody Company currentEmployee) {
		Company company = companyService.findById(id);
		if (company == null) {
			return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
		}
		companyService.update(currentEmployee, id);
		return new ResponseEntity<String>(HttpStatus.OK);
	}

	@DeleteMapping(value = "/{id}", headers = "Accept=application/json")
	public ResponseEntity<Company> delete(@PathVariable("id") int id) {
		Company company = companyService.findById(id);
		if (company == null) {
			return new ResponseEntity<Company>(HttpStatus.NOT_FOUND);
		}
		companyService.delete(id);
		return new ResponseEntity<Company>(HttpStatus.NO_CONTENT);
	}

}
