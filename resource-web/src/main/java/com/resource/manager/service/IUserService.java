package com.resource.manager.service;

import com.resource.common.model.User;

public interface IUserService {
	public User getActiveUser(String userName);
}
