package com.revature.warlockzone.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.revature.warlockzone.WarlockZoneApplication;
import com.revature.warlockzone.beans.User;
import com.revature.warlockzone.services.LoginService;

@RestController
@CrossOrigin
public class LoginController {
	private static Logger log = Logger.getLogger(LoginController.class.getName());
	@Autowired
	LoginService loginService;
	@Autowired
	User user;
	
	//Response entity takes care of returning status
	@RequestMapping(method = RequestMethod.POST, value = "/login")
	public ResponseEntity<User> login(@RequestBody User user){
		log.info("login: username: " +user.getUsername() + ", password: " +  user.getPassword());
	    user = loginService.authenticate(user.getUsername(), user.getPassword());
	    log.info("login result\t " + ((user==null) ? "null" : user.toString()));
	    return (user==null) ? ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null) : ResponseEntity.status(HttpStatus.ACCEPTED).body(user);
	}
	
}
