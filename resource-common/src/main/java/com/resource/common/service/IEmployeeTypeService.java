package com.resource.common.service;

import java.util.List;

import com.resource.common.model.EmployeeType;

public interface IEmployeeTypeService {

	public List<EmployeeType> list();
	
	public EmployeeType find(Integer id);

	public EmployeeType updateWorTimeSettings(EmployeeType employeeType);
	
	public List<String> errors();

	public void updateHRSettings(EmployeeType employeeType);

	public void add(EmployeeType employeeType);
}
