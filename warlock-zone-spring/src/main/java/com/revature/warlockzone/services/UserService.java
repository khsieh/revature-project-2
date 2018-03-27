package com.revature.warlockzone.services;

import java.util.List;
import java.util.Optional;

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
	
	public User getUser(int id) {
		Optional search = userDao.findById(id);
		return (search.isPresent()) ? (User) search.get() : null;
	}
	
	public User getUserByUsername(String username){
		//probably need to change this
		return userDao.findByUsername(username);
	}
	
	public void addUser(User user) {
		userDao.save(user);
	}
	
	public void updateUser(User user) {
		userDao.save(user);
	}
	
	public void deleteUserById(int id) {
		userDao.deleteById(id);
	}
	public void updatePassword(User user) {
		userDao.save(user);
	}
	
	
}
