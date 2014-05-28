package com.portal.dao.interfaces;

import com.portal.entity.User;

import java.util.List;

public interface UserDAO {

    public User getUser(String login);

    public void addUser(User user);

    public void setUserGroup(String login, Long groupId);

    public List<User> findAll();

    public void deleteUser(User user);

}
