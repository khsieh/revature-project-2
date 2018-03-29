package com.revature.warlockzone.controller;

import java.util.List;
import java.util.UUID;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.revature.warlockzone.beans.User;
import com.revature.warlockzone.services.LoginService;
import com.revature.warlockzone.services.UserService;

@RestController
@CrossOrigin
public class LoginController {
	private static Logger log = Logger.getLogger(LoginController.class.getName());
	@Autowired
	LoginService loginService;
	@Autowired
	User user;
	@Autowired
	UserService userService;
	//Response entity takes care of returning status
	@RequestMapping(method = RequestMethod.POST, value = "/login")
	public ResponseEntity<User> login(@RequestBody User user){
		log.info("login: username: " +user.getUsername() + ", password: " +  user.getPassword());
	    user = loginService.authenticate(user.getUsername(), user.getPassword());
	    if(user != null) {
	    	user.setToken(UUID.randomUUID().toString());
	    	userService.updateToken(user);
	    }
	    log.info("login result\t " + ((user==null) ? "null" : user.toString()));
	    return (user==null) ? ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null) : ResponseEntity.status(HttpStatus.ACCEPTED).body(user);
	}
	
	//logout servlet
	@SuppressWarnings("rawtypes")
	@RequestMapping(method = RequestMethod.POST , value = "/exit")
	public ResponseEntity loggingout(@RequestParam("token") String token){
		List<User> user = userService.getAllUsers();
		for(int i = 0; i < user.size(); i++) {
			if(user.get(i).getToken().equals(token)) {
				user.get(i).setToken(null);
				userService.updateUser(user.get(i));
				System.out.println(user.get(i).getToken());
				
			}
		}
        return new ResponseEntity(HttpStatus.ACCEPTED);
	}

	
}
