package com.company.resource.service;

import java.util.Date;
import java.util.List;

import com.resource.common.model.Team;
import com.resource.common.model.TeamLeader;

public interface ICompanyTeamLeaderService {

	public List<TeamLeader> findAllByDate(Date date, Team team);
}
