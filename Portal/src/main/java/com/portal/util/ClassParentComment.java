package com.portal.util;

import java.util.List;


public class ClassParentComment {

	private long size;
	private List<ParentComment> comments;
	
	public void setSize(long size){
		this.size = size;
	}
	
	public void setComments(List<ParentComment> comments){
		this.comments = comments;
	}
	
	public long getSize(){
		return this.size;
	}
	
	public List<ParentComment> getComments(){
		return this.comments;		
	}
}
