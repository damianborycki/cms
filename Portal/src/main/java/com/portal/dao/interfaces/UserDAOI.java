package com.portal.dao.interfaces;

import com.portal.entity.User;
import com.portal.init.ClassUser;

import java.util.List;

public interface UserDAOI {

    public User getUser(String login);    

    public User addUser(User user);

    public void setUserGroup(String login, Long groupId);
    
    public void setUserData(User user);
    
    public User getLoggedUser();

    public List<User> findAll();
    
    public ClassUser getAllUsers(int limit, int pageNo, String sortBy, String sortOrder);
    
    public int totalUsers();

    public void deleteUser(String login);

}
