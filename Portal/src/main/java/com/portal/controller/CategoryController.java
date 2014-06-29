package com.portal.controller;

import com.portal.dao.interfaces.CategoryDAOI;
import com.portal.entity.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * User: Bartosz
 * Date: 18.05.14
 * Time: 21:19
 */

@Controller
public class CategoryController {

    @Autowired
    private CategoryDAOI categoryDAO;

    @RequestMapping(value="category/{id}", method= RequestMethod.GET)
    @ResponseBody
    public Category get(@PathVariable Long id){
        return categoryDAO.get(id);
    }

    @RequestMapping(value="category", method= RequestMethod.GET)
    @ResponseBody
    public List<Category> getAll(){
        return categoryDAO.findAll();
    }

    @RequestMapping(value="category", method= RequestMethod.GET, params = "parent")
    @ResponseBody
    public List<Category> getByParentId(@RequestParam Long parent){
        List<Category> list = categoryDAO.getByParentId(parent);
        return list;
    }

    @RequestMapping(value="category", method= RequestMethod.GET, params = "child")
    @ResponseBody
    public List<Category> getByChildId(@RequestParam Long child){

        return categoryDAO.getByChildId(child);
    }

    @RequestMapping(value="category", method= RequestMethod.POST, consumes= MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    ResponseEntity<Object> create(@RequestBody Category category){
        categoryDAO.create(category);
        return new ResponseEntity<Object>(null, HttpStatus.ACCEPTED);
    }

    @RequestMapping(value="category/{id}", method= RequestMethod.PUT, consumes=MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    ResponseEntity<Object> edit(@PathVariable Long id, @RequestBody Category category){
        categoryDAO.edit(id, category);
        return new ResponseEntity<Object>(null, HttpStatus.ACCEPTED);
    }

    @RequestMapping(value="category/{id}", method= RequestMethod.DELETE)
    public @ResponseBody
    ResponseEntity<Object> delete(@PathVariable Long id){
        categoryDAO.delete(id);
        return new ResponseEntity<Object>(null, HttpStatus.ACCEPTED);
    }





}
