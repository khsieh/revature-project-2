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
	UserService userService;
	@Autowired
	private PostDAO postDao;
	@Autowired
	private Post post;
	
	
	public List<Post> getAllPosts(){
		return postDao.findAll();
	}
	
	public long getNumberOfPosts() {
		return postDao.count();
	}
	
	public Post getPostById(int id) {
		post = postDao.findBypostId(id);
		return post;
	}
	
	public void addPost(Post post) {
		postDao.save(post);
	}
	
	public void updatePost(Post post) {
		String image = post.getImage();
		if(image!=null && !image.isEmpty()) {
			if(image.length() > S3Service.baseUrl.length()) {
				if(!image.substring(0, S3Service.baseUrl.length()).equals(S3Service.baseUrl)){
					User user = userService.getUser(post.getUser().getUserID());
					post.setImage(S3Service.uploadImage(user, post));
				}
			}
		}
		postDao.save(post);
	}
	
	public void deletePost(int id) {
		postDao.deleteById(id);
	}
	
	public List<Post> getLastTenPosts(){
		return postDao.findFirst10ByOrderByPostIdDesc();
	}
	
	public List<Post> getNextTenPosts(int id){
		return postDao.findFirst10ByPostIdLessThanOrderByPostIdDesc(id);
	}
	
	public List<Post> getPostsByUser(User user){
		return postDao.findAllByUser(userService.getUser(user.getUserID()));	
	}
	
}
