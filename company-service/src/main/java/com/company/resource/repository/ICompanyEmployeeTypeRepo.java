package com.company.resource.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.resource.common.model.Company;
import com.resource.common.model.EmployeeType;

public interface ICompanyEmployeeTypeRepo  extends JpaRepository<EmployeeType, Integer> {

	@Query(value = "SELECT e FROM EmployeeType e WHERE e.company =:company")
	public List<EmployeeType> findAll(Company company);
}
