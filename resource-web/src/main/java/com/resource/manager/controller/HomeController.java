package com.resource.manager.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.company.resource.service.ICompanyService;
import com.resource.common.controller.ApplicationController;
import com.resource.common.session.CookieService;

@Controller
public class HomeController extends ApplicationController {
	
	@Autowired
	ICompanyService companyService;

	@RequestMapping(value = "/home", method = RequestMethod.GET)
    public String home(ModelMap model, HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		CookieService.setSessionCookie(request, response);
		session.setAttribute("companies", companyService.all());
        return "home.html";
    }
	
	@RequestMapping(value = "/404", method = RequestMethod.GET)
    public String notFound(ModelMap model, HttpServletRequest request, HttpServletResponse response, HttpSession session) {
        return "404.html";
    }
}
