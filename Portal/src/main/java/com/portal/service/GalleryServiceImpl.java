package com.portal.service;

import org.springframework.transaction.annotation.Transactional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.portal.dao.interfaces.GalleryDAO;
import com.portal.entity.Gallery;

@Service
@Transactional
public class GalleryServiceImpl implements GalleryService {

    @Autowired
    private GalleryDAO galleryDAO;

    public Gallery getGallery(Long id) {
        return galleryDAO.getGallery(id);
    }

}
