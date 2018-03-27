package com.revature.warlockzone.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.warlockzone.beans.Post;
import com.revature.warlockzone.beans.User;
import com.revature.warlockzone.dao.PostDAO;

@Service
public class PostService {

	@Autowired
	private PostDAO postDao;
	@Autowired
	private Post post;
	
	
	public List<Post> getAllPosts(){
		return securePosts(postDao.findAll());
	}
	
	public Post getPostById(int id) {
		post = postDao.findBypostId(id);
		post.getUser().setPassword("null");
		return post;
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
	
	public List<Post> getLastTenPosts(){
		return securePosts(postDao.findFirst10ByOrderByPostIdDesc());
	}
	
	public List<Post> getNextTenPosts(int id){
		return securePosts(postDao.findFirst10ByPostIdLessThanOrderByPostIdDesc(id));
	}
	
	private List<Post> securePosts(List<Post> posts) {
		
		for(int i = 0; i < posts.size(); i++) {
			posts.get(i).getUser().setPassword("null");
		}
		
		return posts;
	}
}
