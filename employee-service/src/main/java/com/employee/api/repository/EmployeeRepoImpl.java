package com.employee.api.repository;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.resource.common.model.Employee;

@Repository
public class EmployeeRepoImpl implements EmployeeRepo {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public void create(Employee employee) {
		Session session = sessionFactory.getCurrentSession();
		session.save(employee);
	}

	@Override
	public List<Employee> get() {
		Session session = sessionFactory.getCurrentSession();
		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<Employee> criteria = builder.createQuery(Employee.class);
		criteria.from(Employee.class);
		List<Employee> employees = session.createQuery(criteria).getResultList();
		return employees;
	}

	@Override
	public Employee findById(int id) {
		Session session = sessionFactory.getCurrentSession();
		Employee employee = (Employee) session.get(Employee.class, id);
		return employee;
	}

	@Override
	public Employee update(Employee employee, int id) {
		Session session = sessionFactory.getCurrentSession();
		Employee existingEmployee = (Employee) session.get(Employee.class, id);
		existingEmployee.setBirthDate(employee.getBirthDate());
		existingEmployee.setBloodGroup(employee.getBloodGroup());
		existingEmployee.setEmail(employee.getEmail());
		existingEmployee.setEmployeeCode(employee.getEmployeeCode());
		existingEmployee.setFirstName(employee.getFirstName());
		existingEmployee.setGender(employee.getGender());
		existingEmployee.setLastName(employee.getLastName());
		existingEmployee.setMartialStatus(employee.getMartialStatus());
		existingEmployee.setMiddleName(employee.getMiddleName());
		existingEmployee.setNationalId(employee.getNationalId());
		session.update(existingEmployee);
		return employee;
	}

	@Override
	public void delete(int id) {
		Session session = sessionFactory.getCurrentSession();
		Employee employee = findById(id);
		session.delete(employee);
	}

}
