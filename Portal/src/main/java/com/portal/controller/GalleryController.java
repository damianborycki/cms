package com.portal.controller;

import com.portal.dao.interfaces.GalleryDAOI;
import com.portal.entity.Gallery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.Date;


@Controller
public class GalleryController {

    @Autowired
    GalleryDAOI galleryDAO;
    //  GalleryDAO galleryDAO;

    @RequestMapping(value="/addGallery", method=RequestMethod.POST)
    public String addImage(@RequestParam("firstname") String firstname, @RequestParam("lastname") String lastname, Model model) {
        Gallery g = new Gallery();

        g.setAdd_usr(Long.valueOf(123));
        g.setDescription(firstname);
        g.setAdd_usr(Long.valueOf(123));
        g.setAdd_datetime(new Date(System.currentTimeMillis()));


        galleryDAO.addGallery(g);

     /*   Gallery g = new Gallery();
        galleryDAO.addGallery(g);*/

        return "/home";
    }
}
