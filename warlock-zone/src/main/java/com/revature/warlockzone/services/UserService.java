package com.revature.warlockzone.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.warlockzone.beans.User;
import com.revature.warlockzone.dao.UserDAO;

@Service
public class UserService {

	@Autowired
	UserDAO userDao;
	
	public List<User> getAllUsers(){
		//probably need to change this
		return userDao.findAll();	
	}
	
	public void addUser(User user) {
		System.out.println(user.getFirstName() + " " + user.getLastName());
		userDao.save(user);
	}
	
}
