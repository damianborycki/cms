package com.portal.dao;

import com.portal.entity.Gallery;

public interface GalleryDAO {

    public Gallery getGallery(Long id);
    public void addGallery(Gallery g);

}
