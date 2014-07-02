package com.portal.dao.interfaces;

import com.portal.entity.Gallery;
import com.portal.entity.GalleryMetadata;
import com.portal.entity.Tag;

import java.util.List;

public interface GalleryDAOI {

    public Gallery getGallery(Long id);
    public void addGallery(Gallery g);
    public void deleteGallery(String id);
    public GalleryMetadata getGalleryMetadata(Long id);
    public void setGalleryMetadata(Long id, String description, List<Tag> tags);

}
