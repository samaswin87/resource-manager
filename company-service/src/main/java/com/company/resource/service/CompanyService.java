package com.company.resource.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.company.resource.repository.ICompanyRepo;
import com.resource.common.model.Company;

@Service("companyService")
@Transactional
public class CompanyService implements ICompanyService {

	@Autowired
	ICompanyRepo companyRepo;
	
	@Override
	public Company create(Company company) {
		return companyRepo.save(company);
	}

	@Override
	public List<Company> get() {
		return (List<Company>) companyRepo.findAll();
	}
	
	@Override
	public List<Company> all() {
		return (List<Company>) companyRepo.all();
	}

	@Override
	public Company findById(int id) {
		return companyRepo.findById(id).orElse(null);
	}

	@Override
	public Company update(Company employee) {
		return companyRepo.save(employee);
	}

	@Override
	public void delete(int id) {
		companyRepo.deleteById(id);
	}

	@Override
	public Page<Company> get(Pageable page) {
		return companyRepo.findAll(page);
	}
	
	@Override
	public Page<Company> findByName(Pageable page, String name) {
		return companyRepo.findByName(page, name);
	}

}
