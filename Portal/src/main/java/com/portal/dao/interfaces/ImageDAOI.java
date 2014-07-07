package com.portal.dao.interfaces;

import com.portal.entity.Image;
import com.portal.entity.ImageMetadata;
import java.util.List;

public interface ImageDAOI {

    public Image getImage(String id, long width, long height);
    public Image getBiggestImage(String id);
    public void addImage(Image i);
    public List<Image> getAllUnapproved();
    public List<Image> getAllImages(String id);
    public ImageMetadata getImageMetadata(String id);
    public List<Image> getAll();
}
