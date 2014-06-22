package com.portal.controller;

import com.portal.dao.interfaces.CommentDAOI;
import com.portal.dao.interfaces.UserDAOI;
import com.portal.entity.Comment;
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
	public @ResponseBody List<Comment> usersComments(@PathVariable("userId") long userID,
			@RequestParam("LIMIT") int limit, 
			@RequestParam("PAGE_NO") int pageNO,
			@RequestParam("sortOrder") String sortOrder,
			HttpServletResponse response){
		response.setStatus(HttpServletResponse.SC_OK);
		
		return commentDAO.getUserComments(userID, limit, pageNO, sortOrder);
	}
	
	@RequestMapping(value="/articleComments/{articleId}", method=RequestMethod.GET)
	public @ResponseBody List<Comment> articleComments(@PathVariable("articleId") long articleID,
			@RequestParam("LIMIT") int limit,
			@RequestParam("PAGE_NO") int pageNO,
			@RequestParam("sortOrder") String sortOrder,
			HttpServletResponse response){
		
		return commentDAO.getArticleComments(articleID, limit, pageNO, sortOrder);
	}

}
