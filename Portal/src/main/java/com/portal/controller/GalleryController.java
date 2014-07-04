package com.portal.controller;

import com.portal.dao.interfaces.GalleryDAOI;
import com.portal.entity.Gallery;
import com.portal.entity.GalleryMetadata;
import com.portal.entity.Image;
import com.portal.entity.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.sql.Date;
import java.util.List;


@Controller
public class GalleryController {

    @Autowired
    GalleryDAOI galleryDAO;

    @RequestMapping(value="/gallery", method=RequestMethod.POST)
    public String addImage(@RequestParam("images") List<Image> images,
                           @RequestParam("description") String description,
                           @RequestParam("tags") List<Tag> tags,
                           @RequestParam("add_usr") Long add_usr,
                           Model model) {
        Gallery gallery = new Gallery();

        gallery.setImages(images);
        gallery.setDescription(description);
        gallery.setAdd_usr(add_usr);
        gallery.setTags(tags);

        galleryDAO.addGallery(gallery);

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
