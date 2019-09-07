package com.resource.common.session;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;

import com.resource.common.config.ResourceSalt;
import com.resource.common.service.ISessionService;

public class SessionClearer {
	
	private ISessionService service;
	
	public SessionClearer(ISessionService service) {
		this.service = service;
	}
	
	public void clear(HttpServletRequest request) {
		Cookie[] cookies = request.getCookies();
		if(cookies == null)
			return;
		
		for (int i = 0; i < cookies.length; i++) {
			if(StringUtils.equals(cookies[i].getName(), "resource_name")) {
				String username = ResourceSalt.decrypt(cookies[i].getName(), "username");
				if(username != null) {
					service.clearSessionAttributes(username);
					service.clearSession(username);
				}
			}
		}
		
	}

}
