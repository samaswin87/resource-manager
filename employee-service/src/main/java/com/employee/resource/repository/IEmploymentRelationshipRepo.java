package com.employee.resource.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.resource.common.model.EmploymentRelationship;

public interface IEmploymentRelationshipRepo extends JpaRepository<EmploymentRelationship, Integer>{

}
