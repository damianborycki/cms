package com.portal.controller;

import com.portal.dao.interfaces.UserDAOI;
import com.portal.entity.Group;
import com.portal.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class UserController {
	
	@Autowired
	private UserDAOI userDAO;
	
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
	
	@RequestMapping(value="/userProfile/{login}", method=RequestMethod.GET)
	public @ResponseBody User getUserProfile(@PathVariable("login") String login, HttpServletResponse response) {
		
		User user = userDAO.getUser(login);
		user.setId(null);
		user.setPassword(null);
		user.setGroup(null);
		
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
	
	@RequestMapping(value="/user/{login}", method=RequestMethod.DELETE)
	public void deleteData(@PathVariable("login") String login, HttpServletResponse response) {
		
		try {
			userDAO.deleteUser(login);
			
			response.setStatus(HttpServletResponse.SC_OK);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			
			response.setStatus(HttpServletResponse.SC_NO_CONTENT);
		}
	}
	
    @RequestMapping(value="/user", method=RequestMethod.POST)
    public void createUser(@RequestBody User user, HttpServletResponse response){
    	
        try{
            response.setStatus(HttpServletResponse.SC_CREATED);     
            userDAO.addUser(user);
            
        } catch(Exception e){
            System.out.println(e.getMessage());         
            response.setStatus(HttpServletResponse.SC_NO_CONTENT);
        }
            
    }
    
	@RequestMapping(value = "/user", method = RequestMethod.GET)
	public @ResponseBody List<User> getAllUsers(@RequestParam("LIMIT") int limit, @RequestParam("PAGE_NO") int pageNo, @RequestParam("sortBy") String sortBy, @RequestParam("sortOrder") String sortOrder, HttpServletResponse response){
		
		try{
            response.setStatus(HttpServletResponse.SC_CREATED);
			return userDAO.getAllUsers(limit, pageNo, sortBy, sortOrder);
		} catch (Exception e){
			System.out.println(e.getMessage());
			response.setStatus(HttpServletResponse.SC_NO_CONTENT);
			return null;
		}
		
	}
	
}
