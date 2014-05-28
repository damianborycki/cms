package com.portal.dao.implementation;


import com.portal.dao.interfaces.CommentDAOI;
import com.portal.entity.Comment;

import java.util.*;

public class CommentDAOImpl implements CommentDAOI {

	@Override
	public Comment getById(long id) {
		Comment c = new Comment();
		c.setId(id);
		c.setDate(Calendar.getInstance().getTime());
		c.setContent("test comment " + id);
		
		return c;
	}
	Comment root(Comment comment){
		Comment c = comment;
		while(c.getParent() != null)
			c = this.getById(c.getParent());
		return c;
	}
	@Override
	public List<Comment> get(int limit, int pageNum,
			Comparator<Comment> sortby, boolean asc) {
		// TODO Auto-generated method stub
		return new ArrayList<Comment>();
	}

	@Override
	public List<Comment> get(long user_id, int limit, int pageNum,
			Comparator<Comment> sortby, boolean asc) {
		// TODO Auto-generated method stub
		return new ArrayList<Comment>();
	}
	@Override
	public List<Comment> get(long articleId, long thread, int limit,
			int pageNum, Comparator<Comment> sortby, boolean asc) {
		// TODO Auto-generated method stub
		return new ArrayList<Comment>();
	}
	@Override
	public List<Comment> getApprovedByUser(long userId) {
		// TODO Auto-generated method stub
		return new ArrayList<Comment>();
	}
	@Override
	public void add(long userId, String content, Long parent, long articleId) {
		// TODO Auto-generated method stub
	}
	@Override
	public void delete(long comment_id) {
		Comment c = this.getById(comment_id);
		for(Comment comment : children(c)){
			delete(comment.getId());
		}
		// TODO delete this comment
	}
	@Override
	public List<Comment> children(Comment comment) {
		// TODO Auto-generated method stub
		return new LinkedList<Comment>();
	}
	

}
