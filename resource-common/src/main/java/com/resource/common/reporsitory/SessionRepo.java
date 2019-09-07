package com.resource.common.reporsitory;

import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.resource.common.model.SpringSession;
import com.resource.common.model.SpringSessionAttribute;
import com.resource.common.model.User;

@Repository
@Transactional
public class SessionRepo implements ISessionRepo {

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public void clearSession(String username) {
		Session session = sessionFactory.getCurrentSession();
		Query<SpringSession> query = session.createQuery("FROM SpringSession s WHERE s.principal_name = :name", SpringSession.class);
		query.setParameter("name", username);
		SpringSession userSession = query.uniqueResult();
		if(userSession != null) {
			session.delete(userSession);
		}
	}
	
	public void clearSessionAttributes(String username) {
		Session session = sessionFactory.getCurrentSession();
		Query<SpringSession> query = session.createQuery("FROM SpringSession s WHERE s.principal_name = :principal_name", SpringSession.class);
		query.setParameter("principal_name", username);
		SpringSession userSession = query.uniqueResult();
		if(userSession != null) {
			Query<SpringSessionAttribute> attrQuery = session.createQuery("FROM SpringSessionAttribute s WHERE s.session_primary_id = :id", SpringSessionAttribute.class);
			attrQuery.setParameter("id", userSession.getPrimaryId());
			List<SpringSessionAttribute> userSessionAttributes = attrQuery.getResultList();
			if(!userSessionAttributes.isEmpty()) {
				List<String> attributeNames = userSessionAttributes.stream().map(s -> s.getAttributeName()).collect(Collectors.toList());
				Query<?> removeQuery = session.createQuery("DELETE SpringSessionAttribute s WHERE attributeName IN (:attributeNames)");
				removeQuery.setParameter("attributeNames", attributeNames);
				removeQuery.executeUpdate();
			}
		}
	}
	
	@Override
	public void clearSession() {
		Session session = sessionFactory.getCurrentSession();
		Query<SpringSession> query = session.createQuery("FROM SpringSession", SpringSession.class);
		List<SpringSession> sessions = query.getResultList();
		if(!sessions.isEmpty())
		{
			Query<?> removeQuery = session.createQuery("DELETE SpringSession s WHERE primaryId IN (:primaryIds)");
			List<String> primaryIds = sessions.stream().map(s -> s.getPrimaryId()).collect(Collectors.toList());
			removeQuery.setParameter("primaryIds", primaryIds);
			removeQuery.executeUpdate();
		}
	}
	
	@Override
	public void clearSessionAttributes() {
		Session session = sessionFactory.getCurrentSession();
		Query<SpringSessionAttribute> query = session.createQuery("FROM SpringSessionAttribute", SpringSessionAttribute.class);
		List<SpringSessionAttribute> sessions = query.getResultList();
		if(!sessions.isEmpty())
		{
			Query<?> removeQuery = session.createQuery("DELETE SpringSessionAttribute s WHERE attributeName IN (:attributeNames)");
			List<String> attributeNames = sessions.stream().map(s -> s.getAttributeName()).collect(Collectors.toList());
			removeQuery.setParameter("attributeNames", attributeNames);
			removeQuery.executeUpdate();
		}
	}

	@Override
	public SpringSession findSession(String username) {
		Session session = sessionFactory.getCurrentSession();
		Query<?> query = session.createQuery("FROM SpringSession s WHERE principalName = :name");
		query.setParameter("name", username);
		SpringSession userSession = (SpringSession) query.uniqueResult();
		return userSession;
	}
	
	@Override
	public User getActiveUser(String username) {
		Session session = sessionFactory.getCurrentSession();
		Query<?> query = session.createQuery("FROM User u WHERE u.username = :name");
		query.setParameter("name", username);
		User user = (User) query.uniqueResult();
		return user;
	}
}
