package com.portal.dao.implementation;

import com.portal.dao.interfaces.ImageDAOI;
import com.portal.entity.Image;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Repository
public class ImageDAOImpl implements ImageDAOI {

    @Autowired
    private SessionFactory sessionFactory;

    private Session openSession() {
        return sessionFactory.getCurrentSession();
    }

    public Image getImage(String id) {
        List<Image> imageList = new ArrayList<Image>();
        Query query = openSession().createQuery("from Image i where i.id = :id");
        query.setParameter("id", id);

        imageList = query.list();

        if (imageList.size() > 0)
            return imageList.get(0);
        else
            return null;
    }

    @Transactional(readOnly=false)
    public void addImage(Image i) {
        Session session = openSession();
        session.save(i);
    }
}
