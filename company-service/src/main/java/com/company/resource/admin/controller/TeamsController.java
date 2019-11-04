package com.company.resource.admin.controller;

import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.company.resource.model.TeamTree;
import com.company.resource.service.ICompanyTeamLeaderService;
import com.company.resource.service.ICompanyTeamMemberService;
import com.company.resource.service.ITeamService;
import com.resource.common.controller.AdminController;
import com.resource.common.model.Team;
import com.resource.common.model.TeamLeader;
import com.resource.common.model.TeamMember;
import com.resource.common.routes.AdminPath;

@Controller
public class TeamsController  extends AdminController {
	
	@Autowired 
	private ITeamService service;
	
	@Autowired
	private ICompanyTeamMemberService memberService;
	
	@Autowired
	private ICompanyTeamLeaderService leaderService;

	@GetMapping(value = "/company/teams")
	public String list(ModelMap map) {
		return AdminPath.team_list.partial();
	}
	
	@GetMapping(value = "/company/teams/new")
	public String add(ModelMap map) {
		map.addAttribute("team", new Team());
		return AdminPath.team_add.partial();
	}
	
	@PostMapping(value = "/company/teams/new")
	public String add(ModelMap map, @Valid @ModelAttribute("team") Team team, BindingResult bindingResult) {
		if(bindingResult.hasErrors()) {
			map.addAttribute("team", team);
			return AdminPath.team_add.partial();
		}
		team.setCompany(currentCompany());
		service.create(team);
		return AdminPath.team_list.redirect();
	}
	
	@GetMapping(value = "/company/teams/{id}/edit")
	public String edit(ModelMap map, @PathVariable("id") Integer id) {
		Team team = service.find(id);
		map.addAttribute("team", team);
		return AdminPath.team_edit.partial();
	}
	
	@PostMapping(value = "/company/teams/{id}/edit")
	public String update(ModelMap map, @PathVariable("id") Integer id, @Valid @ModelAttribute("team") Team team, BindingResult bindingResult) {
		if(bindingResult.hasErrors()) {
			map.addAttribute("team", team);
			return AdminPath.team_edit.partial();
		}
		team.setCompany(currentCompany());
		team.setId(id);
		service.create(team);
		return AdminPath.team_list.redirect();
	}
	
	@GetMapping(value = "/company/teams/tree", headers = "Accept=application/json")
	public ResponseEntity<List<TeamTree>> teamTrees() {
		List<TeamTree> teamTrees = service.createTeamTree();
		return ResponseEntity.ok().body(teamTrees);
	}
	
	@GetMapping(value = "/company/teams/{id}/tree/members", headers = "Accept=application/json")
	public String members(ModelMap map, @PathVariable("id") Integer id) {
		Team team = service.find(id);
		List<TeamMember> members = memberService.findAllByDate(new Date(), team);
		List<TeamLeader> leaders = leaderService.findAllByDate(new Date(), team);
		map.addAttribute("team", team);
		map.addAttribute("members", members);
		map.addAttribute("leaders", leaders);
		return AdminPath.team_members.partial();
	}
	
	@GetMapping(value = "/company/teams/{id}/members")
	public String viewMembers(ModelMap map, @PathVariable("id") Integer id) {
		Team team = service.find(id);
		List<TeamMember> members = memberService.findAllByDate(new Date(), team);
		List<TeamLeader> leaders = leaderService.findAllByDate(new Date(), team);
		map.addAttribute("team", team);
		map.addAttribute("members", members);
		map.addAttribute("leaders", leaders);
		return AdminPath.team_members_show.partial();
	}
}
