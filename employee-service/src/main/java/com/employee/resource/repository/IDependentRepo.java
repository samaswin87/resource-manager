package com.employee.resource.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.resource.common.model.Dependent;

public interface IDependentRepo extends JpaRepository<Dependent, Integer>{

}
