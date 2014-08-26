package com.portal.util;

import com.portal.entity.User;

import java.util.List;

public class ClassUser {

	
	private long size;
	private List<User> users;
	
	public void setSize(long size){
		this.size = size;
	}
	
	public void setUsers(List<User> users){
		this.users = users;
	}
	
	public long getSize(){
		return this.size;
	}
	
	public List<User> getUsers(){
		return users;		
	}
}
