package com.revature.warlockzone.controller;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.revature.warlockzone.beans.User;
import com.revature.warlockzone.services.UserService;

@RestController
@CrossOrigin
public class UserController {
	private static Logger log = Logger.getLogger(UserController.class.getName());
	@Autowired
	UserService userService;
	
	public boolean tokenCheck(String token) {
		List<User> users = userService.getAllUsers();
		for(int i = 0; i < users.size(); i++) {
			if(users.get(i).getToken().equals(token)) {
				return true;
			}
		}
		
		return false;
	}
	@RequestMapping(method = RequestMethod.POST, value = "/user")
	public void addUser(@RequestBody User user) {
		log.info("addUser " + user.toString());
		userService.addUser(user);
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/validateUser")
	public ResponseEntity<User> login(@RequestBody User user){
	    user = userService.authenticate(user.getUsername(), user.getPassword());
	    return (user==null) ? ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null) : ResponseEntity.status(HttpStatus.ACCEPTED).body(null);
	}
	
	@RequestMapping("/user")
	public List<User> getUsers(@RequestParam("token") String tokens) {
		if(tokenCheck(tokens)) {
			log.info("getAllUsers");
			return userService.getAllUsers();
		
		}
		return null;
	}
	
//	@RequestMapping("/user/{id}")
//	public User getUser(@PathVariable int id) {
//		return userService.getUser(id);
//	}
	
	@RequestMapping("/user/{username}")
	public User getUserByUsername(@PathVariable String username, @RequestParam("token") String tokens) {
		if(tokenCheck(tokens)) {
			log.info("getUserByUsername: " + username);
			return userService.getUserByUsername(username);
		}
		return null;
	}
	
	@RequestMapping(method = RequestMethod.PUT, value = "/user")
	public void updateUser(@RequestBody User user, @RequestParam("token") String tokens) {
		if(tokenCheck(tokens)) {
			log.info("updateUser: " + user.toString());
			userService.updateUser(user);
			
		}
	}
	
	@RequestMapping(method = RequestMethod.DELETE, value = "/user/{id}")
	public void deleteUserById(@PathVariable int id , @RequestParam("token") String tokens) {
		if(tokenCheck(tokens)) {
			log.warn("deleteUser: " + id);
			userService.deleteUserById(id);
			
		}
	}
}
