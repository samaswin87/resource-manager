package com.company.resource.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.company.resource.repository.ICompanyTeamLeaderRepo;
import com.resource.common.model.Team;
import com.resource.common.model.TeamLeader;

@Service
public class CompanyTeamLeaderSerrvice implements ICompanyTeamLeaderService {

	@Autowired
	private ICompanyTeamLeaderRepo repo;
	
	@Override
	public List<TeamLeader> findAllByDate(Date date, Team team) {
		return repo.findAll(date, team);
	}

}
