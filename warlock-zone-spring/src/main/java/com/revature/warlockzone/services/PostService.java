package com.revature.warlockzone.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.warlockzone.beans.Post;
import com.revature.warlockzone.dao.PostDAO;

@Service
public class PostService {

	@Autowired
	private PostDAO postDao;
	
	public List<Post> getAllPosts(){
		return postDao.findAll();
	}
	
	public Post getPostById(int id) {
		return postDao.findBypostId(id);
	}
	
	public void addPost(Post post) {
		postDao.save(post);
	}
	
	public void updatePost(Post post) {
		postDao.save(post);
	}
	
	public void deletePost(int id) {
		postDao.deleteById(id);
	}
	
}
