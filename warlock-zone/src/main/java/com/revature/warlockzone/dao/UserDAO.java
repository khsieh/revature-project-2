package com.revature.warlockzone.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.revature.warlockzone.beans.User;

public interface UserDAO extends JpaRepository<User, Integer>{
	
	public User findByUsername(String username);
	
	public User findByEmail(String email);
	
	
}
