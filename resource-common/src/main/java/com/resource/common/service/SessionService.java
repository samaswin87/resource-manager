package com.resource.common.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.resource.common.model.SpringSession;
import com.resource.common.model.User;
import com.resource.common.reporsitory.ISessionRepo;

@Service
public class SessionService implements ISessionService {
	
	@Autowired
	private ISessionRepo repo;
	
	@Override
	public void clearSession(String username) {
		repo.clearSession(username);
	}
	
	@Override
	public void clearSession() {
		repo.clearSession();
	}

	@Override
	public void clearSessionAttributes() {
		repo.clearSessionAttributes();
	}
	
	public void clearSessionAttributes(String username) {
		repo.clearSessionAttributes(username);
	}
	
	@Override
	public SpringSession findSession(String username) {
		return repo.findSession(username);
	}
	
	@Override
	public User getActiveUser(String username) {
		return repo.getActiveUser(username);
	}

}
