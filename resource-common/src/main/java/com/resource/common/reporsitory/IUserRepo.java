package com.resource.common.reporsitory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import com.resource.common.model.User;

public interface IUserRepo extends JpaRepository<User, Integer> {
	
	@Transactional
	public User findByUsername(String userName);

}
