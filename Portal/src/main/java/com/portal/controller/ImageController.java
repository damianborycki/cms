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
import com.portal.controller.ImageScaler;


@Controller
public class ImageController {

    @Autowired
    ImageDAOI imageDAO;

    @Autowired
    UserDAOI userDAO;

    private char slash = '/';

    ImageScaler imageScaler;

    ImageController()
    {
         imageScaler = new ImageScaler(getImagesDirectory());
    }

    @RequestMapping(value="/add_image",method = RequestMethod.POST)
    public ModelAndView addImage(@RequestParam("file") MultipartFile imageData,
                           @RequestParam("description") String description,
                           @RequestParam("author") String author,
                           @RequestParam("x1") long x1,
                           @RequestParam("y1") long y1,
                           @RequestParam("x2") long x2,
                           @RequestParam("y2") long y2,
                           //@RequestParam("tags") Tag tags,
                           Model model) throws Exception 
    {
        Image image = new Image();
        String imagePath = getNewImagePath (imageData, image.getId());

        saveImage(imageData, imagePath, x1,y1,x2,y2);

        image.setId(image.getId() + imageData.getOriginalFilename());
        image.setLink(imagePath);
        image.setAdd_usr(getLoggedUserId());
        image.setAuthor(author);
        image.setType("jpg");
        setImageSize(image, imagePath);

        imageDAO.addImage(image);

        return new ModelAndView("redirect:http://localhost:8080/pages/adminIndex.html#/image");
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

    private void saveTempImageFile( String path, MultipartFile imageData ) throws Exception
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

    private void deleteTempFile(String path) throws Exception
    {
        File file = new File(path);
        file.delete();
    }

    private void saveImage(MultipartFile imageData, String path, long x1, long y1, long x2, long y2) throws Exception
    {
        String tempFilePath = path + ".temp";
        saveTempImageFile( tempFilePath, imageData );
        imageScaler.cropImage( tempFilePath, path, x1, y1, x2, y2 );
        deleteTempFile( tempFilePath );
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

    private long getLoggedUserId() throws RuntimeException
    {
        User loggedUser = userDAO.getLoggedUser();
        if(loggedUser == null)
            throw new RuntimeException("no user logged in!");
        String loggedUserLogin = loggedUser.getLogin();
        return userDAO.getUser(loggedUserLogin).getId();
    }

    private void setImageSize(Image image, String imagePath) throws Exception
    {
        File file = new File(imagePath);
        BufferedImage bufferedImage = ImageIO.read(file);
        image.setHeight((long)bufferedImage.getHeight());
        image.setWidth((long)bufferedImage.getWidth());
    }

    private String getDefaultImageLink(long width,long height)
    {
	Image scaledDefault = imageDAO.getImage("default", width, height);
	if( scaledDefault != null )
	    return scaledDefault.getLink();
        scaledDefault = imageScaler.tryToScaleImage(imageDAO, "default", width, height);
	if( scaledDefault != null )
            return scaledDefault.getLink();
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
            Image scaledImage = imageScaler.tryToScaleImage(imageDAO, imageId, width, height);
            if( scaledImage != null )
                if( scaledImage.getApp_usr() != null )
                    return scaledImage.getLink();
        }
        if( image == null || image.getApp_usr() == null)
            return getDefaultImageLink(width,height);

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
