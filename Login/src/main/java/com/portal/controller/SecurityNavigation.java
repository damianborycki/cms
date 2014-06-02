package com.portal.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException; 
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

@Controller
public class SecurityNavigation {
	
	private String username;
	private String password;
	
	@Autowired
	@Qualifier("authManager")
	private AuthenticationManager authenticationManager;
	
	@RequestMapping(value = "/login", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, headers = {"content-type=application/json"})
	public void createSession(@RequestBody String authData, HttpServletRequest request, HttpServletResponse response) {
		
		String[] params = authData.split("&");
		
		this.username = params[0].split("=")[1];
		this.password = params[1].split("=")[1];

	    Authentication authenticationToken = new UsernamePasswordAuthenticationToken(username, password);
	    
	    response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
	    
	    try {

	        Authentication authentication = authenticationManager.authenticate(authenticationToken);

	        SecurityContext securityContext = SecurityContextHolder.getContext();

	        securityContext.setAuthentication(authentication);

	        HttpSession session = request.getSession(true);
	        session.setAttribute("SPRING_SECURITY_CONTEXT", securityContext);
	        
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
