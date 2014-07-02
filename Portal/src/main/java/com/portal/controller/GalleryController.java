package com.portal.controller;

import com.portal.dao.interfaces.GalleryDAOI;
import com.portal.entity.Gallery;
import com.portal.entity.GalleryMetadata;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

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

    @RequestMapping(value="/gallery", method=RequestMethod.DELETE)
    public void deleteGallery(@RequestParam("id") String id) {
        galleryDAO.deleteGallery(id);
    }

    @RequestMapping(value="/gallerymetadata", method=RequestMethod.GET)
    @ResponseBody
    public GalleryMetadata getMetadata(@RequestParam("id") Long galleryId) throws Exception
    {
        return galleryDAO.getGalleryMetadata(galleryId);
    }
}
