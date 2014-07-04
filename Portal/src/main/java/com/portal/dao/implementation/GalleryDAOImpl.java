package com.portal.dao.implementation;

import java.util.ArrayList;
import java.util.List;

import com.portal.dao.interfaces.GalleryDAOI;
import com.portal.entity.GalleryMetadata;
import com.portal.entity.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import com.portal.entity.Gallery;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class GalleryDAOImpl implements GalleryDAOI {

    @Autowired
    private SessionFactory sessionFactory;

    private Session openSession() {
        return sessionFactory.getCurrentSession();
    }

    public Gallery getGallery(Long id) {
        List<Gallery> galleryList = new ArrayList<Gallery>();
        Query query = openSession().createQuery("from Gallery g where g.id = :id");
        query.setParameter("id", id);

        galleryList = query.list();

        if (galleryList.size() > 0)
            return galleryList.get(0);
        else
            return null;
    }

    @Transactional(readOnly=false)
    public void addGallery(Gallery g) {
        Session session = openSession();
        session.save(g);
    }
    
    public void deleteGallery(Long id) {
        Query query = openSession().createQuery("delete from Gallery where id=:id");
        query.setParameter("id", id);
        query.executeUpdate();
    }

    @Override
    public GalleryMetadata getGalleryMetadata(Long id) {
        GalleryMetadata galleryMetadata = new GalleryMetadata();
        Gallery gallery = getGallery(id);

        galleryMetadata.setDescription(gallery.getDescription());
        galleryMetadata.setTags(gallery.getTags());

        return galleryMetadata;
    }

    @Override
    public void setGalleryMetadata(Long id, String description, List<Tag> tags) {

    }


}
