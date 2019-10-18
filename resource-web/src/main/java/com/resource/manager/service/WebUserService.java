package com.resource.manager.service;

import java.util.Arrays;

import javax.transaction.Transactional;

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
	
	@Transactional
	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		com.resource.common.model.User activeUser = service.getActiveUser(userName);
		GrantedAuthority authority = new SimpleGrantedAuthority(activeUser.getRoles().get(0).getRoleName());
		UserDetails userDetails = (UserDetails)new User(activeUser.getUsername(), activeUser.getPassword(), Arrays.asList(authority));
		return userDetails;
	}
}
