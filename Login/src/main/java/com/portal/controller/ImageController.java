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
  //  GalleryDAO galleryDAO;

    @RequestMapping(value="/addImage", method=RequestMethod.POST)
    public String addImage(@RequestParam("firstname") String firstname, @RequestParam("lastname") String lastname, Model model) {
        Image i = new Image();

        i.setAdd_usr(Long.valueOf(123));
        i.setType(firstname);
        i.setWidth(Long.valueOf(123));
        i.setHeight(Long.valueOf(123));
        i.setLink(lastname);

        imageDAO.addImage(i);

     /*   Gallery g = new Gallery();
        galleryDAO.addGallery(g);*/

        return "/home";
    }
}
