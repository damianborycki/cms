package com.portal.dao.interfaces;

import com.portal.entity.Image;
import com.portal.entity.ImageMetadata;
import java.util.List;
import java.sql.Date;

public interface ImageDAOI {

    public Image getImage(String id, long width, long height);
    public Image getBiggestImage(String id);
    public void addImage(Image i);
    public List<Image> getAllUnapproved(Long addUserId, java.sql.Date startDate, java.sql.Date endDate);
    public List<Image> getAllImages(String id);
    public ImageMetadata getImageMetadata(String id);
    public List<Image> getAll();
    public List<String> getImageIds(long userId, java.sql.Date startDate, java.sql.Date endDate);
    public void deleteImage(String id);
    public String acceptImage(Long app_usr, String id); 
}
