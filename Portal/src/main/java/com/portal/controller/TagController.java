package com.portal.controller;

import com.portal.dao.interfaces.TagDAOI;
import com.portal.entity.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * User: Bartosz
 * Date: 2014-05-19
 * Time: 21:26
 */

@Controller
public class TagController {

    @Autowired
    public TagDAOI tagDAO;

    @RequestMapping(value="/tag/{id}", method= RequestMethod.GET)
    @ResponseBody
    public Tag get(@PathVariable Long id){
        return tagDAO.get(id);
    }

    @RequestMapping(value="/tag", method= RequestMethod.GET)
    @ResponseBody
    public List<Tag> getAll(){
        return tagDAO.findAll();
    }

    @RequestMapping(value="/tag/{id}", method=RequestMethod.DELETE)
    public void delete(@PathVariable Long id, HttpServletResponse response) {
        tagDAO.delete(id);
        response.setStatus(HttpServletResponse.SC_OK);
    }

    @RequestMapping(value="/tag/{id}", method= RequestMethod.PUT, consumes= MediaType.APPLICATION_JSON_VALUE)
    public void edit(@PathVariable Long id, @RequestBody Tag tag, HttpServletResponse response) {
        tagDAO.edit(id, tag);
        response.setStatus(HttpServletResponse.SC_OK);
    }

    @RequestMapping(value="/tag", method= RequestMethod.POST, consumes= MediaType.APPLICATION_JSON_VALUE)
    public void create(@RequestBody Tag tag, HttpServletResponse response) {
        tagDAO.create(tag);
        response.setStatus(HttpServletResponse.SC_OK);
    }

}
