package com.portal.controller;

import java.awt.image.BufferedImage;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.mortennobel.imagescaling.AdvancedResizeOp;
import com.mortennobel.imagescaling.ResampleOp;
import com.portal.dao.interfaces.ImageDAOI;
import com.portal.dao.interfaces.UserDAOI;
import com.portal.entity.Image;
import com.portal.entity.ImageMetadata;
import com.portal.entity.User;


@Controller
public class ImageController {

    @Autowired
    ImageDAOI imageDAO;

    @Autowired
    UserDAOI userDAO;

    private char slash = '\\';

    @RequestMapping(value="/add_image",method = RequestMethod.POST)
    public ModelAndView addImage(@RequestParam("file") MultipartFile imageData,
                           @RequestParam("description") String description,
                           @RequestParam("author") String author,
                           //@RequestParam("tags") Tag tags,
                           Model model) throws Exception 
    {
        Image image = new Image();
        String imagePath = getNewImagePath (imageData, image.getId());

        saveImage(imageData, imagePath);

        image.setId(image.getId() + imageData.getOriginalFilename());
        image.setLink(imagePath);
        image.setAdd_usr(getLoggedUserId());
        image.setAuthor(author);
        image.setType("jpg");
        setImageSize(image, imagePath);

        imageDAO.addImage(image);

        return new ModelAndView("redirect:http://localhost:8080/pages");
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

    private void saveImage(MultipartFile imageData, String path) throws Exception
    {
        File imageFile = new File( path );
        if (!imageFile.exists()) 
            imageFile.createNewFile();  

        OutputStream outputStream = new FileOutputStream(imageFile);
        InputStream inputStream = imageData.getInputStream();

        int read = 0;  
        byte[] bytes = new byte[1024];  
          
        while ((read = inputStream.read(bytes)) != -1) 
        {
            outputStream.write(bytes, 0, read);  
        }

        outputStream.close();
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

    private long getLoggedUserId() throws RuntimeException
    {
        User loggedUser = userDAO.getLoggedUser();
        if(loggedUser == null)
            throw new RuntimeException("no user logged in!");
        String loggedUserLogin = loggedUser.getLogin();
        return userDAO.getUser(loggedUserLogin).getId();
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

    private void setImageSize(Image image, String imagePath) throws Exception
    {
        File file = new File(imagePath);
        BufferedImage bufferedImage = ImageIO.read(file);
        image.setHeight((long)bufferedImage.getHeight());
        image.setWidth((long)bufferedImage.getWidth());
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
    public ImageMetadata getMetadata(@RequestParam("id") String imageId) throws Exception
    {
        return imageDAO.getImageMetadata(imageId);
    }
}
