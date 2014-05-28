package com.portal.controller;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import com.portal.dao.UserDAO;
import com.portal.entity.Group;
import com.portal.entity.User;

@Controller
public class UserController {
	
	@Autowired
	private UserDAO userDAO;
	
	@RequestMapping(value={"/", "index", "home"}, method=RequestMethod.GET)
	public ModelAndView homePage() {
		return new ModelAndView("home");
	}
	
	@RequestMapping(value="/user/{login}", method=RequestMethod.GET)
	public @ResponseBody User getUserData(@PathVariable("login") String login, HttpServletResponse response) {
		
		User user = userDAO.getUser(login);
		user.setId(null);
		user.setPassword(null);
		user.getGroup().setId(null);
		
		return user;
	}
	
	@RequestMapping(value="/setUserGroup/{login}", method=RequestMethod.POST)
	public void setUserGroup(@PathVariable("login") String login, @RequestBody Group group, HttpServletResponse response) {
		
		userDAO.setUserGroup(login, group.getId());
		
		response.setStatus(HttpServletResponse.SC_ACCEPTED);
		
	}
	
	//Testowy
	@RequestMapping(value="/getCurrentUserLogin", method=RequestMethod.GET)
	public @ResponseBody User userPage(HttpServletResponse response) {
		
		org.springframework.security.core.userdetails.User user = 
		(org.springframework.security.core.userdetails.User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		
		User systemUser = new User(user.getUsername(), null);
		systemUser.setDateOfRegistration(null);
		
		return systemUser;
	}
	
	
}
