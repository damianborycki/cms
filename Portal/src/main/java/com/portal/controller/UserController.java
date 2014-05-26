package com.portal.controller;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class UserController {
	
	@RequestMapping(value={"/", "index", "home"}, method=RequestMethod.GET)
	public ModelAndView homePage() {
		return new ModelAndView("home");
	}
	
	//Testowy
	@RequestMapping(value="/user-page", method=RequestMethod.GET)
	public @ResponseBody void userPage(HttpServletResponse response) {
		
		response.setStatus(HttpServletResponse.SC_NO_CONTENT);
	}
}
