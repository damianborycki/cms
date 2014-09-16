package com.portal.dao.implementation;

import com.portal.dao.interfaces.TagDAOI;
import com.portal.entity.Tag;
import com.portal.entity.TagType;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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
        sessionFactory.getCurrentSession().close();
        return list;
    }

    @Override
    public Tag get(Long id) {
        Tag tag = (Tag)openSession().load(Tag.class, id);
        sessionFactory.getCurrentSession().close();
        return tag;
    }

    @Override
    public List<Tag> getByIds(List<Long> ids) {
        Session session = openSession();
        List<Tag> tags = session.createQuery("FROM Tag WHERE id IN (:ids)").setParameter("ids", ids).list();
        session.close();
        return tags;
    }

    @Override
    public void create(Tag tag) {
        this.openSession().save(tag);
        sessionFactory.getCurrentSession().close();
    }

    @Override
    public void edit(Long id, Tag template) {
        Session session = openSession();
        Tag tag = (Tag) session.get(Tag.class, id);
        tag.setName(template.getName());
        tag.setDescription(template.getDescription());
        tag.setType(template.getType());

        session.update(tag);
        session.flush();
        sessionFactory.getCurrentSession().close();
    }

    @Override
    public void delete(Long id) {
        Session session = openSession();
        Tag tag = (Tag) session.get(Tag.class, id);
        session.delete(tag);
        session.flush();
        sessionFactory.getCurrentSession().close();
    }

    @Override
    public boolean existsForType(TagType tagType) {
        Session session = openSession();
        Criteria criteria = session.createCriteria(Tag.class);
        criteria.add(Restrictions.eq("type", tagType));
        boolean exists = !criteria.list().isEmpty();
        sessionFactory.getCurrentSession().close();
        return exists;
    }
}
