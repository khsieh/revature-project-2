package com.revature.warlockzone.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.revature.warlockzone.beans.Post;

public interface PostDAO extends JpaRepository<Post, Integer>{
	
	public Post findBypostId(int id);
	
	public List<Post> findFirst10ByOrderByPostIdDesc();

	public List<Post> findFirst10ByPostIdLessThanOrderByPostIdDesc(int id);

	
}
