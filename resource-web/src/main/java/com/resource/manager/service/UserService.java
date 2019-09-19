package com.resource.manager.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.resource.common.model.User;
import com.resource.common.reporsitory.IUserRepo;

@Service
public class UserService implements IUserService {

	@Autowired
	private IUserRepo userRepo;
	
	@Override
	public User getActiveUser(String userName) {
		return userRepo.findByUsername(userName);
	}

}
