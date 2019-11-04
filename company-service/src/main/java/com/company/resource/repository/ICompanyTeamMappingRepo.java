package com.company.resource.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.resource.common.model.TeamMapping;

public interface ICompanyTeamMappingRepo extends JpaRepository<TeamMapping, Integer> {

	@Query("SELECT m FROM TeamMapping m "
			+ "WHERE team.id = :teamId "
			+ "AND (m.startDate <= :date AND m.endDate IS NULL) "
			+ "OR (m.startDate <= :date AND m.endDate >= :date) ")
	public List<TeamMapping> findByTeamAndDate(@Param("date") Date date, @Param("teamId")  Integer teamId);

}
