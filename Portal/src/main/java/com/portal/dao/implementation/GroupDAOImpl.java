package com.portal.dao.implementation;

import com.portal.dao.interfaces.GroupDAOI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.portal.entity.Group;

import java.util.List;


@Repository
public class GroupDAOImpl implements GroupDAOI {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	private Session getCurrentSession() {
		return sessionFactory.getCurrentSession();
	}

	public Group get(Long id) {
		Group group = (Group) getCurrentSession().load(Group.class, id);
		return group;
	}

    @Override
    public List<Group> findAll() {
		Query query = getCurrentSession().createQuery("from Group");
		return query.list();
    }

    @Override
    public void add(Group group) {
    	getCurrentSession().persist(group);
    }

    @Override
    public void edit(Group group, String name, String description) {
        group.setName(name);
        group.setDescription(description);
        getCurrentSession().merge(group);
    }

    @Override
    public void delete(Group group) {
    	getCurrentSession().delete(group);
    }

}
