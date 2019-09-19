package com.resource.manager.service;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class WebUserService implements UserDetailsService {
	
	@Autowired
	IUserService service;
	
	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		com.resource.common.model.User activeUser = service.getActiveUser(userName);
		System.out.println(activeUser.getRole().getRoleName());
		GrantedAuthority authority = new SimpleGrantedAuthority(activeUser.getRole().getRoleName());
		UserDetails userDetails = (UserDetails)new User(activeUser.getUsername(), activeUser.getPassword(), Arrays.asList(authority));
		return userDetails;
	}
}
