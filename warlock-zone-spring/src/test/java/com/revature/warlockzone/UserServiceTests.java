package com.revature.warlockzone;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Service;
import org.springframework.test.context.junit4.SpringRunner;

import com.revature.warlockzone.beans.User;
import com.revature.warlockzone.services.PostService;
import com.revature.warlockzone.services.UserService;

@Service
public class UserServiceTests {
	    @Autowired
	    UserService userService ;
	    @Autowired
	    PostService postService;
	    
	    User testUser;
	    @Test
	    public void contextLoads() {
	    }

	    @Test
	    public void testAddUser(User testUser){
	    	System.out.println(userService==null);
	        userService.addUser(testUser);
	        User testUser2 = userService.getUserByUsername(testUser.getUsername());
	        compareUser(testUser, testUser2);
	        testUser.setUserID(testUser2.getUserID());
	    }

	    @Test
	    public void testUpdateUser(User testUser) {
	    	String username = testUser.getUsername();
	    	testUser.setUsername("testUser2");
	    	userService.updateUser(testUser);
	    	User testUser2 = userService.getUserByUsername(username);
	        compareUser(testUser, testUser2);
	    }

	    @Test
	    public void testDeleteUser(User testUser) {
	        userService.deleteUserById(testUser.getUserID());
	        userService.getUser(testUser.getUserID());
	        assertTrue(testUser == null);
	    }
	    
	    private void compareUser(User testUser, User testUser2) {
	    	assertTrue(testUser!=null);
	    	assertTrue(testUser2!=null);
	    	assertTrue(testUser.getUsername()==testUser2.getUsername());
	        assertTrue(testUser.getFirstName()==testUser2.getFirstName());
	        assertTrue(testUser.getLastName()==testUser2.getLastName());
	        assertTrue(testUser.getEmail()==testUser2.getEmail());
	        assertTrue(testUser.getProfilePicture()==testUser2.getProfilePicture());
	    }
}
