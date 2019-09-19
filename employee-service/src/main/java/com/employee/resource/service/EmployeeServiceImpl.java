package com.employee.resource.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.employee.resource.repository.IEmployeeRepo;
import com.resource.common.model.Employee;

@Service("employeeService")
@Transactional
public class EmployeeServiceImpl implements IEmployeeService {

	@Autowired
    private IEmployeeRepo employeeRepo;

	
	@Override
	public Employee create(Employee employee) {
		return employeeRepo.save(employee);
	}
	
	@Override
	public List<Employee> get() {
		return (List<Employee>) employeeRepo.findAll();
	}
	
	@Override
	public Page<Employee> get(Pageable page) {
		return employeeRepo.findAll(page);
	}

	@Override
	public Employee findById(int id) {
		Optional<Employee> employee = employeeRepo.findById(id);
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
