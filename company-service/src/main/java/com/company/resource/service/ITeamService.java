package com.company.resource.service;

import java.util.List;

import com.company.resource.model.TeamTree;
import com.resource.common.model.Team;

public interface ITeamService {

	public List<Team> list();

	public List<TeamTree> createTeamTree();
	
	public Team find(Integer id);
	
	public Team create(Team team);
}
