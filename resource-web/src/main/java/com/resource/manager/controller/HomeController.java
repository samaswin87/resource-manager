package com.resource.manager.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.resource.common.session.CookieService;

@Controller
public class HomeController {

	@RequestMapping(value = "/home", method = RequestMethod.GET)
    public String home(ModelMap model, HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		CookieService.setSessionCookie(request, response);
        return "home.html";
    }
	
}
