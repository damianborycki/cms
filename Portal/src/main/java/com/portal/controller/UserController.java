package com.portal.controller;

import com.portal.dao.interfaces.UserDAOI;
import com.portal.entity.Group;
import com.portal.entity.User;
import com.portal.util.ClassUser;
import com.portal.util.ClassPassword;

import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class UserController {
	
	@Autowired
	private UserDAOI userDAO;
	
	@Autowired
	private MailSender mailSender;
	
	@RequestMapping(value="/user/{login:.+}", method=RequestMethod.GET)
	public @ResponseBody User getUserData(@PathVariable("login") String login, HttpServletResponse response) {
		
		try {
			User user = userDAO.getUser(login);
			user.setId(null);
			user.setPassword(null);
			user.getGroup().setId(null);
			
			response.setStatus(HttpServletResponse.SC_OK);
			
			return user;
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
			response.setStatus(HttpServletResponse.SC_NO_CONTENT);
			
			return null;
		}
		
	}
	
	@RequestMapping(value="/userProfile/{login:.+}", method=RequestMethod.GET)
	public @ResponseBody User getUserProfile(@PathVariable("login") String login, HttpServletResponse response) {
		
		User user = userDAO.getUser(login);
		user.setId(null);
		user.setPassword(null);
		user.setGroup(null);
		
		return user;
	}
	
	@RequestMapping(value="/setUserGroup/{login:.+}", method=RequestMethod.PATCH)
	public void setUserGroup(@PathVariable("login") String login, @RequestBody Group group, HttpServletResponse response) {
		
		try {
			boolean result = userDAO.setUserGroup(login, group.getId());

			if(result) response.setStatus(HttpServletResponse.SC_ACCEPTED);
			else response.setStatus(HttpServletResponse.SC_NO_CONTENT);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			response.setStatus(HttpServletResponse.SC_NO_CONTENT);
		}
		
	}
	
	@RequestMapping(value="/getCurrentUser", method=RequestMethod.GET)
	public @ResponseBody User userPage(HttpServletResponse response, HttpServletRequest request) {
		
		try {
			
			User u = userDAO.getLoggedUser();
			
			System.out.println(u.getLogin());
			
			return u;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
			return null;
		}
		
	}
	
	@RequestMapping(value="/user/{login:.+}", method=RequestMethod.DELETE)
	public void deleteData(@PathVariable("login") String login, HttpServletResponse response) {
		
		try {
			userDAO.deleteUser(login);
			response.setStatus(HttpServletResponse.SC_OK);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			response.setStatus(HttpServletResponse.SC_NO_CONTENT);
		}
	}
	
	@RequestMapping(value="/user/{login:.+}", method=RequestMethod.PATCH)
	public void setData(@PathVariable("login") String login, @RequestBody User user, HttpServletResponse response) {
		
		if(userDAO.getLoggedUser().getLogin().trim().equals(login.trim())) {
			try {
				boolean result = userDAO.setUserData(user);
				
				if(result) response.setStatus(HttpServletResponse.SC_OK);
				else response.setStatus(HttpServletResponse.SC_NO_CONTENT);
			} catch (Exception e) {
				System.out.println(e.getMessage());
				response.setStatus(HttpServletResponse.SC_NO_CONTENT);
			}
		} else {
			response.setStatus(HttpServletResponse.SC_FORBIDDEN);
		}
		
	}
	
    @RequestMapping(value="/user", method=RequestMethod.POST)
    public void createUser(@RequestBody User user, HttpServletResponse response){
    	
        try {
            response.setStatus(HttpServletResponse.SC_CREATED);     
            User u = userDAO.addUser(user, true);
            
            if (u != null) response.setStatus(HttpServletResponse.SC_CREATED);
            else response.setStatus(HttpServletResponse.SC_NO_CONTENT);
        } catch(Exception e){
            System.out.println(e.getMessage());         
            response.setStatus(HttpServletResponse.SC_NO_CONTENT);
        }
            
    }
    
    @RequestMapping(value="/createUser", method=RequestMethod.POST)
    public void createUserViaPanel(@RequestBody User user, HttpServletResponse response){
    	
        try {
            response.setStatus(HttpServletResponse.SC_CREATED);     
            User u = userDAO.addUser(user, false);
            
            if (u != null) response.setStatus(HttpServletResponse.SC_CREATED);
            else response.setStatus(HttpServletResponse.SC_NO_CONTENT);
        } catch(Exception e){
            System.out.println(e.getMessage());         
            response.setStatus(HttpServletResponse.SC_NO_CONTENT);
        }
            
    }
    
    @RequestMapping(value = "/totalUsers", method = RequestMethod.GET)
    public @ResponseBody int totalUsers(HttpServletResponse response){    	
    	try {
    		response.setStatus(HttpServletResponse.SC_CREATED);
    		return userDAO.totalUsers();
    	} catch (Exception e){
    		System.out.println(e.getMessage());;
    		response.setStatus(HttpServletResponse.SC_NO_CONTENT);
    		return 0;
    	}
    }
    
	@RequestMapping(value = "/user", method = RequestMethod.GET)
	public @ResponseBody ClassUser getAllUsers(@RequestParam("limit") int limit, 
			@RequestParam("pageNo") int pageNo, 
			@RequestParam("sortBy") String sortBy, 
			@RequestParam("sortOrder") String sortOrder, 
			HttpServletResponse response){
		
		try{
            response.setStatus(HttpServletResponse.SC_CREATED);
			return userDAO.getAllUsers(limit, pageNo, sortBy, sortOrder);
		} catch (Exception e){
			System.out.println(e.getMessage());
			response.setStatus(HttpServletResponse.SC_NO_CONTENT);
			return null;
		}
		
	}
	
	@RequestMapping(value = "/loginExists", method = RequestMethod.POST)
	public @ResponseBody boolean loginExist(@RequestBody User login, HttpServletResponse response){
		
		return userDAO.loginExists(login);
	}
	
	@RequestMapping(value = "/emailExists", method = RequestMethod.POST)
	public @ResponseBody boolean emailExists(@RequestBody User email, HttpServletResponse response){
		
		return userDAO.emailExists(email);
	}
	
	@RequestMapping(value = "/activate", method = RequestMethod.PATCH)
	public void sendMail(@RequestParam("code") String code, HttpServletResponse response){
		
		userDAO.activateAccount(code);

	}
	
	@RequestMapping(value = "/changePassword", method = RequestMethod.POST)
	public @ResponseBody boolean changePassword(@RequestBody ClassPassword passwords, HttpServletResponse response){
	
		return userDAO.changPassword(passwords);
	}
	
}
