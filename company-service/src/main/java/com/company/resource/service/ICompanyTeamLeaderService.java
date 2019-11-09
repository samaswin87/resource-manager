package com.company.resource.service;

import java.util.Date;
import java.util.List;

import com.resource.common.model.Team;
import com.resource.common.model.TeamLeader;

public interface ICompanyTeamLeaderService {

	public List<TeamLeader> findAllByDate(Date date, Team team);

	public void add(TeamLeader leader);

	public TeamLeader find(Integer leaderId);

	public void update(TeamLeader leader);

	public void delete(Integer leaderId);
}
