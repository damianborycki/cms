package com.portal.dao.implementation;

import com.portal.dao.interfaces.CategoryDAOI;
import com.portal.entity.Category;
import com.portal.entity.Comment;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * User: Bartosz
 * Date: 2014-05-19
 * Time: 20:39
 */

@Component
public class CategoryDAOImpl implements CategoryDAOI {

    @Autowired
    private SessionFactory sessionFactory;

    private Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }

    private Session openSession() {
        return sessionFactory.openSession();
    }

    @Override
    public Category get(Long id) {
        return (Category)openSession().get(Category.class, id);
    }

    //@FIXME problem z mapowaniem i petla po parencie
    @Override
    public List<Category> findAll() {
    	List<Category> c = openSession().createQuery("from Category").list();
    	
    	for (Category c1 : c) {
    		Category ce = new Category();
    		if (c1.getParent() != null) {
    			ce.setId(c1.getParent().getId());
    			c1.setParent(ce);
    		}
    	}
    	
        return c;
    }

    @Override
    public void create(Category category) {
        openSession().save(category);
    }

    @Override
    public void edit(Long id, Category template) {
        Session session = openSession();

        Category category = (Category)session.get(Category.class, id);
        category.fromTemplate(template);
        session.update(category);
        session.flush();

    }

    @Override
    public void delete(Long id) {
        Session session = openSession();
        Category category = (Category)session.get(Category.class, id);
        session.delete(category);
        session.flush();
    }

    @Override
    public List<Category> getByParentId(Long id) {
        Category parent = (Category)openSession().get(Category.class, id);
        return parent.getChildren();
    }

    @Override
    public List<Category> getByChildId(Long id) {
        Category child = (Category)openSession().get(Category.class, id);

        // lista?
        List<Category> parents = new ArrayList<>();
        parents.add(child.getParent());

        return parents;

    }
}
