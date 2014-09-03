package com.portal.controller;

import com.portal.dao.interfaces.TagDAOI;
import com.portal.dao.interfaces.TagTypeDAOI;
import com.portal.entity.TagType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
public class TagTypeController {
    @Autowired
    public TagTypeDAOI tagTypeDAO;
    @Autowired
    public TagDAOI tagDAO;
    
    @RequestMapping(value="/type", method= RequestMethod.GET)
    @ResponseBody
    public List<TagType> getAll(){
        return tagTypeDAO.findAll();
    }

    @RequestMapping(value="/type/{id}", method= RequestMethod.GET)
    @ResponseBody
    public TagType get(@PathVariable Long id){
        return tagTypeDAO.get(id);
    }

    @RequestMapping(value="type", method= RequestMethod.POST, consumes= MediaType.APPLICATION_JSON_VALUE)
    public void create(@RequestBody TagType tagType, HttpServletResponse response){
        tagTypeDAO.create(tagType);
        response.setStatus(HttpServletResponse.SC_OK);
    }

    @RequestMapping(value="type/{id}", method= RequestMethod.PUT, consumes=MediaType.APPLICATION_JSON_VALUE)
    public void edit(@PathVariable Long id, @RequestBody TagType tagType, HttpServletResponse response){
        tagTypeDAO.edit(id, tagType);
        response.setStatus(HttpServletResponse.SC_OK);
    }

    @RequestMapping(value="type/{id}", method= RequestMethod.DELETE)
    public void delete(@PathVariable Long id, HttpServletResponse response){
        if (tagDAO.existsForType(tagTypeDAO.get(id))) {
            response.setStatus(HttpServletResponse.SC_CONFLICT);
        } else {
            tagTypeDAO.delete(id);
            response.setStatus(HttpServletResponse.SC_OK);
        }
    }
}
