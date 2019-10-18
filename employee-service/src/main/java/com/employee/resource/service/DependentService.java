package com.employee.resource.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.employee.resource.repository.IDependentRepo;
import com.resource.common.model.Dependent;

@Service("dependentService")
@Transactional
public class DependentService implements IDependentService {
	
	@Autowired
	IDependentRepo repo;
	
	@Override
	public Dependent create(Dependent dependent) {
		return repo.save(dependent);
	}

	@Override
	public Dependent findById(int id) {
		return repo.findById(id).orElse(null);
	}

	@Override
	public Dependent update(Dependent dependent) {
		return repo.save(dependent);	
	}

	@Override
	public void delete(int id) {
		repo.deleteById(id);
	}

}
