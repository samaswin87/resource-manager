package com.resource.common.service;

import com.resource.common.model.User;

public interface ISessionService {

	public User getActiveUser(String username);
}
