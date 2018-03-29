package com.revature.warlockzone.controller;


import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.revature.warlockzone.beans.Post;
import com.revature.warlockzone.beans.User;
import com.revature.warlockzone.services.PostService;
import com.revature.warlockzone.services.UserService;

@RestController
//allows communication from other ip's
@CrossOrigin
public class PostController {
	private static Logger log = Logger.getLogger(PostController.class.getName());
	@Autowired
	private PostService postService;
	@Autowired
	private UserService userService;
	public boolean tokenCheck(String token, Post post) {
        User user = userService.getUser(post.getUser().getUserID());
        if(token.equals(user.getToken())){
            return true;
        }
		return false;
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/post")
	public void addPost(@RequestBody Post post,  @RequestParam("token") String tokens) {
		if(tokenCheck(tokens,post)) {
			log.info("addPost: " + post.toString());
			postService.addPost(post);
		}
		
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/post/RecentPosts")
	public List<Post> addAndRetrieve(@RequestBody Post post, @RequestParam("token") String tokens) {
		if(tokenCheck(tokens,post))
		{
			log.info("addAndRetrieve: " + post.toString());
			postService.addPost(post);
			return postService.getLastTenPosts();

		}
		return null;
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/postByUser")
	public List<Post> getUserPosts(@RequestBody User user,  @RequestParam("token") String tokens){
		if(tokenCheck(tokens,post)) {
			return postService.getPostsByUser(user);

		}
		return null;
	}
	
	@RequestMapping("/post")
	public List<Post> getPosts( @RequestParam("token") String tokens){
		// if(tokenCheck(tokens)) {
			log.info("getPosts");
			return postService.getAllPosts();
		// }
		// return null;
		
	}
	
	
	@RequestMapping("/post/{id}")
	public Post getPost(@PathVariable int id,  @RequestParam("token") String tokens){
		// if(tokenCheck(tokens,post)) {
			log.info("getPost: "+ id);		
			return postService.getPostById(id);
		// }
		// return null;
	}
	
	@RequestMapping("/RecentPosts")
	public List<Post> getRecentPosts( @RequestParam("token") String tokens){
		// if(tokenCheck(tokens,post)) {
			log.info("getRecentPosts");
			return postService.getLastTenPosts();
		// }
		// return null;
	}
	
	@RequestMapping("/RecentPosts/{id}")
	public List<Post> getRecentPosts(@PathVariable int id, @RequestParam("token") String tokens){
		// if(tokenCheck(tokens,post)) {
			log.info("getRecentPosts: "+ id);
			return postService.getNextTenPosts(id);
		// }
		// return null;
	}
	
	@GetMapping("/postCount")
	public long getPostCount() {
		return postService.getNumberOfPosts();
	}
	
	@RequestMapping(method = RequestMethod.PUT, value = "/post")
	public void updatePost(@RequestBody Post post,  @RequestParam("token") String tokens) {
		if(tokenCheck(tokens,post)) {
			log.info("updatePost");
			postService.updatePost(post);
		}
		
	}
	
	@RequestMapping(method = RequestMethod.DELETE, value = "/post/{id}" )
	public void deletePost(@PathVariable int id,  @RequestParam("token") String tokens) {
		if(tokenCheck(tokens,post)) {
			log.warn("deletePost: " + id);
			postService.deletePost(id);
		}
		
	}
	
}
