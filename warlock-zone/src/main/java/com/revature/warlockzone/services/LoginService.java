package com.revature.warlockzone.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.warlockzone.beans.User;

@Service
public class LoginService {

	@Autowired
	UserService userService;
	@Autowired
	User user;
	
	public User authenticate(String username, String password) {
		
		user = userService.getUserByUsername(username);
		
		if(user == null) {
			return null;
		}
		else if(user.getPassword().equals(password)) {
			user.setPassword(null);
			return user;
		}
		else {
			//user and pass are not equal
			return null;
		}
	}
	
}
