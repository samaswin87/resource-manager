package com.employee.resource.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.employee.resource.repository.IEmployeeRepo;
import com.resource.common.model.Company;
import com.resource.common.model.Employee;

@Service("employeeService")
@Transactional
public class EmployeeService implements IEmployeeService {

	public static final List<String> errors = new ArrayList<String>();
	
	@Autowired
    private IEmployeeRepo employeeRepo;
	
	@Autowired 
	private HttpSession httpSession;
	
	@Override
	public Employee create(Employee employee) {
		return employeeRepo.save(employee);
	}
	
	@Override
	public List<Employee> get() {
		Company currentCompany = (Company) httpSession.getAttribute("currentCompany");
		return (List<Employee>) employeeRepo.findAll(currentCompany);
	}
	
	@Override
	public Page<Employee> get(Pageable page) {
		Company currentCompany = (Company) httpSession.getAttribute("currentCompany");
		return employeeRepo.findAll(page, currentCompany);
	}

	@Override
	public Employee findById(int id) {
		Optional<Employee> employee = employeeRepo.findById(id);
		return employee.orElse(null);
	}
	
	@Override
	public Employee findByCode(String code) {
		Optional<Employee> employee = employeeRepo.findByEmployeeCode(code);
		return employee.orElse(null);
	}

	@Override
	public Employee update(Employee employee) {
		return employeeRepo.save(employee);
	}

	@Override
	public void delete(int id) {
		employeeRepo.deleteById(id);
	}

}
