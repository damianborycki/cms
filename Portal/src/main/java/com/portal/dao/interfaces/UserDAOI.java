package com.portal.dao.interfaces;

import com.portal.entity.Group;
import com.portal.entity.User;
import com.portal.util.ClassUser;
import com.portal.util.ClassPassword;

import java.util.List;

public interface UserDAOI {

    public User getUser(String login);    

    public User addUser(User user, boolean withActivation);

    public boolean setUserGroup(String login, Long groupId);
    
    public boolean setUserData(User user);
    
    public User getLoggedUser();

    public List<User> findAll();
    
    public ClassUser getAllUsers(int limit, int pageNo, String sortBy, String sortOrder);
    
    public int totalUsers();

    public void deleteUser(String login);
    
    public void setLastLoginDate(String login);
    
    public boolean loginExists(User login);
    
    public boolean emailExists(User email);
    
    public void activateAccount(String code);

    public boolean existsForGroup(Group group);
    
    public boolean changPassword(ClassPassword passwords);
    
    public void setUserAvatar(String login, String imageId);
}
