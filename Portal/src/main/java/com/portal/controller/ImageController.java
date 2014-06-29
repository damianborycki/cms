package com.portal.controller;

import com.portal.dao.interfaces.ImageDAOI;
import com.portal.entity.Image;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile; 
import java.io.File;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.io.*;
import java.util.List;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import com.mortennobel.imagescaling.ResampleOp;
import com.mortennobel.imagescaling.AdvancedResizeOp;


@Controller
public class ImageController {

    @Autowired
    ImageDAOI imageDAO;

    private char slash = '\\';

    @RequestMapping(value="/image", method=RequestMethod.POST)
    public String addImage(@RequestParam("imageData") MultipartFile imageData,
                           @RequestParam("description") String description,
                           @RequestParam("author") String author,
                           //@RequestParam("tags") Tag tags,
                           @RequestParam("add_usr") Long add_urs,
                           Model model) {
        Image image = new Image();
        OutputStream outputStream = null;
        InputStream inputStream = null;

        try
        {
            image.setId(image.getId() + imageData.getOriginalFilename());
            String imagePath = getNewImagePath (imageData, image.getId());
            image.setLink(imagePath);

            File imageFile = new File( imagePath );
            if (!imageFile.exists()) 
            {
                imageFile.createNewFile();  
            }
            outputStream = new FileOutputStream(imageFile);
            inputStream = imageData.getInputStream();

            int read = 0;  
            byte[] bytes = new byte[1024];  
          
            while ((read = inputStream.read(bytes)) != -1) 
            {
                outputStream.write(bytes, 0, read);  
            }

            outputStream.close();
        }
        catch(Exception e)
        {
            return "/home";
        }

        image.setAdd_usr(add_urs);
        image.setAuthor(author);
        image.setType("jpg");

        imageDAO.addImage(image);

        return "/home";
    }
    
    @RequestMapping(value="/image", method=RequestMethod.GET)
    public void getImage(HttpServletResponse response,
                         @RequestParam("id") String imageId,
                         @RequestParam("width") long width,
                         @RequestParam("height") long height) throws Exception
    {
        File file = new File( getImagePath(imageId, width, height));
        FileInputStream is = new FileInputStream(file);
        IOUtils.copy(is, response.getOutputStream());
        response.flushBuffer();
    }

    @RequestMapping(value="/avatar", method=RequestMethod.GET)
    public void getAvatar(HttpServletResponse response,
                         @RequestParam("id") String imageId) throws Exception
    {
        File file = new File( getAvatarPath(imageId));
        FileInputStream is = new FileInputStream(file);
        IOUtils.copy(is, response.getOutputStream());
        response.flushBuffer();
    }

    @RequestMapping(value="/unapproved_images", method=RequestMethod.GET)
    @ResponseBody
    public List<Image> getUnapprovedImages()
    {
        return imageDAO.getAllUnapproved();
    }

    private String getImagesDirectory()
    {
        return System.getProperty("catalina.base") + slash + "webapps" +slash + "images" + slash;
    }

    private String getNewImagePath(MultipartFile file, String id)
    {
        String imagePath = getImagesDirectory() + id;

        return imagePath;
    }

    private String getScaledImagePath(String id, long width, long height)
    {
        String imagePath = getImagesDirectory() + id + "_" + width + "_" + height + ".jpg";
        return imagePath;
    }

    private String tryToScaleImage(String id, long width, long height)
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
        }
        return scaledImagePath;
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

    private String getDefaultImageLink()
    {
        return getImagesDirectory() + "default" + slash + "default.jpg";
    }

    private String getDefaultAvatarLink()
    {
        return getImagesDirectory() + "avatars" + slash + "defaultAvatar" + slash + "default_avatar.jpg";
    }

    private String getImagePath(String imageId, long width, long height)
    {
        Image image = imageDAO.getImage(imageId,width,height);
        if( image == null )
        {
            String scaledImage = tryToScaleImage(imageId, width, height);
            if( scaledImage != null)
                return scaledImage;
        }
        if( image == null || image.getApp_usr() == null)
            return getDefaultImageLink();

        return image.getLink();
    }

    private String getAvatarPath(String imageId)
    {
        Image image = imageDAO.getImage(imageId,100,100);
        if (image == null || image.getApp_usr() == null)
            return getDefaultAvatarLink();

        return image.getLink();
    }

    @RequestMapping(value="/metadata", method=RequestMethod.GET)
    @ResponseBody
    public List<Image> getMetadata(@RequestParam("id") String imageId) throws Exception
    {
        return imageDAO.getAllImages(imageId);
    }
}
