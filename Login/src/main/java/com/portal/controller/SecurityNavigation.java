package com.portal.controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException; 
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

@Controller
@Scope("request")
public class SecurityNavigation {
	
	private String username;
	private String password;
	
	@Autowired
	@Qualifier("authManager")
	private AuthenticationManager authenticationManager;
	
	@RequestMapping(value="/login", method=RequestMethod.GET)
	public ModelAndView loginForm() {
		return new ModelAndView("login-form");
	}
	
	@RequestMapping(value="/error-login", method=RequestMethod.GET)
	public ModelAndView invalidLogin() {
		ModelAndView modelAndView = new ModelAndView("login-form");
		modelAndView.addObject("error", true);
		return modelAndView;
	}
	
	@RequestMapping(value="/success-login", method=RequestMethod.GET)
	public ModelAndView successLogin() {
		return new ModelAndView("success-login");
	}
	
	@RequestMapping(value = "/login-rest", method = RequestMethod.POST)
	public @ResponseBody String authentication(@RequestParam("username") String username,
	        @RequestParam("password") String password, HttpServletRequest request) {
		
		this.username = username;
		this.password = password;

	    Authentication authenticationToken = new UsernamePasswordAuthenticationToken(username, password);
	    
	    try {

	        Authentication authentication = authenticationManager.authenticate(authenticationToken);

	        SecurityContext securityContext = SecurityContextHolder.getContext();

	        securityContext.setAuthentication(authentication);

	        HttpSession session = request.getSession(true);
	        session.setAttribute("SPRING_SECURITY_CONTEXT", securityContext);

	        return "Success";
	    } catch (AuthenticationException ex) {
	        return "Error " + ex.getMessage();
	    }

	}
	
	@RequestMapping(value = "/logout-rest", method = RequestMethod.DELETE)
	public @ResponseBody String logout(HttpServletRequest request) {
		
		try {
			request.logout();
		} catch (ServletException e) {
			e.printStackTrace();
			
			return "Error: " + e.getMessage();
		}
		
		return "Success";
	}

}
