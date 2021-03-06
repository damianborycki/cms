package com.portal.controller;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.awt.image.BufferedImage;
import com.mortennobel.imagescaling.ResampleOp;
import com.mortennobel.imagescaling.AdvancedResizeOp;

import org.springframework.beans.factory.annotation.Autowired;

import com.portal.entity.Image;
import com.portal.dao.interfaces.ImageDAOI;

public class ImageScaler
{
    ImageScaler(String imagesDirectory)
    {
        this.imagesDirectory = imagesDirectory;
    }

    private char slash = '/';
    private int jcropWidth = 700;
    private int jcropHeight = 500;

    public Image tryToScaleImage(ImageDAOI imageDAO, String id, long width, long height)
    {
        Image biggestImage = imageDAO.getBiggestImage(id);
        if(biggestImage == null)
            return null;
        
        String biggestImageLink = biggestImage.getLink();
        String scaledImagePath = scaleImage(id, biggestImageLink, width, height);
        if( scaledImagePath != null)
        {
            Image scaledImage = biggestImage;
            scaledImage.setLink(scaledImagePath);
            scaledImage.setWidth(width);
            scaledImage.setHeight(height);
            imageDAO.addImage(scaledImage);
            return scaledImage;
        }
        return null;
    }

    private String scaleImage(String id, String link, long width, long height)
    {
        try
        {
            File file = new File(link);
            BufferedImage source = ImageIO.read(file);
            ResampleOp resampleOp = new ResampleOp ((int)width,(int)height);
            resampleOp.setUnsharpenMask(AdvancedResizeOp.UnsharpenMask.VerySharp);
            BufferedImage rescaled = resampleOp.filter(source, null);
            String scaledImagePath = getScaledImagePath(id, width, height);
            File file2 = new File(scaledImagePath);
            if( rescaled != null && file2 != null )
            {
                ImageIO.write( rescaled, "JPG", file2);
            }
            return scaledImagePath;
        }
        catch(Exception e)
        {
            return null;
        }
    }

    private String getScaledImagePath(String id, long width, long height)
    {
        String imagePath = imagesDirectory + id + "_" + width + "_" + height + ".jpg";
        return imagePath;
    }

    private int recalculateCoords(int origSize, int coordSize, long coord )
    {
         return ( (int)coord * origSize ) / coordSize;
    }

    public void cropImage(String tempFilePath, String path, long x1, long y1, long x2, long y2) throws Exception
    {
        BufferedImage image = ImageIO.read(new File(tempFilePath));
        int originalWidth = image.getWidth();
        int originalHeight = image.getHeight();
        BufferedImage cropped = image.getSubimage( recalculateCoords( originalWidth,   jcropWidth,  x1 ),
                             	                   recalculateCoords( originalHeight,  jcropHeight, y1 ),
                                                   recalculateCoords( originalWidth,   jcropWidth,  x2-x1 ),
                                                   recalculateCoords( originalHeight,  jcropHeight, y2-y1 ) );
        ImageIO.write( cropped, "jpg", new File(path) );
    }
    
    public void cropImage(String tempFilePath, String path) throws Exception
    {
        cropImage(tempFilePath, path, 0, 0, jcropWidth, jcropHeight);
    }

    String imagesDirectory;
}

