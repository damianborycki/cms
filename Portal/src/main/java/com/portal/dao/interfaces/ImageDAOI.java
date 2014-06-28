package com.portal.dao.interfaces;

import com.portal.entity.Image;
import java.util.List;

public interface ImageDAOI {

    public Image getImage(String id, long width, long height);
    public void addImage(Image i);
    public List<Image> getAllUnapproved();

}
