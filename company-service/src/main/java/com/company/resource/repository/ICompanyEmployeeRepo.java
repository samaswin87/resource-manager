package com.company.resource.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.resource.common.dto.EmployeeDTO;
import com.resource.common.model.Company;
import com.resource.common.model.Employee;

public interface ICompanyEmployeeRepo extends JpaRepository<Employee, Integer> {

	@Query(value = "SELECT NEW com.resource.common.dto.EmployeeDTO(e.id, e.firstName, e.lastName, e.employeeCode) FROM Employee e LEFT JOIN TeamMember m ON e.id = m.employee.id WHERE m.team.id != :teamId OR m.team IS NULL AND e.employeeCode = :name OR e.firstName like %:name% OR e.lastName like %:name%")
	public List<EmployeeDTO> serach(@Param("name") String name, @Param("teamId") Integer teamId);
	
	@Query(value = "SELECT NEW com.resource.common.dto.EmployeeDTO(e.id, e.firstName, e.lastName, e.employeeCode) FROM Employee e LEFT JOIN TeamMember m ON e.id = m.employee.id WHERE m.team.id != :teamId OR m.team IS NULL")
	public List<EmployeeDTO> serach(@Param("teamId") Integer teamId);
	
	@Query(value = "SELECT e FROM Employee e WHERE e.company =:company AND e.employeeCode =:code")
	public Employee findByCode(@Param("code") String code, @Param("company") Company company);
}
