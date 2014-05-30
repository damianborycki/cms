package com.portal.dao.interfaces;

import com.portal.entity.Image;

public interface ImageDAOI {

    public Image getImage(String id);
    public void addImage(Image i);

}
