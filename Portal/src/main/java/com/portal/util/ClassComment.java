package com.portal.util;

import com.portal.entity.Comment;

import java.util.List;


public class ClassComment {

	private long size;
	private List<Comment> comments;
	
	public void setSize(long size){
		this.size = size;
	}
	
	public void setComments(List<Comment> comments){
		this.comments = comments;
	}
	
	public long getSize(){
		return this.size;
	}
	
	public List<Comment> getComments(){
		return comments;		
	}
	
}
