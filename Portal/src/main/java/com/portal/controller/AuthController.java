package com.portal.controller;

import javax.servlet.http.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException; 
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import com.portal.dao.interfaces.UserDAOI;

@Controller
public class AuthController {
	
	@Autowired
	@Qualifier("authManager")
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private UserDAOI userDAO;
	
	@RequestMapping(value = "/login", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, headers = {"content-type=application/json"})
	public void createSession(@RequestBody com.portal.entity.User user, HttpServletRequest request, HttpServletResponse response) {
		
	    Authentication authenticationToken = new UsernamePasswordAuthenticationToken(user.getLogin(), user.getPassword());
	    
	    try {

	        Authentication authentication = authenticationManager.authenticate(authenticationToken);

	        SecurityContext securityContext = SecurityContextHolder.getContext();

	        securityContext.setAuthentication(authentication);

	        HttpSession session = request.getSession(true);
	        session.setAttribute("SPRING_SECURITY_CONTEXT", securityContext);
	        
	        userDAO.setLastLoginDate(user.getLogin());
	        
	        response.setStatus(HttpServletResponse.SC_CREATED);

	    } catch (AuthenticationException ex) {
	    	
	    	response.setStatus(HttpServletResponse.SC_NO_CONTENT);
	    	
	    }
	    
	}
	
	@RequestMapping(value = "/logout", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteSession(HttpServletRequest request) {
		
		SecurityContextHolder.getContext().setAuthentication(null);
	}
	
}
