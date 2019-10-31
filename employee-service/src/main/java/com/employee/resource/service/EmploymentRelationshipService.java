package com.employee.resource.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.employee.resource.repository.IEmploymentRelationshipRepo;
import com.resource.common.model.EmploymentRelationship;

@Service
public class EmploymentRelationshipService implements IEmploymentRelationshipService {

	@Autowired
	IEmploymentRelationshipRepo repo;
	
	@Override
	public EmploymentRelationship create(EmploymentRelationship relationship) {
		return repo.save(relationship);
	}

	@Override
	public EmploymentRelationship findById(int id) {
		return repo.findById(id).orElse(null);
	}

	@Override
	public EmploymentRelationship update(EmploymentRelationship relationship) {
		return repo.save(relationship);
	}

	@Override
	public void delete(int id) {
		repo.deleteById(id);
	}

}
