package com.portal.controller;

import com.portal.dao.interfaces.CommentDAOI;
import com.portal.dao.interfaces.UserDAOI;
import com.portal.entity.Comment;
import com.portal.util.ClassComment;
import com.portal.util.ClassParentComment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@Controller
public class CommentController {
	
	@Autowired
	private UserDAOI userDAO;
	
	@Autowired
	private CommentDAOI commentDAO;
	
	@RequestMapping(value = "/comment", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, headers = {"content-type=application/json"})
	public void addComment(@RequestBody Comment comment, HttpServletRequest request, HttpServletResponse response) {
		
		boolean result = commentDAO.add(comment.getUser().getLogin(), 
					   comment.getContent(),
					   comment.getParent().getId(), 
					   comment.getArticle().getId());
		
		if (result) response.setStatus(HttpServletResponse.SC_CREATED);
		else response.setStatus(HttpServletResponse.SC_NO_CONTENT);

	}
	
	@RequestMapping(value="/setCommentStatus", method=RequestMethod.PATCH)
	public void setCommentStatus(@RequestBody ClassComment commentWrapper, HttpServletResponse response) {
		
		commentDAO.setStates(commentWrapper.getComments());
			
		response.setStatus(HttpServletResponse.SC_OK);
	}
	
	@RequestMapping(value="/comment", method=RequestMethod.DELETE)
	public void deleteComments(@RequestBody List<Comment> comments, HttpServletResponse response) {
		
		commentDAO.delete(comments);
		
		response.setStatus(HttpServletResponse.SC_OK);
	}
	
	@RequestMapping(value="/userComments/{login:.+}", method=RequestMethod.GET)	
	public @ResponseBody ClassComment usersComments(@PathVariable("login") String login,
			@RequestParam("limit") int limit, 
			@RequestParam("pageNo") int pageNO,
			@RequestParam("sortOrder") String sortOrder,
			HttpServletResponse response){
		response.setStatus(HttpServletResponse.SC_OK);
		
		ClassComment cc = commentDAO.getUserComments(login, limit, pageNO, sortOrder);
		
		return cc;
	}
	
	@RequestMapping(value="/articleComments/{articleId}", method=RequestMethod.GET)
	public @ResponseBody ClassParentComment articleComments(@PathVariable("articleId") long articleID,
			@RequestParam("limit") int limit,
			@RequestParam("pageNo") int pageNO,
			@RequestParam("sortOrder") String sortOrder,
			HttpServletResponse response){
		
		ClassParentComment coms = commentDAO.getArticleComments(articleID, limit, pageNO, sortOrder);
		
		for (int i = 0; i < coms.getComments().size(); i++) {
			coms.getComments().get(i).getArticle().setComments(null);
		}
		
		return coms;
	}
	
	@RequestMapping(value="/comment/{commentId}", method=RequestMethod.GET)
	public @ResponseBody Comment getComment(@PathVariable("commentId") long commentId, HttpServletResponse response) {
		
		response.setStatus(HttpServletResponse.SC_OK);
		
		return commentDAO.getById(commentId);
	}
	
	@RequestMapping(value="/comment", method=RequestMethod.GET)
	public @ResponseBody ClassComment getAllComments(@RequestParam("status") long status, 
			@RequestParam("limit") int limit, 
			@RequestParam("pageNo") int pageNo,			
			@RequestParam("sortOrder") String sortOrder,
			HttpServletResponse response){
		
		if(status == 0){
			return commentDAO.getAllComments(limit, pageNo, sortOrder);
		} else {
			return commentDAO.getAllComments(status, limit, pageNo, sortOrder);	
		}				
	}

	
	@RequestMapping(value="/totalComments", method=RequestMethod.GET)
	public @ResponseBody long totalComments(@RequestParam("status") long status,	
			HttpServletResponse response){
		
		return commentDAO.getTotalComments(status);							
	}
	
	@RequestMapping(value="/comments/{login:.+}", method=RequestMethod.GET)
	public @ResponseBody ClassComment getUserComments(@PathVariable("login") String login,
			@RequestParam("status") long status, 
			@RequestParam("limit") int limit, 
			@RequestParam("pageNo") int pageNo,			
			@RequestParam("sortOrder") String sortOrder,
			HttpServletResponse response){
		 	
		return commentDAO.getUserComments(login, status, limit, pageNo, sortOrder);
	}
}
