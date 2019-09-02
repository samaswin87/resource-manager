package com.resource.manager.reporsitory;

import com.resource.common.model.User;

public interface IUserRepo {

	public User getActiveUser(String userName);
}
