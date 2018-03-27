package com.revature.warlockzone.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.revature.warlockzone.beans.User;
import com.revature.warlockzone.services.UserService;

@RestController
@CrossOrigin
public class RegisterController {
	
	@Autowired
	UserService userService;
	//get user information in form and save in database
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	  public ModelAndView addUser(HttpServletRequest request, HttpServletResponse response,
			  @ModelAttribute("user") User user) {

	  userService.addUser(user); //add user to database

	  return new ModelAndView("welcome", "firstname", user.getFirstName());//returns next view + possilbe url

	  }

}
