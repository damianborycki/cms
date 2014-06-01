package com.portal.dao.interfaces;

import com.portal.entity.User;

import java.util.List;

public interface UserDAOI {

    public User getUser(String login);    

    public User addUser(User user);

    public void setUserGroup(String login, Long groupId);
    
    public void setUserData(User user);

    public List<User> findAll();
    
    public List<User> getAllUsers(int limit, int pageNo, String sortBy, String sortOrder);

    public void deleteUser(String login);

}
