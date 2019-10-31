package com.company.resource.admin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

import com.resource.common.controller.AdminController;
import com.resource.common.routes.AdminPath;

@Controller
public class TeamsController  extends AdminController {

	@GetMapping(value = "/company/teams")
	public String list(ModelMap map) {
		return AdminPath.team_list.partial();
	}
}
