package com.company.resource.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.resource.common.model.Company;

public interface ICompanyRepo extends PagingAndSortingRepository<Company, Integer> {

	@Query(value = "SELECT c FROM Company c WHERE c.name like %:name%")
	Page<Company> findByName(Pageable pageable, @Param("name") String name);
	
	@Query(value = "SELECT new com.resource.common.model.Company(c.id, c.name) FROM Company c Order By c.name")
	List<Company> all();
}
