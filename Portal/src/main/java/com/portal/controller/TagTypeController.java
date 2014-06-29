package com.portal.controller;

import com.portal.dao.interfaces.TagTypeDAOI;
import com.portal.entity.TagType;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class TagTypeController {
    @Autowired
    public TagTypeDAOI tagTypeDAO;
    
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
    public @ResponseBody
    ResponseEntity<Object> create(@RequestBody TagType tagType){
        tagTypeDAO.create(tagType);
        return new ResponseEntity<Object>(null, HttpStatus.ACCEPTED);
    }
    
    @RequestMapping(value="type/{id}", method= RequestMethod.PUT, consumes=MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    ResponseEntity<Object> edit(@PathVariable Long id, @RequestBody TagType tagType){
        tagTypeDAO.edit(id, tagType);
        return new ResponseEntity<Object>(null, HttpStatus.ACCEPTED);
    }

    @RequestMapping(value="type/{id}", method= RequestMethod.DELETE)
    public @ResponseBody
    ResponseEntity<Object> delete(@PathVariable Long id){
        tagTypeDAO.delete(id);
        return new ResponseEntity<Object>(null, HttpStatus.ACCEPTED);
    }
}
