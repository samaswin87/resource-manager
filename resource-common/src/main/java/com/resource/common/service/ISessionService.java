package com.resource.common.service;

import com.resource.common.model.SpringSession;
import com.resource.common.model.User;

public interface ISessionService {

	public void clearSession(String username);
	public void clearSessionAttributes();
	public void clearSession();
	public SpringSession findSession(String username);
	public User getActiveUser(String username);
}
