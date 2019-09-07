package com.resource.common.reporsitory;

import com.resource.common.model.SpringSession;
import com.resource.common.model.User;

public interface ISessionRepo {

	public void clearSession(String username);
	public void clearSessionAttributes(String username);
	public void clearSession();
	public void clearSessionAttributes();
	public SpringSession findSession(String username);
	public User getActiveUser(String username);
}
