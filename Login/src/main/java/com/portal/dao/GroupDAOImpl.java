package com.portal.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.portal.model.Group;


@Repository
public class GroupDAOImpl implements GroupDAO {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	private Session getCurrentSession() {
		return sessionFactory.getCurrentSession();
	}

	public Group getGroup(int id) {
		Group group = (Group) getCurrentSession().load(Group.class, id);
		return group;
	}

}
