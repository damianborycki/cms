package com.portal.dao.implementation;

import com.portal.dao.interfaces.TagDAOI;
import com.portal.entity.Tag;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * User: Bartosz
 * Date: 2014-05-19
 * Time: 21:29
 */

@Component
public class TagDAOImpl implements TagDAOI {

    @Autowired
    private SessionFactory sessionFactory;

    private Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }

    private Session openSession() {
        return sessionFactory.openSession();
    }

    @Override
    public List<Tag> findAll() {
        List<Tag> list = openSession().createQuery("from Tag").list();
        return list;
    }

    @Override
    public Tag get(Long id) {
        Tag tag = (Tag)openSession().load(Tag.class, id);
        return tag;
    }
}
