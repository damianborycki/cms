package com.portal.dao.interfaces;

import com.portal.entity.Gallery;

public interface GalleryDAOI {

    public Gallery getGallery(Long id);
    public void addGallery(Gallery g);

}
