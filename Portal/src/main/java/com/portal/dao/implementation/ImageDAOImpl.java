package com.portal.dao.implementation;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Set;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Date;


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
//import java.sql.Date;

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
        Session session = openSession();
        Query query = session.createQuery("from Image i where i.id = :id and i.width = :width and i.height = :height");
        query.setParameter("id", id);
        query.setParameter("width",width);
        query.setParameter("height",height);

        imageList = query.list();
        session.close();

        if (imageList.size() > 0)
            return imageList.get(0);
        else
            return null;
    }

    public List<Image> getAllUnapproved(Long addUserId, java.sql.Date startDate, java.sql.Date endDate)
    {
        List<Image> imageList;// = new ArrayList<Image>();

        Query query;
        Session session = openSession();
        if( addUserId == null)
        	query = session.createQuery("from Image where app_usr is null");
        else
        {
        	query = openSession().createQuery("from Image i where i.app_usr is null and i.add_usr = :add_usr and add_datetime between :startDate and :endDate");
        	query.setParameter("add_usr", addUserId);
        	query.setParameter("startDate", startDate);
        	query.setParameter("endDate", endDate);
        }
        
        imageList = query.list();
        session.close();
        return unique(imageList);
    }

    public List<Image> getAllImages(String id)
    {
        List<Image> imageList = new ArrayList<Image>();

        Session session = openSession();
        Query query = session.createQuery("from Image i where i.id = :id");
        query.setParameter("id",id);
        imageList = query.list();
        session.close();
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


    private List<Image> unique(List<Image> images)
    {
    	Set<String> imageIds = new HashSet<String>();
    	List<Image> uniqueImages = new ArrayList<Image>();
    	
    	for(Image image : images)
    	{
    		String id = image.getId();
        	boolean idFound = false;
        	for( String i : imageIds )
        	{
        		if( i.equals(id))
        		{
        			idFound = true;
        			break;
        		}
        	}
        	
        	if( idFound == false )
        	{
        		uniqueImages.add(image);
        		imageIds.add(id);
        	}
    	}
    	return uniqueImages;
    }
    
    @Override
    public List<Image> getAll() {
    	Session session = openSession();
        List<Image> images = session.createQuery("FROM Image i WHERE i.id <> 'default'").list();
        session.close();
        
        return unique(images);
    }
    
    public List<String> getImageIds(long userId, java.sql.Date startDate, java.sql.Date endDate)
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
    
    public void deleteImage(String id)
    {
    	Session session = openSession();
    	Query query = session.createQuery("delete Image i where i.id = :id");
    	query.setParameter("id", id);
    	query.executeUpdate();
    	session.close();
    }
    
	public String acceptImage(Long app_usr, String id)
    {
		Session session = openSession();
    	Query query = session.createQuery("update Image i set i.app_usr = :app_usr, app_datetime = :app_datetime where i.id = :id");
    	query.setParameter("id", id);
    	query.setParameter("app_usr", app_usr);
    	
    	Calendar cal = Calendar.getInstance();
    	cal.setTime(new java.util.Date());

    	query.setParameter("app_datetime", new java.sql.Date(cal.get(Calendar.YEAR)-1900, cal.get(Calendar.MONTH), cal.get(Calendar.DATE)));
    	query.executeUpdate();
    	session.close();
    	String queryString = query.getQueryString();
    	return queryString;
    }

    public String addGalleryId(Long galleryId,  String id)
    {
    	Session session = openSession();
        Query query = session.createQuery("update Image i set i.gallery_id = :gallery_id where i.id = :id");
        query.setParameter("id", id);
        query.setParameter("gallery_id", galleryId);

        query.executeUpdate();
        session.close();
        String queryString = query.getQueryString();
        return queryString;
    }
}
