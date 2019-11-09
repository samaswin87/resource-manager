package com.company.resource.admin.controller;

import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.company.resource.service.ICompanyTeamMappingService;
import com.company.resource.service.ICompanyTeamMemberService;
import com.company.resource.service.IEmployeeService;
import com.company.resource.service.ITeamService;
import com.resource.common.controller.AdminController;
import com.resource.common.dto.EmployeeDTO;
import com.resource.common.model.Employee;
import com.resource.common.model.Team;
import com.resource.common.model.TeamMapping;
import com.resource.common.model.TeamMember;
import com.resource.common.routes.AdminPath;

@Controller
public class TeamMembersController extends AdminController {

	@Autowired
	private ITeamService service;

	@Autowired
	private IEmployeeService employeeService;

	@Autowired
	private ICompanyTeamMemberService memberService;
	
	@Autowired 
	private ICompanyTeamMappingService mappingService;
	
	@GetMapping(value = "/company/teams/{id}/members")
	public String viewMembers(ModelMap map, @PathVariable("id") Integer id) {
		Team team = service.find(id);
		List<TeamMember> members = memberService.findAllByDate(new Date(), team);
		map.addAttribute("team", team);
		map.addAttribute("members", members);
		return AdminPath.team_members_show.partial();
	}

	@GetMapping(value = "/company/teams/{id}/members/new", headers = "Accept=application/json")
	public String add(ModelMap map, @PathVariable("id") Integer id) {
		Team team = service.find(id);
		map.addAttribute("team", team);
		return AdminPath.team_members_new.partial();
	}
	
	@PostMapping(value = "/company/teams/{id}/members/add")
	public ResponseEntity<String> create(ModelMap map, @PathVariable("id") Integer id, @RequestBody EmployeeDTO employeeDTO) {
		Team team = service.find(id);
		if(team == null || StringUtils.isEmpty(employeeDTO.getEmployeeCode())) {
			return new ResponseEntity<String>("Please provide valid params", HttpStatus.BAD_REQUEST);
		}
		Employee employee = employeeService.findByCode(employeeDTO.getEmployeeCode());
		if(employee == null) {
			return new ResponseEntity<String>("Please provide valid employee Id", HttpStatus.BAD_REQUEST);
		}
		TeamMember member = new TeamMember();
		member.setEmployee(employee);
		member.setActive(true);
		member.setIsPrimary(true);
		member.setStartDate(new Date());
		member.setTeam(team);
		member.setTitle("Not Set");
		memberService.add(member);
		
		TeamMapping mapping = mappingService.findByDate(new Date(), team.getId());
		mapping.setNoOfEmployees(mapping.getNoOfEmployees() + 1);
		mappingService.update(mapping);
		return new ResponseEntity<String>(AdminPath.team_members.path(), HttpStatus.OK);
	}

	@GetMapping(value = "/company/teams/{id}/members/{memberId}/edit")
	public String edit(ModelMap map, @PathVariable("id") Integer id, @PathVariable("memberId") Integer memberId) {
		TeamMember member = memberService.find(memberId);
		if (member == null)
			return AdminPath.team_members.redirect();
		
		map.addAttribute("member", memberService.find(memberId));
		map.addAttribute("employee", member.getEmployee());
		map.addAttribute("team", service.find(id));
		return AdminPath.team_members_edit.partial();
	}
	
	@PostMapping(value = "/company/teams/{id}/members/{memberId}/edit")
	public String update(ModelMap map, @PathVariable("id") Integer id, @PathVariable("memberId") Integer memberId, @Valid @ModelAttribute("member") TeamMember member, BindingResult bindingResult) {
		if(bindingResult.hasErrors()) {
			TeamMember existingMember = memberService.find(memberId);
			map.addAttribute("team", service.find(id));
			map.addAttribute("member", member);
			map.addAttribute("employee", existingMember.getEmployee());
			return AdminPath.team_members_edit.partial();
		}
		
		TeamMember existingMember = memberService.find(memberId);
		existingMember.setStartDate(member.getStartDate());
		existingMember.setEndDate(member.getEndDate());
		existingMember.setTitle(member.getTitle());
		memberService.update(existingMember);
		
		return AdminPath.team_members_show.redirect();
	}
	
	@DeleteMapping(value = "/company/teams/{id}/members/{memberId}/delete")
	public String delete(@PathVariable("id") Integer id, ModelMap map, @PathVariable("memberId") Integer memberId) {
		TeamMember member = memberService.find(memberId);
		if (member != null) {
			memberService.delete(memberId);
		}
		return AdminPath.team_members_show.redirect();
	}
	
}
