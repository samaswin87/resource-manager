package com.resource.common.reporsitory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.resource.common.model.SpringSession;

public interface ISpringSessionRepo extends JpaRepository<SpringSession, Integer> {
	
	@Modifying
	@Transactional
	@Query("DELETE FROM SpringSession s where s.principalName = ?1")
    public Long deleteByPrincipalName(String userName);
	
	@Query("SELECT s FROM SpringSession s WHERE s.principalName = ?1")
	public SpringSession findByPrincipalName(String userName);

}
