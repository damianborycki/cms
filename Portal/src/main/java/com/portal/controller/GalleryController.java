package com.portal.controller;

import com.portal.dao.interfaces.GalleryDAOI;
import com.portal.dao.interfaces.ImageDAOI;
import com.portal.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;


@Controller
public class GalleryController {

    @Autowired
    GalleryDAOI galleryDAO;
    @Autowired
    ImageDAOI imageDAO;

    @RequestMapping(value="/gallery", method=RequestMethod.POST)
    public ModelAndView addImage(@RequestParam("images") String images,
                           @RequestParam("description") String description,
                           @RequestParam("add_usr") Long add_usr,
                           Model model)
    {

        Gallery gallery = new Gallery();

        List<Image> imagesList = new ArrayList<>();
        String[] imgProps = images.split(",");

        for (String imgProp : imgProps)
        {
            String[] img = imgProp.split(":");
            imagesList.add(imageDAO.getImage(img[0], new Long(img[1]), new Long(img[2])));
        }

        gallery.setImages(imagesList);
        gallery.setDescription(description);
        gallery.setAdd_usr(add_usr);

        galleryDAO.addGallery(gallery);

        for (Image img : imagesList)
        {
            imageDAO.addGalleryId(gallery.getId(), img.getId());
        }

        return new ModelAndView("redirect:/adminIndex.html#/gallery");
    }

    @RequestMapping(value="/gallery", method=RequestMethod.GET)
    public @ResponseBody Gallery getGallery(HttpServletResponse response,
                           @RequestParam("id") Long id) {
        return galleryDAO.getGallery(id);
    }


    @RequestMapping(value="/gallery", method=RequestMethod.DELETE)
    public void deleteGallery(@RequestParam("id") Long id) {
        galleryDAO.deleteGallery(id);
    }

    @RequestMapping(value="/gallerymetadata", method=RequestMethod.GET)
    @ResponseBody
    public GalleryMetadata getMetadata(@RequestParam("id") Long galleryId) throws Exception
    {
        return galleryDAO.getGalleryMetadata(galleryId);
    }

    @RequestMapping(value="/images", method=RequestMethod.GET)
    public @ResponseBody List<Image> getImages(HttpServletResponse response) {
        response.setStatus(HttpServletResponse.SC_OK);
        return imageDAO.getAll();
    }
}
