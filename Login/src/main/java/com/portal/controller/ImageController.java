package com.portal.controller;

import com.portal.dao.ImageDAO;
import com.portal.entity.Image;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile; 
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.IOException;

@Controller
public class ImageController {

    @Autowired
    ImageDAO imageDAO;

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
        }
        catch(IOException e)
        {
            // what should I do here?
        }

        image.setAdd_usr(add_urs);
        image.setAuthor(author);
        image.setType("jpg");

        image.setWidth(Long.valueOf(123)); // TODO get widht and height from image
        image.setHeight(Long.valueOf(123));

        imageDAO.addImage(image);

        return "/home";
    }

    private String getNewImagePath(MultipartFile file)
    {
        return "example.jpg";
    }
}
