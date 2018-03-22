package com.revature.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import com.revature.beans.User;

public interface UserDAO extends JpaRepository<User, Integer>{
	
	public User findByUsername(String username);
	
}
