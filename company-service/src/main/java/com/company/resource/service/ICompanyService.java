package com.company.resource.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.resource.common.model.Company;
import com.resource.common.service.PagingModelService;

public interface ICompanyService extends PagingModelService<Company> {

	public Page<Company> findByName(Pageable pageable, String name);
	public List<Company> all();
}
