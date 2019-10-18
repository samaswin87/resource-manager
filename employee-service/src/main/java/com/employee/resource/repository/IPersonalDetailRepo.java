package com.employee.resource.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.resource.common.model.PersonalDetail;

@Repository
public interface IPersonalDetailRepo extends JpaRepository<PersonalDetail, Integer> {

	@Query("SELECT p FROM PersonalDetail p WHERE p.employee.employeeCode =:employeeCode")
	public PersonalDetail findByEmployeeCode(String employeeCode);
}
