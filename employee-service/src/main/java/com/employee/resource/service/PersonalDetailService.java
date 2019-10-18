package com.employee.resource.service;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.employee.resource.repository.IPersonalDetailRepo;
import com.resource.common.model.PersonalDetail;

@Service("personalService")
@Transactional
public class PersonalDetailService implements IPersonalDetailService {

	@Autowired
    private IPersonalDetailRepo personalRepo;

	@Override
	public PersonalDetail create(PersonalDetail personalDetail) {
		return personalRepo.save(personalDetail);
	}

	@Override
	public PersonalDetail findById(int id) {
		Optional<PersonalDetail> personalDetail = personalRepo.findById(id);
		return personalDetail.orElse(null);
	}

	@Override
	public PersonalDetail update(PersonalDetail personalDetail) {
		return personalRepo.save(personalDetail);
	}

	@Override
	public void delete(int id) {
		personalRepo.deleteById(id);
	}

	@Override
	public PersonalDetail findByEmployeeCode(String employeeCode) {
		return personalRepo.findByEmployeeCode(employeeCode);
	}

}
