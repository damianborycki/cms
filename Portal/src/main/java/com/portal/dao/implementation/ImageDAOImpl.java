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
        return sessionFactory.openSession();
    }

    public Image getImage(String id, long width, long height) {
        List<Image> imageList = new ArrayList<Image>();
        Query query = openSession().createQuery("from Image i where i.id = :id and i.width = :width and i.height = :height");
        query.setParameter("id", id);
        query.setParameter("width",width);
        query.setParameter("height",height);

        imageList = query.list();

        if (imageList.size() > 0)
            return imageList.get(0);
        else
            return null;
    }

    public List<Image> getAllUnapproved()
    {
        List<Image> imageList = new ArrayList<Image>();

        Query query = openSession().createQuery("from Image i where i.app_usr = :app_usr");
        query.setParameter("app_usr",null);
        imageList = query.list();
        return imageList;
    }

    private List<Image> getAllImages(String id)
    {
        List<Image> imageList = new ArrayList<Image>();

        Query query = openSession().createQuery("from Image i where i.id = :id");
        query.setParameter("id",id);
        imageList = query.list();
        return imageList;
    }

    public Image getBiggestImage(String id)
    {
        List<Image> imageList = getAllImages(id);

        long biggestImageSize = -1;
        Image biggestImage = null;
        for(int i=0; i<imageList.size(); i++)
        {
            Image current = imageList.get(i);
            long currentSize = current.getWidth() * current.getHeight();
            if( currentSize > biggestImageSize )
            {
                biggestImage = current;
                biggestImageSize = currentSize;
            }
        }
        return biggestImage;
    }

    @Transactional(readOnly=false)
    public void addImage(Image i) {
        Session session = openSession();
        session.save(i);
    }
}
