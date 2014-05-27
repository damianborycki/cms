package com.portal.dao;

import com.portal.entity.User;

public interface UserDAO {
	
	public User getUser(String login);
	public void addUser(User user);
	public void setUserGroup(String login, Long groupId);

}
