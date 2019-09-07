package com.resource.common.session;

import java.security.Principal;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.resource.common.config.ResourceSalt;

public class CookieService {

	public static void setSessionCookie(HttpServletRequest request, HttpServletResponse response) {
		Principal principal = request.getUserPrincipal();
		Cookie cookie = new Cookie("resource_name", ResourceSalt.encrypt(principal.getName(), "username"));
		response.addCookie(cookie);
	}
}
