package com.company.resource.admin.controller;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
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

import com.company.resource.service.ICompanyService;
import com.resource.common.controller.AdminController;
import com.resource.common.model.Company;
import com.resource.common.routes.AdminPath;
import com.resource.common.view.PaginateByName;
import com.resource.common.view.PaginatedList;

@Controller
public class CompanyController extends AdminController {
	
	@Autowired
	ICompanyService companyService;

	@GetMapping(value = "/company/new")
	public String newForm(Company company, ModelMap map) {
		map.addAttribute("companies", companyService.get());
		return AdminPath.company_add.partial();
	}

	@PostMapping(value = "/company/new")
	public String create(@Valid Company company, BindingResult bindingResult, ModelMap map) {
		if(!bindingResult.hasErrors()) {
			if (companyService.create(company) != null) {
				return AdminPath.company_list.redirect();
			}
		}

		return AdminPath.company_add.partial();
	}

	@PaginatedList(name="companies", service="companyService")
	@RequestMapping(value = "/companies", method = RequestMethod.GET)
	public String companies(@RequestParam("page") Optional<Integer> pageIndex, ModelMap map, HttpServletRequest request) {
		return AdminPath.company_list.path();
	}

	@GetMapping(value = "/company/{id}/edit")
	public String update(@PathVariable("id") int id, ModelMap map) {
		Company existingCompany = companyService.findById(id);
		if (existingCompany != null){
			map.addAttribute("company", existingCompany);
		}
		setCompanySelect(map, existingCompany);
		return AdminPath.company_edit.partial();
	}

	@PostMapping(value = "/company/{id}/edit")
	public String update(@PathVariable("id") Integer id, @Valid Company company, BindingResult bindingResult, ModelMap map) {
		if(!bindingResult.hasErrors() || id != null) {
			if (companyService.create(company) != null) {
				return AdminPath.company_list.redirect();
			}
		}
		map.addAttribute("company", company);
		setCompanySelect(map, company);
		return AdminPath.company_edit.partial();
	}
	
	@GetMapping(value = "/company/search")
	@PaginateByName(name="companies", service="companyService", method="findByName")
	public String search(@RequestParam("page") Optional<Integer> pageIndex, @RequestParam("name") Optional<String> name, ModelMap map, HttpServletRequest request) {
		String companyName = name.orElse(null);
		if(StringUtils.isEmpty(companyName))
			return AdminPath.company_list.redirect();
		
		return AdminPath.company_list.path();
	}

	@DeleteMapping(value = "/company/{id}/delete")
	public String delete(@PathVariable("id") Integer id, ModelMap map) {
		Company company = companyService.findById(id);
		if (company != null) {
			companyService.delete(id);
		}
		return AdminPath.company_list.redirect();
	}
	
	private void setCompanySelect(ModelMap map, Company company){
		List<Company> companies = companyService.get();
		companies.remove(company);
		map.addAttribute("companies", companies);
		Company parent = company.getParent();
		if(parent != null)
			map.addAttribute("selectedId", parent.getId());
	}

}
