package com.company.resource.admin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.company.resource.service.ITeamService;
import com.resource.common.controller.AdminController;
import com.resource.common.model.Team;
import com.resource.common.model.TeamLeader;
import com.resource.common.routes.AdminPath;

@Controller
public class TeamLeadersController extends AdminController{

	@Autowired 
	private ITeamService service;
	
	@GetMapping(value = "/company/teams/{id}/leaders/new")
	public String add(ModelMap map, @PathVariable("id") Integer id) {
		Team team = service.find(id);
		map.addAttribute("teamLeaders", new TeamLeader());
		map.addAttribute("team", team);
		return AdminPath.team_leaders_new.partial();
	}
}
