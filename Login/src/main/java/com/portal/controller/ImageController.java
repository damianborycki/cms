package com.portal.controller;

import com.portal.dao.ImageDAO;
import com.portal.entity.Image;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class ImageController {

    @Autowired
    ImageDAO imageDAO;

    @RequestMapping(value="/image", method=RequestMethod.POST)
    public String addImage(@RequestParam("imageData") String imageData,
                           @RequestParam("description") String description,
                           @RequestParam("author") String author,
                           //@RequestParam("tags") Tag tags,
                           @RequestParam("add_usr") Long add_urs,
                           Model model) {
        Image image = new Image();

        // TODO save image do HDD
        image.setAdd_usr(add_urs);
        image.setAuthor(author);
        image.setType("jpg");

        image.setWidth(Long.valueOf(123)); // TODO get widht and height from image
        image.setHeight(Long.valueOf(123));

        image.setLink("link/to/image");

        imageDAO.addImage(image);

        return "/home";
    }
}
