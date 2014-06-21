package com.portal.controller;

import com.portal.dao.interfaces.CommentDAOI;
import com.portal.dao.interfaces.UserDAOI;
import com.portal.entity.Group;
import com.portal.entity.User;
import com.portal.entity.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class CommentController {
	
	@Autowired
	private UserDAOI userDAO;
	
	@Autowired
	private CommentDAOI commentDAO;
	
	@RequestMapping(value="/comment", method=RequestMethod.POST)
	public void addComment(@RequestBody Comment comment, HttpServletResponse response) {
		
		try {
			commentDAO.add(comment.getUser().getId(), 
						   comment.getContent(), 
						   comment.getParent().getId(), 
						   comment.getArticle().getId());
			
			response.setStatus(HttpServletResponse.SC_CREATED);
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
			
			response.setStatus(HttpServletResponse.SC_NO_CONTENT);
		}
		
	}
	
}
