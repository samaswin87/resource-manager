package com.company.resource.service;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.company.resource.repository.ICompanyEmployeeTypeRepo;
import com.resource.common.model.Company;
import com.resource.common.model.EmployeeType;

@Service
public class CompanyEmployeeTypeService implements ICompanyEmployeeTypeService {
	
	@Autowired
	ICompanyEmployeeTypeRepo repo;
	
	@Autowired 
	private HttpSession httpSession;
	
	@Override
	public List<EmployeeType> list() {
		Company currentCompany = (Company) httpSession.getAttribute("currentCompany");
		return repo.findAll(currentCompany);
	}
	
	

}
