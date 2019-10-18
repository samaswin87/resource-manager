package com.resource.common.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.resource.common.model.User;
import com.resource.common.reporsitory.IUserRepo;

@Service
public class SessionService implements ISessionService {
	
	
	@Autowired
	private IUserRepo userRepo;

	
	@Override
	public User getActiveUser(String username) {
		return userRepo.findByUsername(username);
	}

}
