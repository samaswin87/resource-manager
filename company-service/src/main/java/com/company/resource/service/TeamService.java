package com.company.resource.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.company.resource.model.TeamTree;
import com.company.resource.repository.ICompanyTeamRepo;
import com.resource.common.model.Team;
import com.resource.common.model.TeamMapping;
import com.resource.common.service.CompanyBaseService;

@Service
public class TeamService extends CompanyBaseService implements ITeamService {
	
	@Autowired
	private ICompanyTeamRepo repo;

	@Autowired 
	private ICompanyTeamMappingService mappingService;
	
	@Override
	public List<Team> list() {
		return repo.findAllByCompany(company());
	}
	
	public List<TeamTree> createTeamTree() {
		List<TeamTree> teamTrees = new ArrayList<>();
		CopyOnWriteArrayList<Team> teams = new CopyOnWriteArrayList<Team>();
		teams.addAll(list());
		for (int index = 0; index < teams.size(); index++) {
			Team team = teams.get(index);
			TeamTree tree = setTeamTree(team);
			List<TeamTree> childTrees = setChildTree(team, teams);
			tree.setNodes(childTrees);
			teamTrees.add(tree);
		}
		return teamTrees;
	}
	
	private TeamTree setTeamTree(Team team) {
		TeamMapping mapping = mappingService.findByDate(new Date(), team.getId());
		TeamTree teamTree = new TeamTree();
		teamTree.setText(team.getDisplayName());
		teamTree.setCode(team.getCode());
		teamTree.setId("team_"+ team.getId());
		if (mapping != null)
			teamTree.setTags(new String[] {mapping.getNoOfEmployees().toString()});
		return teamTree;
	}
	
	private List<TeamTree> setChildTree(Team team, CopyOnWriteArrayList<Team> teams) {
		List<TeamTree> childTrees = new ArrayList<TeamTree>();
		List<TeamMapping> childTeamMappings;
		
		if (!(childTeamMappings = team.getChildTeamMappings()).isEmpty()) {
			
			for (int index = 0; index < childTeamMappings.size(); index++) {
				TeamMapping childTeamMapping = childTeamMappings.get(index);
				Team chileTeam = childTeamMapping.getTeam();
				TeamTree childTree = setTeamTree(chileTeam);
				childTrees.add(childTree);
				
				// recursive child tree mapping
				if (!chileTeam.getChildTeamMappings().isEmpty()) {
					List<TeamTree> childTreeNodes = setChildTree(chileTeam, teams);
					childTree.setNodes(childTreeNodes);
				}
				teams.remove(chileTeam); // Will remove the team from the list
			}
		}
		return childTrees;
	}

	@Override
	public Team find(Integer id) {
		return repo.findById(id).orElse(null);
	}

	@Override
	public Team create(Team team) {
		return repo.save(team);
	}
}
