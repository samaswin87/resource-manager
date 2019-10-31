package com.resource.common.service;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.resource.common.model.Company;

@Component
public abstract class CompanyBaseService {

	@Autowired 
	private HttpSession httpSession;
	
	public Company company() {
		return (Company) httpSession.getAttribute("currentCompany");
	}
}
