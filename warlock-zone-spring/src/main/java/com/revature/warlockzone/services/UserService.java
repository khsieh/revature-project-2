package com.revature.warlockzone.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.revature.warlockzone.beans.User;
import com.revature.warlockzone.dao.UserDAO;

@Service
public class UserService {

	@Autowired
	UserDAO userDao;
	@Autowired
	User user;
	
public User authenticate(String username, String password) {
		
		user = getUserByUsername(username);
		
		if(user.getPassword().equals(password)) {
			return user;
		}
		else {
			//user and pass are not equal
			return null;
		}
	}
	
	BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
	public List<User> getAllUsers(){
		//probably need to change this
		return userDao.findAll();	
	}
	
	public User getUser(int id) {
		Optional search = userDao.findById(id);
		return (search.isPresent()) ? (User) search.get() : null;
	}
	
	public User getUserByUsername(String username){
		//probably need to change this
		user = userDao.findByUsername(username);
		return user;
	}
	
	public void addUser(User user) {
		String hashedPassword = passwordEncoder.encode(user.getPassword());
		user.setPassword(hashedPassword);

		userDao.save(user);
	}
	
	public void updateUser(User user) {
		String hashedPassword = passwordEncoder.encode(user.getPassword());
		user.setPassword(hashedPassword);
		userDao.save(user);
	}
	
	public void deleteUserById(int id) {
		userDao.deleteById(id);
	}
	public void updatePassword(User user) {
		userDao.save(user);
	}
	
	public void updateToken(User user) {
		userDao.save(user);
	}
	public void deleteToken(int id) {
		userDao.save(user);
	}
	
	
}