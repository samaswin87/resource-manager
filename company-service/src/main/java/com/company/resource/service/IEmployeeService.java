package com.company.resource.service;

import java.util.List;

import com.resource.common.dto.EmployeeDTO;
import com.resource.common.model.Employee;

public interface IEmployeeService {

	List<EmployeeDTO> serach(String name, Integer teamId);

	List<EmployeeDTO> serach(Integer teamId);
	
	Employee findByCode(String code);

}
