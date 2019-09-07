package com.employee.resource.config;

import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import com.resource.common.config.ResourceSalt;
import com.resource.common.model.SpringSession;
import com.resource.common.model.User;
import com.resource.common.service.ISessionService;

@Component("sessionFilter")
public class SessionFilter implements Filter {
	
	private ISessionService service;
	
	// refer https://stackoverflow.com/a/37971596/2634405
	@Resource(name="customAuthenticationManager")
    private AuthenticationManager authManager;
	
	public SessionFilter(ISessionService service) {
		this.service = service;
	}

	// refer: https://github.com/eugenp/tutorials/blob/master/spring-security-mvc-custom/src/main/java/org/baeldung/web/controller/LoginController.java
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		Cookie[] cookies = httpRequest.getCookies();
		for (int i = 0; i < cookies.length; i++) {
			if(StringUtils.equals(cookies[i].getName(), "resource_name")) {
				String username = ResourceSalt.decrypt(cookies[i].getName(), "username");
				User user = service.getActiveUser(username);
				SpringSession userSession = service.findSession(username);
				if(userSession != null) {
					HttpSession session = httpRequest.getSession(true);
					UsernamePasswordAuthenticationToken authReq = new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword());
				    Authentication auth = authManager.authenticate(authReq);
				    SecurityContext sc = SecurityContextHolder.getContext();
				    sc.setAuthentication(auth);
				    session.setAttribute("SPRING_SECURITY_CONTEXT", sc);
				}
			}
		}
		chain.doFilter(request, response);
	}

}