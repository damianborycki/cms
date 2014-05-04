package com.portal.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LinkNavigation {
	
	@RequestMapping(value={"/", "index", "home"}, method=RequestMethod.GET)
	public ModelAndView homePage() {
		return new ModelAndView("home");
	}
	
	@RequestMapping(value="/admin", method=RequestMethod.GET)
	public ModelAndView adminPage() {
		return new ModelAndView("admin");
	}
	
	@RequestMapping(value="/moderation", method=RequestMethod.GET)
	public ModelAndView moderatorPage() {
		return new ModelAndView("moderation");
	}
	
	@RequestMapping(value="/user", method=RequestMethod.GET)
	public ModelAndView userPage() {
		return new ModelAndView("user");
	}
	
	@RequestMapping(value="/user-page", method=RequestMethod.GET)
	public @ResponseBody String adminP() {
		return "User page";
	}

}
