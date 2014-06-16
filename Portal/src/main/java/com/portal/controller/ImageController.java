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

import magick.*;

@Controller
public class ImageController {

    @Autowired
    ImageDAOI imageDAO;

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
            String imagePath = getNewImagePath (imageData);
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

            MagickImage magicImage = new MagickImage (new ImageInfo (imagePath));
            image.setWidth(Long.valueOf(magicImage.getDimension().width));
            image.setHeight(Long.valueOf(magicImage.getDimension().height));
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
                         @RequestParam("id") String imageId) throws Exception
    {
        File file = new File( getImagePath(imageId));
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

    private String getNewImagePath(MultipartFile file)
    {
        // TODO gdzie zapisywac nowe obrazki ???
        return "example.jpg";
    }

    private String getDefaultImageLink()
    {
        return System.getProperty("catalina.base") + "\\webapps\\images\\default\\default.jpg";
    }

    private String getDefaultAvatarLink()
    {
        return System.getProperty("catalina.base") + "\\webapps\\images\\avatars\\defaultAvatar\\default_avatar.jpg";
    }

    private String getImagePath(String imageId)
    {
        Image image = imageDAO.getImage(imageId);
        if( image == null )
            return getDefaultImageLink();

        return image.getLink();
    }

    private String getAvatarPath(String imageId)
    {
        Image image = imageDAO.getImage(imageId);
        if( image == null )
            return getDefaultAvatarLink();

        return image.getLink();
    }
}
