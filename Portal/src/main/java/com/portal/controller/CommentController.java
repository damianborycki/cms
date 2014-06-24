package com.portal.controller;

import com.portal.dao.interfaces.CommentDAOI;
import com.portal.dao.interfaces.UserDAOI;
import com.portal.entity.Comment;
import com.portal.init.ClassComment;
import com.portal.init.ClassParentComment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

@Controller
public class CommentController {
	
	@Autowired
	private UserDAOI userDAO;
	
	@Autowired
	private CommentDAOI commentDAO;
	
	@RequestMapping(value="/comment", method=RequestMethod.POST)
	public void addComment(@RequestBody Comment comment, HttpServletResponse response) {
		
		commentDAO.add(comment.getUser().getId(), 
					   comment.getContent(),
					   comment.getParent().getId(), 
					   comment.getArticle().getId());
			
		response.setStatus(HttpServletResponse.SC_CREATED);
	}
	
	@RequestMapping(value="/setCommentStatus", method=RequestMethod.PATCH)
	public void setCommentStatus(@RequestBody List<Comment> comments, HttpServletResponse response) {
		
		commentDAO.setStates(comments);
			
		response.setStatus(HttpServletResponse.SC_OK);
	}
	
	@RequestMapping(value="/comment", method=RequestMethod.DELETE)
	public void deleteComments(@RequestBody List<Comment> comments, HttpServletResponse response) {
		
		commentDAO.delete(comments);
		
		response.setStatus(HttpServletResponse.SC_OK);
	}
	
	@RequestMapping(value="/userComments/{userId}", method=RequestMethod.GET)	
	public @ResponseBody ClassComment usersComments(@PathVariable("userId") long userID,
			@RequestParam("limit") int limit, 
			@RequestParam("pageNo") int pageNO,
			@RequestParam("sortOrder") String sortOrder,
			HttpServletResponse response){
		response.setStatus(HttpServletResponse.SC_OK);
		
		return commentDAO.getUserComments(userID, limit, pageNO, sortOrder);
	}
	
	@RequestMapping(value="/articleComments/{articleId}", method=RequestMethod.GET)
	public @ResponseBody ClassParentComment articleComments(@PathVariable("articleId") long articleID,
			@RequestParam("limit") int limit,
			@RequestParam("pageNo") int pageNO,
			@RequestParam("sortOrder") String sortOrder,
			HttpServletResponse response){
		
		return commentDAO.getArticleComments(articleID, limit, pageNO, sortOrder);
	}
	
	@RequestMapping(value="/comment/{commentId}", method=RequestMethod.GET)
	public @ResponseBody Comment getComment(@PathVariable("commentId") long commentId, HttpServletResponse response) {
		
		response.setStatus(HttpServletResponse.SC_OK);
		
		return commentDAO.getById(commentId);
	}
	
	@RequestMapping(value="/comment", method=RequestMethod.GET)
	public @ResponseBody ClassParentComment getAllComments(@RequestParam("status") long status, 
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
	
	@RequestMapping(value="/comments/{userID}", method=RequestMethod.GET)
	public @ResponseBody ClassComment getUserComments(@PathVariable("userID") long userID,
			@RequestParam("status") long status, 
			@RequestParam("limit") int limit, 
			@RequestParam("pageNo") int pageNo,			
			@RequestParam("sortOrder") String sortOrder,
			HttpServletResponse response){
		 	
		return commentDAO.getUserComments(userID, status, limit, pageNo, sortOrder);
	}
}
