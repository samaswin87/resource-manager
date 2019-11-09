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

import com.company.resource.service.ICompanyTeamLeaderService;
import com.company.resource.service.IEmployeeService;
import com.company.resource.service.ITeamService;
import com.resource.common.controller.AdminController;
import com.resource.common.dto.EmployeeDTO;
import com.resource.common.model.Employee;
import com.resource.common.model.Team;
import com.resource.common.model.TeamLeader;
import com.resource.common.routes.AdminPath;

@Controller
public class TeamLeadersController extends AdminController{

	@Autowired
	private ITeamService service;

	@Autowired
	private IEmployeeService employeeService;

	@Autowired
	private ICompanyTeamLeaderService leaderService;

	@GetMapping(value = "/company/teams/{id}/leaders")
	public String viewLeaders(ModelMap map, @PathVariable("id") Integer id) {
		Team team = service.find(id);
		List<TeamLeader> leaders = leaderService.findAllByDate(new Date(), team);
		map.addAttribute("team", team);
		map.addAttribute("leaders", leaders);
		return AdminPath.team_leaders_show.partial();
	}

	@GetMapping(value = "/company/teams/{id}/leaders/new", headers = "Accept=application/json")
	public String add(ModelMap map, @PathVariable("id") Integer id) {
		Team team = service.find(id);
		map.addAttribute("team", team);
		return AdminPath.team_leaders_new.partial();
	}

	@PostMapping(value = "/company/teams/{id}/leaders/add")
	public ResponseEntity<String> create(ModelMap map, @PathVariable("id") Integer id, @RequestBody EmployeeDTO employeeDTO) {
		Team team = service.find(id);
		if(team == null || StringUtils.isEmpty(employeeDTO.getEmployeeCode())) {
			return new ResponseEntity<String>("Please provide valid params", HttpStatus.BAD_REQUEST);
		}
		Employee employee = employeeService.findByCode(employeeDTO.getEmployeeCode());
		if(employee == null) {
			return new ResponseEntity<String>("Please provide valid employee Id", HttpStatus.BAD_REQUEST);
		}
		TeamLeader leader = new TeamLeader();
		leader.setEmployee(employee);
		leader.setStartDate(new Date());
		leader.setIsApprover(false);
		leader.setTeam(team);
		leaderService.add(leader);
		return new ResponseEntity<String>(AdminPath.team_leaders.path(), HttpStatus.OK);
	}

	@GetMapping(value = "/company/teams/{id}/leaders/{leaderId}/edit")
	public String edit(ModelMap map, @PathVariable("id") Integer id, @PathVariable("leaderId") Integer leaderId) {
		TeamLeader leader = leaderService.find(leaderId);
		if (leader == null)
			return AdminPath.team_leaders.redirect();

		map.addAttribute("leader", leader);
		map.addAttribute("employee", leader.getEmployee());
		map.addAttribute("team", service.find(id));
		return AdminPath.team_leaders_edit.partial();
	}

	@PostMapping(value = "/company/teams/{id}/leaders/{leaderId}/edit")
	public String update(ModelMap map, @PathVariable("id") Integer id, @PathVariable("leaderId") Integer leaderId, @Valid @ModelAttribute("leader") TeamLeader leader, BindingResult bindingResult) {
		TeamLeader existingLeader = leaderService.find(leaderId);
		if(bindingResult.hasErrors()) {
			map.addAttribute("team", service.find(id));
			map.addAttribute("leader", leader);
			map.addAttribute("employee", existingLeader.getEmployee());
			return AdminPath.team_leaders_edit.partial();
		}

		existingLeader.setIsApprover(leader.getIsApprover());
		existingLeader.setStartDate(leader.getStartDate());
		existingLeader.setEndDate(leader.getEndDate());
		leaderService.update(existingLeader);

		return AdminPath.team_leaders_show.redirect();
	}

	@DeleteMapping(value = "/company/teams/{id}/leaders/{leaderId}/delete")
	public String delete(@PathVariable("id") Integer id, ModelMap map, @PathVariable("leaderId") Integer leaderId) {
		TeamLeader leader = leaderService.find(leaderId);
		if (leader != null) {
			leaderService.delete(leaderId);
		}
		return AdminPath.team_leaders_show.redirect();
	}
}
