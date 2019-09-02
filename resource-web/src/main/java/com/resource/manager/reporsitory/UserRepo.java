package com.resource.manager.reporsitory;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.resource.common.model.User;

@Repository
@Transactional
public class UserRepo implements IUserRepo {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public User getActiveUser(String userName) {
		Session session = sessionFactory.getCurrentSession();
		Query<?> query = session.createQuery("FROM User u WHERE u.username = :name");
		query.setParameter("name", userName);
		User user = (User) query.uniqueResult();
		return user;
	}

}
