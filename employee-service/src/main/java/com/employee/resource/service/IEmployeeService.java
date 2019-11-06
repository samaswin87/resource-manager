package com.employee.resource.service;

import com.resource.common.model.Employee;
import com.resource.common.service.PagingModelService;

public interface IEmployeeService extends PagingModelService<Employee> {

	public Employee findByCode(String code);
}
