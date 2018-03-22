package com.revature.warlockzone.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.revature.warlockzone.beans.Post;

public interface PostDAO extends JpaRepository<Post, Integer>{
	
	public Post findBypostId(int id);
	
}
