package com.portal.dao.interfaces;

import com.portal.entity.Gallery;

public interface GalleryDAO {

    public Gallery getGallery(Long id);
    public void addGallery(Gallery g);

}
