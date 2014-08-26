package com.portal.util;

import java.util.ArrayList;
import java.util.List;

import com.portal.entity.Comment;

public class ParentComment extends Comment {
	private List<Comment> replies = new ArrayList<Comment>();
	
	public void addReply(Comment comment){
		replies.add(comment);
	}
	
	public List<Comment> getReplies(){
		return replies;
	}
	
}
