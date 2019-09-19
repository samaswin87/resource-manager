package com.resource.common.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.resource.common.model.SpringSession;
import com.resource.common.model.User;
import com.resource.common.reporsitory.ISessionAttributesRepo;
import com.resource.common.reporsitory.ISpringSessionRepo;
import com.resource.common.reporsitory.IUserRepo;

@Service
public class SessionService implements ISessionService {
	
	@Autowired
	private ISpringSessionRepo repo;
	
	@Autowired
	private ISessionAttributesRepo attrRepo;
	
	@Autowired
	private IUserRepo userRepo;
	
	@Override
	public void clearSession(String username) {
		SpringSession session = findSession(username);
		if(session != null) {
			repo.deleteByPrincipalName(username);
			attrRepo.deleteBySessionId(session.getPrimaryId());
		}
	}
	
	@Override
	public void clearSession() {
		repo.deleteAll();
	}
	
	@Override
	public SpringSession findSession(String username) {
		return repo.findByPrincipalName(username);
	}
	
	@Override
	public User getActiveUser(String username) {
		return userRepo.findByUsername(username);
	}

	@Override
	public void clearSessionAttributes() {
		attrRepo.deleteAll();
	}

}
