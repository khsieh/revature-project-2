package com.revature.warlockzone.services;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.revature.warlockzone.beans.User;

@Service
public class LoginService {

	@Autowired
	UserService userService;
	@Autowired
	User user;
	BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

	
	public User authenticate(String username, String password) {
		if (username == null || password == null) {
			return null;
		}
		user = userService.getUserByUsername(username);
		
		//	user.setPassword(hashedPassword);
		if(user == null) {
			return null;
		}
		//match input user from encrypted database
		else if(passwordEncoder.matches(password, user.getPassword())) {
		//	user.setPassword(null);
			return user;
		}
		else {
			//user and pass are not equal
			return null;
		}
	}
	
}
