package com.resource.common.reporsitory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.resource.common.model.SpringSessionAttribute;

public interface ISessionAttributesRepo extends JpaRepository<SpringSessionAttribute, Integer> {
	
	@Transactional
	@Modifying
	@Query("DELETE FROM SpringSessionAttribute s WHERE s.sessionPrimaryId = ?1")
	public Long deleteBySessionId(String sessionPrimaryId);

}
