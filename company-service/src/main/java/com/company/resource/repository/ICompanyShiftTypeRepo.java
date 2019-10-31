package com.company.resource.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.resource.common.model.Company;
import com.resource.common.model.ShiftType;

public interface ICompanyShiftTypeRepo extends JpaRepository<ShiftType, Integer> {

	@Query(value = "SELECT s FROM ShiftType s WHERE s.company =:company")
	public List<ShiftType> findAll(Company company);
}
