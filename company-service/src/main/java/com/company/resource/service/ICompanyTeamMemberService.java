package com.company.resource.service;

import java.util.Date;
import java.util.List;

import com.resource.common.model.Team;
import com.resource.common.model.TeamMember;

public interface ICompanyTeamMemberService {

	public List<TeamMember> findAllByDate(Date date, Team team);
	
	public TeamMember add(TeamMember member);
	
	public TeamMember find(Integer memberId);

	public void update(TeamMember member);

	public void delete(Integer id);
}
