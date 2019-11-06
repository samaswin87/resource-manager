package com.company.resource.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.company.resource.repository.ICompanyTeamMappingRepo;
import com.resource.common.model.TeamMapping;

@Service
public class CompanyTeamMappingService implements ICompanyTeamMappingService {

	@Autowired
	private ICompanyTeamMappingRepo repo;
	
	@Override
	public TeamMapping findByDate(Date date, Integer teamId) {
		return repo.findByTeamAndDate(date, teamId).stream().findFirst().orElse(null);
	}

	@Override
	public TeamMapping update(TeamMapping mapping) {
		return repo.save(mapping);
	}

}
