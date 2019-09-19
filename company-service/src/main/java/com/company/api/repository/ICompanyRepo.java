package com.company.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.resource.common.model.Company;

public interface ICompanyRepo extends JpaRepository<Company, Integer> {

}
