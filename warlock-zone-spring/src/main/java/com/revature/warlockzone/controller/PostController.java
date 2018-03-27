package com.revature.warlockzone.controller;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.revature.warlockzone.beans.Post;
import com.revature.warlockzone.dao.PostDAO;
import com.revature.warlockzone.services.PostService;

@RestController
//allows communication from other ip's
@CrossOrigin
public class PostController {
	private static Logger log = Logger.getLogger(PostController.class.getName());
	@Autowired
	private PostService postService;
	
	@RequestMapping(method = RequestMethod.POST, value = "/post")
	public void addPost(@RequestBody Post post) {
		log.info("addPost: " + post.toString());
		postService.addPost(post);
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/post/RecentPosts")
	public List<Post> addAndRetrieve(@RequestBody Post post) {
		log.info("addAndRetrieve: " + post.toString());
		postService.addPost(post);
		return postService.getLastTenPosts();
	}
	
	@RequestMapping("/post")
	public List<Post> getPosts(){
		log.info("getPosts");
		return postService.getAllPosts();
	}
	
	
	@RequestMapping("/post/{id}")
	public Post getPost(@PathVariable int id){
		log.info("getPost: "+ id);		
		return postService.getPostById(id);
	}
	
	@RequestMapping("/RecentPosts")
	public List<Post> getRecentPosts(){
		log.info("getRecentPosts");
		return postService.getLastTenPosts();
	}
	
	@RequestMapping("/RecentPosts/{id}")
	public List<Post> getRecentPosts(@PathVariable int id){
		log.info("getRecentPosts: "+ id);
		return postService.getNextTenPosts(id);
	}
	
	@RequestMapping(method = RequestMethod.PUT, value = "/post")
	public void updatePost(@RequestBody Post post) {
		log.info("updatePost");
		postService.updatePost(post);
	}
	
	@RequestMapping(method = RequestMethod.DELETE, value = "/post/{id}")
	public void deletePost(@PathVariable int id) {
		log.warn("deletePost: " + id);
		postService.deletePost(id);
	}
	
}
