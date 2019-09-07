package com.company.api.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.company.api.repository.ICompanyRepo;
import com.resource.common.model.Company;

@Service
@Transactional
public class CompanyService implements ICompanyService {

	@Autowired
	ICompanyRepo companyRepo;

	
	@Override
	public void create(Company company) {
		companyRepo.create(company);
	}

	@Override
	public List<Company> get() {
		return companyRepo.get();
	}

	@Override
	public Company findById(int id) {
		return companyRepo.findById(id);
	}

	@Override
	public Company update(Company employee, int id) {
		return companyRepo.update(employee, id);
	}

	@Override
	public void delete(int id) {
		companyRepo.delete(id);
	}
	
	

}
