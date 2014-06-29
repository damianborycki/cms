package com.portal.controller;

import com.portal.dao.interfaces.TagDAOI;
import com.portal.entity.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

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

}
