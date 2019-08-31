package com.employee.api.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.resource.common.model.Employee;
import com.employee.api.repository.EmployeeRepo;

@Service
@Transactional
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
    EmployeeRepo employeeRepo;

	
	@Override
	public void create(Employee employee) {
		employeeRepo.create(employee);
	}

	@Override
	public List<Employee> get() {
		return employeeRepo.get();
	}

	@Override
	public Employee findById(int id) {
		return employeeRepo.findById(id);
	}

	@Override
	public Employee update(Employee employee, int id) {
		return employeeRepo.update(employee, id);
	}

	@Override
	public void delete(int id) {
		employeeRepo.delete(id);
	}
	
	

}
