package com.portal.controller;

import com.portal.dao.interfaces.CommentStateDAOI;
import com.portal.entity.CommentState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Mateusz on 2014-06-25.
 */
@Controller
public class CommentStateController {

    @Autowired
    private CommentStateDAOI commentStateDAO;

    @RequestMapping(value = "commentState", method = RequestMethod.GET)
    @ResponseBody
    public List<CommentState> getAll() {
        return commentStateDAO.findAll();
    }

    @RequestMapping(value = "commentState/{id}", method = RequestMethod.GET)
    @ResponseBody
    public CommentState get(@PathVariable Long id) {
        return commentStateDAO.get(id);
    }

    @RequestMapping(value="commentState", method= RequestMethod.POST, consumes="application/json")
    @ResponseBody
    public ResponseEntity<Object> create(@RequestBody CommentState commentState){
        commentStateDAO.add(commentState);
        return new ResponseEntity<Object>(commentState, HttpStatus.ACCEPTED);
    }

    @RequestMapping(value = "commentState/{id}", method = RequestMethod.PUT, consumes = "application/json")
    @ResponseBody
    public ResponseEntity<Object> put(@RequestBody CommentState commentState, @PathVariable String name, @PathVariable String description) {
        commentStateDAO.edit(commentState, name, description);
        return new ResponseEntity<Object>(commentState, HttpStatus.ACCEPTED);
    }

    @RequestMapping(value="commentState/{id}", method= RequestMethod.DELETE, consumes="application/json")
    @ResponseBody
    public ResponseEntity<Object> delete(@RequestBody CommentState commentState) {
        commentStateDAO.delete(commentState);
        return new ResponseEntity<Object>(commentState, HttpStatus.ACCEPTED);
    }

}
