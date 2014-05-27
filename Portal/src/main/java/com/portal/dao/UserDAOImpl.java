package com.portal.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import com.portal.entity.Group;
import com.portal.entity.User;

@Repository
public class UserDAOImpl implements UserDAO {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	private Session openSession() {	
		return sessionFactory.openSession();
	}

	public User getUser(String login) {
		List<User> users = new ArrayList<User>();
		Query query = openSession().createQuery("from User u where u.login = :login");
		query.setParameter("login", login);

		users = query.list();
		
		if (users != null && users.size() > 0)
			return users.get(0);
		else
			return null;	
	}
	
	public void setUserGroup(String login, Long groupId) {
		
		Query groupQuery = openSession().createQuery("from Group g where g.id = :groupId");
		groupQuery.setParameter("groupId", groupId);
		
		List<Group> groups = groupQuery.list();
		
		if (groups != null && groups.size() > 0) {
			
			Group groupToSet = groups.get(0);
			
			Query query = openSession().createQuery("update User u set u.group = :groupToSet where u.login = :login");
			query.setParameter("login", login);
			query.setParameter("groupToSet", groupToSet);
			query.executeUpdate();
		}

	}
	
	public void addUser(User user) {
		
	}

}
