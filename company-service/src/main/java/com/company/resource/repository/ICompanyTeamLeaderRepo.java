package com.company.resource.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.resource.common.model.Team;
import com.resource.common.model.TeamLeader;

public interface ICompanyTeamLeaderRepo extends JpaRepository<TeamLeader, Integer>{

	@Query("SELECT l FROM TeamLeader l WHERE l.team = :team "
			+ "AND (l.startDate <= :date AND l.endDate IS NULL) "
			+ "OR (l.startDate <= :date AND l.endDate >= :date)")
	public List<TeamLeader> findAll(@Param("date") Date date, @Param("team") Team team);

}
