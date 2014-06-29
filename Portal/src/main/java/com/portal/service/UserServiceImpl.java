package com.portal.service;

import org.springframework.transaction.annotation.Transactional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.portal.dao.interfaces.UserDAOI;
import com.portal.entity.User;

@Service
@Transactional
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserDAOI userDAO;
	
	@Override
	public User getUser(String login) {
		return userDAO.getUser(login);
	}

}
