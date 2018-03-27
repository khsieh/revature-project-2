package com.revature.warlockzone.controller;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.revature.warlockzone.beans.User;
import com.revature.warlockzone.services.UserService;

@RestController
@CrossOrigin
public class UserController {
	private static Logger log = Logger.getLogger(UserController.class.getName());
	@Autowired
	UserService userService;
	
	@RequestMapping(method = RequestMethod.POST, value = "/user")
	public void addUser(@RequestBody User user) {
		log.info("addUser " + user.toString());
		userService.addUser(user);
	}
	
	@RequestMapping("/user")
	public List<User> getUsers() {
		log.info("getAllUsers");
		return userService.getAllUsers();
	}
	
//	@RequestMapping("/user/{id}")
//	public User getUser(@PathVariable int id) {
//		return userService.getUser(id);
//	}
	
	@RequestMapping("/user/{username}")
	public User getUserByUsername(@PathVariable String username) {
		log.info("getUserByUsername: " + username);
		return userService.getUserByUsername(username);
	}
	
	@RequestMapping(method = RequestMethod.PUT, value = "/user")
	public void updateUser(@RequestBody User user) {
		log.info("updateUser: " + user.toString());
		userService.updateUser(user);
	}
	
	@RequestMapping(method = RequestMethod.DELETE, value = "/user/{id}")
	public void deleteUserById(@PathVariable int id) {
		log.warn("deleteUser: " + id);
		userService.deleteUserById(id);
	}
}
