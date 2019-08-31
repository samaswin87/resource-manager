package com.company.api.repository;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.resource.common.model.Company;

@Repository
public class CompanyRepoImpl implements CompanyRepo {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public void create(Company company) {
		Session session = sessionFactory.getCurrentSession();
		session.save(company);
	}

	@Override
	public List<Company> get() {
		Session session = sessionFactory.getCurrentSession();
		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<Company> criteria = builder.createQuery(Company.class);
		criteria.from(Company.class);
		List<Company> companies = session.createQuery(criteria).getResultList();
		return companies;
	}

	@Override
	public Company findById(int id) {
		Session session = sessionFactory.getCurrentSession();
		Company company = (Company) session.get(Company.class, id);
		return company;
	}

	@Override
	public Company update(Company company, int id) {
		Session session = sessionFactory.getCurrentSession();
		Company existingCompany = (Company) session.get(Company.class, id);
		session.update(existingCompany);
		return company;
	}

	@Override
	public void delete(int id) {
		Session session = sessionFactory.getCurrentSession();
		Company company = findById(id);
		session.delete(company);
	}

}
