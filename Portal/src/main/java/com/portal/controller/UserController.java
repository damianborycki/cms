package com.portal.controller;

import com.portal.dao.interfaces.UserDAO;
import com.portal.entity.Group;
import com.portal.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class UserController {
	
	@Autowired
	private UserDAO userDAO;
	
	@RequestMapping(value={"/", "/index", "/home"}, method=RequestMethod.GET)
	public String homePage() {
		return "index";
	}

    @RequestMapping(value={"/admin"}, method=RequestMethod.GET)
	public String adminPage() {
		return "adminIndex";
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
	public @ResponseBody User userPage(HttpServletResponse response, HttpServletRequest request) {
		
		try {
		
			org.springframework.security.core.userdetails.User user = 
			(org.springframework.security.core.userdetails.User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			
			User systemUser = new User(user.getUsername(), null);
			systemUser.setDateOfRegistration(null);
			
			response.setStatus(HttpServletResponse.SC_OK);
		
			return systemUser;
		
		} catch(Exception e) {
			
			System.out.println(e.getMessage());
			
			response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
			
			return null;
		}
	}
	
}
