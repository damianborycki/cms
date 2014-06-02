package com.portal.dao;

import com.portal.entity.Image;

public interface ImageDAO {

    public Image getImage(String id);
    public void addImage(Image i);

}
