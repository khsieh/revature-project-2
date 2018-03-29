package com.revature.warlockzone;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Service;
import org.springframework.test.context.junit4.SpringRunner;

import com.revature.warlockzone.beans.Post;
import com.revature.warlockzone.services.PostService;
import com.revature.warlockzone.services.UserService;

@Service
public class PostServiceTests {
	@Autowired
    UserService userService;
    @Autowired
    PostService postService;
    
    @Test
    public void contextLoads() {
    }

    @Test
    public void testAddPost(Post testPost) {
	    postService.addPost(testPost);
	    List<Post> testPosts = postService.getAllPosts();
	    Post testPost2 = (testPosts.size()>0) ? testPosts.get(0) : null;
	    comparePost(testPost, testPost2);
	    testPost.setPostId(testPost.getPostId());
    }

    @Test
    public void testUpdatePost(Post testPost) {
    	String message2="automagically again";
    	testPost.setMessage(message2);
    	postService.updatePost(testPost);
    	Post testPost2 = postService.getPostById(testPost.getPostId());
        assertTrue(testPost2.getMessage().equals(message2));
    }

    @Test
    public void testDeletePost(Post testPost) {
        postService.deletePost(testPost.getPostId());
        Post testPost2 = postService.getPostById(testPost.getPostId());
        assertTrue(testPost2 == null);
    }
    private void comparePost(Post testPost, Post testPost2) {
    	assertTrue(testPost  !=  null);
    	assertTrue(testPost2  !=  null);
    	assertTrue(testPost.getPostId()  ==  testPost2.getPostId());
    	assertTrue(testPost.getMessage()  ==  testPost2.getMessage());
    	assertTrue(testPost.getImage()  ==  testPost2.getImage());
    	assertTrue(testPost.getTimeStamp()  ==  testPost2.getTimeStamp());
    }
}

