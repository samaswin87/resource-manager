package com.employee.resource.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.resource.common.model.Employee;

@Repository
public interface IEmployeeRepo extends PagingAndSortingRepository<Employee, Integer>{

}
