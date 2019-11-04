package com.company.resource.admin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.company.resource.service.ICompanyTeamMemberService;
import com.company.resource.service.ITeamService;
import com.resource.common.controller.AdminController;
import com.resource.common.model.Team;
import com.resource.common.model.TeamMember;
import com.resource.common.routes.AdminPath;

@Controller
public class TeamMembersController extends AdminController {

	@Autowired 
	private ITeamService service;
	
	@Autowired
	private ICompanyTeamMemberService memberService;
	
	
	@GetMapping(value = "/company/teams/{id}/members/new")
	public String add(ModelMap map, @PathVariable("id") Integer id) {
		Team team = service.find(id);
		map.addAttribute("teamMembers", new TeamMember());
		map.addAttribute("team", team);
		return AdminPath.team_members_new.partial();
	}
	
}
