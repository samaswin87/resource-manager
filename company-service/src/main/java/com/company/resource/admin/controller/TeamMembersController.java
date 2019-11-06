package com.company.resource.admin.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

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

	@GetMapping(value = "/company/teams/{id}/members/new")
	public String add(ModelMap map, @PathVariable("id") Integer id) {
		Team team = service.find(id);
		map.addAttribute("teamMembers", new TeamMember());
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

	@GetMapping(value = "/company/teams/{id}/search/employees", headers = "Accept=application/json")
	public String searchList(@RequestParam("name") String name, @PathVariable("id") Integer id, ModelMap map) {
		Team team = service.find(id);
		List<EmployeeDTO> employeeDTOs = new ArrayList<>();
		if (StringUtils.isEmpty(name)) {
			employeeDTOs = employeeService.serach(id);
		} else {
			employeeDTOs = employeeService.serach(name, id);
		}
		List<Integer> teamEmployeeIds = team.getTeamMembers().stream().map(m -> m.getEmployee().getId())
				.collect(Collectors.toList());
		Iterator<EmployeeDTO> itr = employeeDTOs.iterator();
		while (itr.hasNext()) {
			EmployeeDTO employeeDTO = itr.next();

			if (teamEmployeeIds.contains(employeeDTO.getId())) {
				itr.remove();
			}

		}

		map.addAttribute("employees", employeeService.serach(id));
		return AdminPath.employee_search_list.partial();
	}

}
