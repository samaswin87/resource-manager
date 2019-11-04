package com.company.resource.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.company.resource.repository.ICompanyTeamMembersRepo;
import com.resource.common.model.Team;
import com.resource.common.model.TeamMember;
import com.resource.common.service.CompanyBaseService;

@Service
public class CompanyTeamMemberService extends CompanyBaseService implements ICompanyTeamMemberService {

	@Autowired
	private ICompanyTeamMembersRepo repo;
	
	@Override
	public List<TeamMember> findAllByDate(Date date, Team team) {
		return repo.findAll(date, team);
	}

}
