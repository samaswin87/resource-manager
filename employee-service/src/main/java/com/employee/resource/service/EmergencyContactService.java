package com.employee.resource.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.employee.resource.repository.IEmergencyContactRepo;
import com.resource.common.model.EmergencyContact;

@Service("contactService")
@Transactional
public class EmergencyContactService implements IEmergencyContactService {
	
	@Autowired
	IEmergencyContactRepo repo;
	
	@Override
	public EmergencyContact create(EmergencyContact contact) {
		return repo.save(contact);
	}

	@Override
	public EmergencyContact findById(int id) {
		return repo.findById(id).orElse(null);
	}

	@Override
	public EmergencyContact update(EmergencyContact contact) {
		return repo.save(contact);
	}

	@Override
	public void delete(int id) {
		repo.deleteById(id);
	}

}
