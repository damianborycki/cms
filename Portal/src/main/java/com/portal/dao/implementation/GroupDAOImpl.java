package com.portal.dao.implementation;

import com.portal.dao.interfaces.GroupDAOI;

import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.portal.entity.Group;

import java.util.List;


@Repository
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class GroupDAOImpl implements GroupDAOI {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	private Session getCurrentSession() {
		return sessionFactory.getCurrentSession();
	}

    private Session openSession() {
        return sessionFactory.openSession();
    }

	public Group get(Long id) {
		Group group = (Group) openSession().load(Group.class, id);
		return group;
	}

    @Override
    public List<Group> findAll() {
		Query query = openSession().createQuery("from Group");
		return query.list();
    }

    @Override
    public void add(Group group) {
        openSession().save(group);
    }

    @Override
    public void edit(Group group) { 	
    	Session session = openSession();
    	Transaction t = session.beginTransaction();
    	session.merge(group);
    	t.commit();
    }

    @Override
    public void delete(Group group) {
        openSession().delete(group);
    }

}
