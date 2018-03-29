package com.revature.warlockzone.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.revature.warlockzone.beans.User;
import com.revature.warlockzone.services.UserService;

@RestController
@CrossOrigin
public class CheckTokenController {
	@Autowired
	private UserService userService;
	
	@SuppressWarnings("rawtypes")
	@GetMapping("/checkToken")
	public ResponseEntity checkToken(@RequestParam("token") String tokens) {
		List<User> users = userService.getAllUsers();
		for(int i = 0; i < users.size(); i++) {
			if(users.get(i).getToken().equals(tokens)) {
			    return new ResponseEntity(HttpStatus.ACCEPTED);			
		}
	}
	    return new ResponseEntity(HttpStatus.NOT_ACCEPTABLE);
	}
}
