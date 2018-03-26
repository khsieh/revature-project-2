package com.revature.warlockzone.services;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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
