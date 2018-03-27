package com.revature.warlockzone.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.revature.warlockzone.beans.User;
import com.revature.warlockzone.services.LoginService;

@RestController
@CrossOrigin
public class LoginController {

	@Autowired
	LoginService loginService;
	@Autowired
	User user;
	
	//Response entity takes care of returning status
	@RequestMapping(method = RequestMethod.POST, value = "/login")
	public ResponseEntity<User> login(@RequestBody User user){
	    user = loginService.authenticate(user.getUsername(), user.getPassword());
	    return (user==null) ? ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null) : ResponseEntity.ok(user);
	}
	
}
