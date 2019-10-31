package com.company.resource.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.company.resource.repository.ICompanyShiftTypeRepo;
import com.resource.common.model.ShiftType;
import com.resource.common.service.CompanyBaseService;

@Service
public class ShiftTypeService extends CompanyBaseService implements IShiftTypeService{
	
	@Autowired
	ICompanyShiftTypeRepo repo;
	
	@Override
	public List<ShiftType> list() {
		return repo.findAll();
	}

	@Override
	public ShiftType create(String name) {
		ShiftType type = new ShiftType();
		type.setCompany(company());
		type.setName(name);
		return repo.save(type);
	}

}
