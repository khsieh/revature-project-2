package com.revature.warlockzone;

import static org.junit.Assert.assertTrue;

import java.util.List;
import java.util.UUID;
import java.sql.Timestamp;

import org.apache.tomcat.jni.Time;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.revature.warlockzone.beans.Post;
import com.revature.warlockzone.beans.User;
import com.revature.warlockzone.services.LoginService;
import com.revature.warlockzone.services.PostService;
import com.revature.warlockzone.services.S3Service;
import com.revature.warlockzone.services.UserService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class WarlockZoneApplicationTests {
	@Autowired
	LoginService loginService;
	@Autowired
	public UserService userService;
	
	@Autowired
	public PostService postService;
	
	@Test
	public void main() {
		User testUser= new User();
		testUser.setUsername("testUserWarlock");
		testUser.setFirstName("Test");
		testUser.setLastName("Warlock");
		String password = "testUser";
		testUser.setPassword("testUser");
		testUser.setProfilePicture("data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAVoAAAERAQMAAAAAAP2aAAAABlBMVEX///8AAABVwtN+AAAACXBIWXMAAA7EAAAOxAGVKw4bAAAFVklEQVR4Ae1ZQa4kNQxNT48YFkgfIRYsRhDEBXo5CyRylD7CLNlNjtZH+UeAHQtEk2c7jp1KJd2CvytrqmI/PzuOk6r/f00IhxwdODpwdODowNGBowNHB44OHB0I5/v9/s+jffi1EE+vj7HPN/A+PkZG4hBKyPdrPicO4VP4bk3mxCF8yL8syTVxCJ9/ziv2N0p4SUvyRckhpaaPtVuDc2z6UDs39BS+bcZQe9/QU/i6GUPth4aegolssNGuRv/jK2OM1GzAF7MAA6vq3X8qPlTalsD9koekCpotAZQrPhzTEB2DpzzGh+iqVy5otWWOvDoMjhydtTDSwu/c2Vlz4zR3e+87b86t1fl10UebTTuONptmtLfEl/vfFh/pepo/ZPxgm4uSPxVe/Zm1F1L35HQrjHPeozFeyT+S+fucXJ+TK9EWLychy/tu8dqTh6qm5An2ipF3V30Q568y8V4kmf8h0M8QGbgJXpsjZjdEsnVr5itMRK7rC+FK9s4tEd5+xE5XmIkc6Y7bdIWZaInuuM1WKCvLStalKtIUTmQZLa6xRGOynTttOArwOapHD3BU30ZhWj0ZcE96xzRLmPSOydHMaOs3MFTOmQxqO2NgqEzOFnWGdfDafbLkCNaIMLjM3+4ZxqR3EW7qX3kd8YvAtgbeJhEqkT/il2dYtumwmySo5L4WBdfkN8YENyam54meAbv38DZJUEGmB4tu+7uSQY7lolKohw+QL4gKt3L5rgOtkqGkct3KFQKFZFIHN3KUm6SjYggbcJlTvFIodSINiICIjJv0i2ISPAMhMhiybWTHARGQktFqSC5XLNdIaFqUEMWby1gDBdJByUkgjFKSIG1QchYslnGPTE0oTqodfJQgnYHpZENG1hWZykEaENUAYKRmVv+MTGspderMiNJIkxXqkKyr3SFTEHwgPkwOeUGOdoefIqcQkH0kVGts54gfsTyiSjccuRjTzMlmfoqMk1SiR0I1F1/5J/J2ZMyV6jR+rGVkhYFEtZzyH8k4rA9nfpqM9g1kWzMek4NcW/VANyqV3zCL1r0ROba80zJwbPwhmyzwjcmuU5My4PIn8ikyVuGmQjqWbeYJmV7y9LjU8KfIiNwpY5v5/yKHXGZFmSooIarllVzMp8jUv5pjldmRY4nCNZJUQGpJdcaipGp0I/CnyNSSmgTRuEYSASbcRFIZs+j9EAEk3ERyGXGNJAKkG5QiWS7ovUQA5uBgsW7B8Fch3lNkc6DflSy7mYn3FPl9LWr+ex2fOMwtgjhjVphHSmqKREW7ZHZkzYDGmKoUJ4XJScFYtF0yVwAKSyqDaY6gdchQdFfoz7EFWd1UlYbWhDomaFolKQuyNoumiIgfSgSqjaakBAHeSCQkC05WEmM7cIFXcdwwJtyGwo2QDzhnouQhESCT6Y9X+XqjC9jGcNeEQDUtyfJX6RXJtI/bzOK6wMPfstZk+l7Clet2bjNLheWzAv5XDjIhh0wMECmg9ofR/p4YKF8gyncIiB5BNt09ivXl/soa76mg3bDxxY5gzc2syXo7fbP43BGs2e/BZLfNwZcMfbBN3J4SQTdlOXZ21rTNmwdj00qXKzordKZ3ttcR46lze7Pbley9neWXP21z/xhN29zvip+nK6KY2ULdCqyL9KtF5m0OQV5HHOIibRbR5XVEFr8NBqQK2W7ZwOr3462Zq/XJtzQOuLS4Hc18vr3tUBrcds2W3/xOay1Yr8983HctdwmboZxrw3a1OnurZ5daDt4rO/n/diZEcglLYhZsTv2yYFU3pf5crcWI1PxFeUEk90/3+1+P8A7O0YGjA3sd+BdDd3c97E/QawAAAABJRU5ErkJggg==");
		testUser.setEmail("testUser@warlock-zone.com");
		

		Post testPost = new Post();
		testPost.setUser(testUser);
		testPost.setMessage("automagically");
		testPost.setImage("data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAVoAAAERAQMAAAAAAP2aAAAABlBMVEX///8AAABVwtN+AAAACXBIWXMAAA7EAAAOxAGVKw4bAAAFVklEQVR4Ae1ZQa4kNQxNT48YFkgfIRYsRhDEBXo5CyRylD7CLNlNjtZH+UeAHQtEk2c7jp1KJd2CvytrqmI/PzuOk6r/f00IhxwdODpwdODowNGBowNHB44OHB0I5/v9/s+jffi1EE+vj7HPN/A+PkZG4hBKyPdrPicO4VP4bk3mxCF8yL8syTVxCJ9/ziv2N0p4SUvyRckhpaaPtVuDc2z6UDs39BS+bcZQe9/QU/i6GUPth4aegolssNGuRv/jK2OM1GzAF7MAA6vq3X8qPlTalsD9koekCpotAZQrPhzTEB2DpzzGh+iqVy5otWWOvDoMjhydtTDSwu/c2Vlz4zR3e+87b86t1fl10UebTTuONptmtLfEl/vfFh/pepo/ZPxgm4uSPxVe/Zm1F1L35HQrjHPeozFeyT+S+fucXJ+TK9EWLychy/tu8dqTh6qm5An2ipF3V30Q568y8V4kmf8h0M8QGbgJXpsjZjdEsnVr5itMRK7rC+FK9s4tEd5+xE5XmIkc6Y7bdIWZaInuuM1WKCvLStalKtIUTmQZLa6xRGOynTttOArwOapHD3BU30ZhWj0ZcE96xzRLmPSOydHMaOs3MFTOmQxqO2NgqEzOFnWGdfDafbLkCNaIMLjM3+4ZxqR3EW7qX3kd8YvAtgbeJhEqkT/il2dYtumwmySo5L4WBdfkN8YENyam54meAbv38DZJUEGmB4tu+7uSQY7lolKohw+QL4gKt3L5rgOtkqGkct3KFQKFZFIHN3KUm6SjYggbcJlTvFIodSINiICIjJv0i2ISPAMhMhiybWTHARGQktFqSC5XLNdIaFqUEMWby1gDBdJByUkgjFKSIG1QchYslnGPTE0oTqodfJQgnYHpZENG1hWZykEaENUAYKRmVv+MTGspderMiNJIkxXqkKyr3SFTEHwgPkwOeUGOdoefIqcQkH0kVGts54gfsTyiSjccuRjTzMlmfoqMk1SiR0I1F1/5J/J2ZMyV6jR+rGVkhYFEtZzyH8k4rA9nfpqM9g1kWzMek4NcW/VANyqV3zCL1r0ROba80zJwbPwhmyzwjcmuU5My4PIn8ikyVuGmQjqWbeYJmV7y9LjU8KfIiNwpY5v5/yKHXGZFmSooIarllVzMp8jUv5pjldmRY4nCNZJUQGpJdcaipGp0I/CnyNSSmgTRuEYSASbcRFIZs+j9EAEk3ERyGXGNJAKkG5QiWS7ovUQA5uBgsW7B8Fch3lNkc6DflSy7mYn3FPl9LWr+ex2fOMwtgjhjVphHSmqKREW7ZHZkzYDGmKoUJ4XJScFYtF0yVwAKSyqDaY6gdchQdFfoz7EFWd1UlYbWhDomaFolKQuyNoumiIgfSgSqjaakBAHeSCQkC05WEmM7cIFXcdwwJtyGwo2QDzhnouQhESCT6Y9X+XqjC9jGcNeEQDUtyfJX6RXJtI/bzOK6wMPfstZk+l7Clet2bjNLheWzAv5XDjIhh0wMECmg9ofR/p4YKF8gyncIiB5BNt09ivXl/soa76mg3bDxxY5gzc2syXo7fbP43BGs2e/BZLfNwZcMfbBN3J4SQTdlOXZ21rTNmwdj00qXKzordKZ3ttcR46lze7Pbley9neWXP21z/xhN29zvip+nK6KY2ULdCqyL9KtF5m0OQV5HHOIibRbR5XVEFr8NBqQK2W7ZwOr3462Zq/XJtzQOuLS4Hc18vr3tUBrcds2W3/xOay1Yr8983HctdwmboZxrw3a1OnurZ5daDt4rO/n/diZEcglLYhZsTv2yYFU3pf5crcWI1PxFeUEk90/3+1+P8A7O0YGjA3sd+BdDd3c97E/QawAAAABJRU5ErkJggg==");
		testPost.setTimeStamp(new Timestamp(System.currentTimeMillis()));
		
		uploadImage(testUser, testPost);
		testAddUser(testUser);
		testLoginUser(testUser, password);
		testAddPost(testPost);
		testUpdateUser(testUser);
		testUpdatePost(testPost);
		deleteImage(testUser, testPost);
		testDeletePost(testPost);
		testDeleteUser(testUser);
	}
	private void testAddUser(User testUser){
		System.out.println("Adding test user to DB");
        userService.addUser(testUser);
        User testUser2 = userService.getUserByUsername(testUser.getUsername());
        System.out.println("Get user by username");
        System.out.println((testUser2 == null) ? "test user not found in DB" : testUser2.toString());
        compareUser(testUser, testUser2);
        testUser.setUserID(testUser2.getUserID());
        System.out.println("Added test user to DB");
    }

    private void testUpdateUser(User testUser) {
    	System.out.println("Updating test user");
    	String username = testUser.getUsername();
    	testUser.setUsername("testUserWarlock2");
    	userService.updateUser(testUser);
    	User testUser2 = userService.getUserByUsername(username);
        assertTrue(testUser2==null);
        System.out.println(testUser.toString());
        System.out.println("updated test user");
    }

    private void testDeleteUser(User testUser) {
    	System.out.println("Deleting test user");
    	int id = testUser.getUserID();
        userService.deleteUserById(id);
        User testUser2 = userService.getUser(id);
        assertTrue(testUser2 == null);
        System.out.println("Deleted test user");
    }
    
    private boolean compareUser(User testUser, User testUser2) {
    	assertTrue(testUser!=null);
    	assertTrue(testUser2!=null);
    	assertTrue(testUser.getUsername().equals(testUser2.getUsername()));
        assertTrue(testUser.getFirstName().equals(testUser2.getFirstName()));
        assertTrue(testUser.getLastName().equals(testUser2.getLastName()));
        assertTrue(testUser.getEmail().equals(testUser2.getEmail()));
        assertTrue(testUser.getProfilePicture().equals(testUser2.getProfilePicture()));
        return true;
    }
    public void testAddPost(Post testPost) {
		System.out.println("Adding test post to DB");
	    postService.addPost(testPost);
	    List<Post> testPosts = postService.getAllPosts();
	    Post testPost2 = null;
	    for (Post post: testPosts) {
	    	if(userService.getUser(post.getUser().getUserID()).getUsername().equals(testPost.getUser().getUsername())){
	    		testPost2= post;
	    	}
	    }
	    System.out.println("get posts");
	    System.out.println((testPost2==null)?"Test post not found in db": testPost2.toString());
	    comparePost(testPost, testPost2);
	    testPost.setPostId(testPost.getPostId());
	    System.out.println("Added test post to DB");
    }

    public void testUpdatePost(Post testPost) {
    	System.out.println("Updating test post");
    	String message2="automagically again";
    	testPost.setMessage(message2);
    	postService.updatePost(testPost);
    	Post testPost2 = postService.getPostById(testPost.getPostId());
        assertTrue(testPost2.getMessage().equals(message2));
        System.out.println(testPost.toString());
        System.out.println("Updated test Post");
    }

    public void testDeletePost(Post testPost) {
    	System.out.println("Deleting test post");
    	int id = testPost.getPostId();
        postService.deletePost(testPost.getPostId());
        Post testPost2 = postService.getPostById(id);
        assertTrue(testPost2 == null);
        System.out.println(testPost.toString());
        System.out.println("Deleted test Post");
    }
    private void comparePost(Post testPost, Post testPost2) {
    	assertTrue(testPost  !=  null);
    	assertTrue(testPost2  !=  null);
    	assertTrue(testPost.getMessage().equals(testPost2.getMessage()));
    	assertTrue(testPost.getImage().equals(testPost2.getImage()));
    	assertTrue(testPost.getTimeStamp().equals(testPost2.getTimeStamp()));
    	testPost.setPostId(testPost2.getPostId());
    }
    public void uploadImage(User testUser, Post testPost) {
    	System.out.println("Uploading test post and test user images\n");
		System.out.println(testUser.toString());
		System.out.println(testPost.toString());
    	S3Service.createFolder("testUser", testUser.getUserID());
        testUser.setProfilePicture(S3Service.uploadImage(testUser, null));
        S3Service.createFolder("testPost", testPost.getPostId());
        testPost.setImage(S3Service.uploadImage(testUser, testPost));
		System.out.println(testUser.toString());
		System.out.println(testPost.toString());
		System.out.println("Images uploaded\n");
		
        
    }
    public void deleteImage(User testUser, Post testPost) {
    	S3Service.deleteImage("testUser", testUser.getUserID());
        testUser.setProfilePicture("");
        S3Service.deleteFolder("testUser");
        S3Service.deleteImage("testPost", testPost.getPostId());
        testPost.setImage("");
        S3Service.deleteFolder("testPost");
    }
    public void testLoginUser(User testUser, String password) {
    	System.out.println("Logging in test user");
    	System.out.println(testUser.toString());
    	User testUser2 = loginService.authenticate(testUser.getUsername(), password);
    	System.out.println((testUser2==null)? "User not authenticated" : testUser2.toString());
	    compareUser(testUser, testUser2);
	    testUser.setToken(testUser2.getToken());
	    System.out.println("Test user Logged in");
    }
}

