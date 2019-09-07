package com.resource.manager.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.resource.common.service.ISessionService;
import com.resource.common.session.SessionClearer;

@Controller
public class LoginController {
	
	@Autowired
	private ISessionService service;
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
    public String redirect() {
        return "login";
    }
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(HttpServletRequest request) {
		SessionClearer clearer = new SessionClearer(service);
		clearer.clear(request);
        return "login";
    }
	
	@RequestMapping(value="/logout", method = RequestMethod.GET)
    public String logoutPage (HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null){   
        	SessionClearer clearer = new SessionClearer(service);
    		clearer.clear(request);
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return "redirect:/login?logout=true";
    }

}
