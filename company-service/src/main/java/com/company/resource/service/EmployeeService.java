package com.company.resource.service;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.company.resource.repository.ICompanyEmployeeRepo;
import com.resource.common.dto.EmployeeDTO;
import com.resource.common.model.Company;
import com.resource.common.model.Employee;

@Service("companyEmployeeService")
public class EmployeeService implements IEmployeeService {

	@Autowired
	private ICompanyEmployeeRepo repo;
	
	@Autowired 
	private HttpSession httpSession;
	
	@Override
	public List<EmployeeDTO> serachMember(String name, Integer teamId) {
		Company currentCompany = (Company) httpSession.getAttribute("currentCompany");
		return repo.serachMember(name, teamId, currentCompany);
	}
	
	@Override
	public List<EmployeeDTO> serach(Integer teamId) {
		Company currentCompany = (Company) httpSession.getAttribute("currentCompany");
		return repo.serach(teamId, currentCompany);
	}
	
	@Override
	public List<EmployeeDTO> serachLeader(String name, Integer teamId) {
		Company currentCompany = (Company) httpSession.getAttribute("currentCompany");
		return repo.serachLeader(name, teamId, currentCompany);
	}

	@Override
	public Employee findByCode(String code) {
		Company currentCompany = (Company) httpSession.getAttribute("currentCompany");
		return repo.findByCode(code, currentCompany);
	}
}
