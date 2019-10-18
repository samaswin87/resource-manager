package com.employee.resource.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.resource.common.model.Company;
import com.resource.common.model.Employee;

@Repository
public interface IEmployeeRepo extends PagingAndSortingRepository<Employee, Integer>{

	public Optional<Employee> findByEmployeeCode(String code);
	
	@Query(value = "SELECT e FROM Employee e WHERE e.company =:company")
	public List<Employee> findAll(Company company);
	
	@Query(value = "SELECT e FROM Employee e WHERE e.company =:company")
	public Page<Employee> findAll(Pageable page, Company company);
}
