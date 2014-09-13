package com.portal.util;

import com.portal.entity.Comment;

import java.util.List;

import org.codehaus.jackson.annotate.JsonCreator;
import org.codehaus.jackson.annotate.JsonProperty;


public class ClassComment {

	private long size;
	private List<Comment> comments;
	
	@JsonCreator
	public ClassComment(@JsonProperty("size") long size, 
			     		@JsonProperty("comments") List<Comment> comments) {
		
		this.size = size;
		this.comments = comments;
	}
	
	public ClassComment() { }
	
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
