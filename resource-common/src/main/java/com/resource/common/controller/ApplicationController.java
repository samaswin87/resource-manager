package com.resource.common.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.resource.common.model.Company;
import com.resource.common.model.Employee;
import com.resource.common.model.Profile;
import com.resource.common.model.User;
import com.resource.common.service.ISessionService;
import com.resource.common.utils.ObjectMapper;

@Controller
public class ApplicationController {
	
	@Autowired
	private ISessionService service;
	
	@Autowired 
	private HttpSession httpSession;
	
	@Autowired
	public ObjectMapper mapper;

	@ModelAttribute("currentCompany")
	public Company currentCompany() {
		
		Company company = null;
		
		if (httpSession.getAttribute("currentCompany") != null)
			company = (Company) httpSession.getAttribute("currentCompany");
		
		if (company == null) {
			company = currentEmployee().getCompany();
			httpSession.setAttribute("currentCompany", company);
		}
		
		return company;
	}

	@ModelAttribute("currentUser")
	public User currentUser() {
		User user = null;
		
		if (httpSession.getAttribute("currentUser") != null)
			user = (User) httpSession.getAttribute("currentUser");
		
		if (user == null) {
			Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			String username = principal.toString();
			
			if (principal instanceof UserDetails)
				username = ((UserDetails) principal).getUsername();
			
			user = service.getActiveUser(username);
		}
		
		return user;
	}

	@ModelAttribute("currentEmployee")
	public Employee currentEmployee() {
		Employee employee = null; 
		
		if (httpSession.getAttribute("currentEmployee") != null)
			employee = (Employee) httpSession.getAttribute("currentEmployee");
		
		if (employee == null)
			employee = currentUser().getEmployee();
		
		return employee;
	}

	@ModelAttribute("profile")
	public Profile profile() {
		Profile profile = null;
		
		if (httpSession.getAttribute("profile") != null)
			profile = (Profile) httpSession.getAttribute("profile");
		
		if (profile == null)
			profile = currentEmployee().getProfile();
		
		return profile;
	}

	@InitBinder
	protected void initBinder(HttpServletRequest request, ServletRequestDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		dateFormat.setLenient(false);
		binder.registerCustomEditor(Date.class, null, new CustomDateEditor(dateFormat, true));
	}
}
