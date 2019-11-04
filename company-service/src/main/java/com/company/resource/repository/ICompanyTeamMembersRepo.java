package com.company.resource.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.resource.common.model.Team;
import com.resource.common.model.TeamMember;

public interface ICompanyTeamMembersRepo extends JpaRepository<TeamMember, Integer> {

	@Query("SELECT m FROM TeamMember m WHERE m.team = :team "
			+ "AND (m.startDate <= :date AND m.endDate IS NULL) "
			+ "OR (m.startDate <= :date AND m.endDate >= :date)")
	public List<TeamMember> findAll(@Param("date") Date date, @Param("team") Team team);

}
