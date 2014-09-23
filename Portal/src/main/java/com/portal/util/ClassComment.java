package com.portal.util;

import com.portal.entity.Comment;

import java.util.*;

import org.codehaus.jackson.annotate.JsonCreator;
import org.codehaus.jackson.annotate.JsonProperty;


public class ClassComment {

	private long size;
	private List<Comment> comments;
	private List<Long> articleIds;
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
		List<Long> artIds = new ArrayList<Long>();
		
		for(Comment c : comments){
			artIds.add(c.getArticle().getId());
		}
		setArticleIds(artIds);
	}
	
	public void setArticleIds(List<Long> articleIds){
		this.articleIds = articleIds;
	}
	
	public long getSize(){
		return this.size;
	}
	
	public List<Comment> getComments(){
		return comments;		
	}
	
	public List<Long> getArticleIds(){
		return articleIds;
	}
	
}
