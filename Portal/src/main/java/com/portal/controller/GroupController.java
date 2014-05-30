package com.portal.controller;

import com.portal.dao.interfaces.GroupDAOI;
import com.portal.entity.Group;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Mateusz.
 */
@Controller
public class GroupController {

    @Autowired
    private GroupDAOI groupDAO;

    @RequestMapping(value = "group", method = RequestMethod.GET)
    @ResponseBody
    public List<Group> getAll() {
        return groupDAO.findAll();
    }

    @RequestMapping(value = "group/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Group get(@PathVariable Long id) {
        return groupDAO.get(id);
    }

    @RequestMapping(value="group", method= RequestMethod.POST, consumes="application/json")
    @ResponseBody
    public ResponseEntity<Object> create(@RequestBody Group group){
        groupDAO.add(group);
        return new ResponseEntity<Object>(group, HttpStatus.ACCEPTED);
    }

    @RequestMapping(value = "group/{id}", method = RequestMethod.PUT, consumes = "application/json")
    @ResponseBody
    public ResponseEntity<Object> put(@RequestBody Group group, @PathVariable String name, @PathVariable String description) {
        groupDAO.edit(group, name, description);
        return new ResponseEntity<Object>(group, HttpStatus.ACCEPTED);
    }

    @RequestMapping(value="group/{id}", method= RequestMethod.DELETE, consumes="application/json")
    @ResponseBody
    public ResponseEntity<Object> delete(@RequestBody Group group) {
        groupDAO.delete(group);
        return new ResponseEntity<Object>(group, HttpStatus.ACCEPTED);
    }

}
