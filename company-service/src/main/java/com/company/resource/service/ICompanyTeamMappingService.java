package com.company.resource.service;

import java.util.Date;

import com.resource.common.model.TeamMapping;

public interface ICompanyTeamMappingService {

	public TeamMapping findByDate(Date date, Integer teamId);
}
