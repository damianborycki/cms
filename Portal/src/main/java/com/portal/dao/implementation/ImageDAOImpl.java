package com.portal.dao.implementation;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.LinkedHashSet;
import java.sql.Date;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.portal.dao.interfaces.ImageDAOI;
import com.portal.dao.interfaces.UserDAOI;
import com.portal.entity.User;
import com.portal.entity.Image;
import com.portal.entity.ImageMetadata;

@Repository
public class ImageDAOImpl implements ImageDAOI {

    @Autowired
    private SessionFactory sessionFactory;

    @Autowired
    private UserDAOI userDAO;

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

    public List<Image> getAllImages(String id)
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

    private Image getFirstImage(String id)
    {
        List<Image> imageList = getAllImages(id);
        if( imageList.size() <= 0 )
            return null;
        return imageList.get(0);
    }

    public ImageMetadata getImageMetadata(String id)
    {
        Image image = getFirstImage(id);



        if(image == null)
            return null;

        ImageMetadata result = new ImageMetadata();
        result.setAuthor(image.getAuthor());
        result.setDescription(image.getDescription());
        return result;
    }

    @Transactional(readOnly=false)
    public void addImage(Image i) {
        Session session = openSession();
				Transaction tx = session.beginTransaction();
        session.save(i);
				tx.commit();
				session.close();
    }


    @Override
    public List<Image> getAll() {

        List<Image> ids = openSession().createQuery("FROM Image i WHERE i.id <> 'default'").list();
        return ids;
    }

    public List<String> getImageIds(long userId, Date startDate, Date endDate)
    {
        Query query = openSession().createQuery("from Image i where add_usr = :add_usr and add_datetime between '" + startDate.toString() + "' and '" + endDate.toString() + "'");
        query.setParameter("add_usr",userId);
        //query.setParameter("startDate",startDate);
        //query.setParameter("endDate",endDate);
        List<Image> imageList = query.list();
        List<String> idsList = new ArrayList<String>();
        for(int i=0; i<imageList.size(); i++) 
        {
            idsList.add( imageList.get(i).getId() );
        }

        Set<String> setItems = new LinkedHashSet(idsList);
        idsList.clear();
        idsList.addAll(setItems);
        return idsList;

    }
}
