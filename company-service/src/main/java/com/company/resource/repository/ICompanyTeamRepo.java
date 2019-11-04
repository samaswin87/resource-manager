package com.company.resource.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.resource.common.model.Company;
import com.resource.common.model.Team;

public interface ICompanyTeamRepo extends JpaRepository<Team, Integer> {

	@Query(value = "SELECT t FROM Team t WHERE t.company =:company")
	public List<Team> findAllByCompany(Company company);
}
