package com.portal.dao.implementation;

import com.portal.dao.interfaces.GroupDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.portal.entity.Group;

import java.util.ArrayList;
import java.util.List;


@Repository
public class GroupDAOImpl implements GroupDAO {
	
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
        // TODO
        return new ArrayList<Group>();
    }

    @Override
    public void add(Group group) {
        // TODO save it
    }

    @Override
    public void edit(Group group, String name, String description) {
        group.setName(name);
        group.setDescription(description);
        // TODO save it
    }

    @Override
    public void delete(Group group) {
        //TODO
    }

}
