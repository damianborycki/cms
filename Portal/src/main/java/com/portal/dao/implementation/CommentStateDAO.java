package com.portal.dao.implementation;

import com.portal.dao.interfaces.CommentStateDAOI;
import com.portal.entity.CommentState;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Mateusz on 2014-06-25.
 */
@Repository
public class CommentStateDAO implements CommentStateDAOI {

    @Autowired
    private SessionFactory sessionFactory;

    private Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }

    private Session openSession() {
        return sessionFactory.openSession();
    }

    @Override
    public CommentState get(Long id) {
        return (CommentState) openSession().load(CommentState.class, id);
    }

    @Override
    public List<CommentState> findAll() {
        return openSession().createQuery("FROM CommentState").list();
    }

    @Override
    public void add(CommentState commentState) {
        openSession().persist(commentState);
    }

    @Override
    public void edit(CommentState commentState, String name, String description) {
        commentState.setName(name);
        commentState.setDescription(description);
        openSession().merge(commentState);
    }

    @Override
    public void delete(CommentState commentState) {
        openSession().delete(commentState);
    }
}
