package com.revature.warlockzone;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.revature.warlockzone.beans.Post;
import com.revature.warlockzone.beans.User;
import com.revature.warlockzone.services.S3Service;

public class S3ServiceTests {
    
    User testUser;
    @Test
    public void contextLoads() {
    }

    public void uploadImage(User testUser, Post testPost) {
    	S3Service.createFolder("testUser", testUser.getUserID());
        testUser.setProfilePicture(S3Service.uploadImage("testUser", testUser.getUserID(), testUser.getProfilePicture()));
        S3Service.createFolder("testPost", testPost.getPostId());
        testPost.setImage(S3Service.uploadImage("testPost", testPost.getPostId(),testPost.getImage()));
    }
    public void deleteImage(User testUser, Post testPost) {
    	S3Service.deleteImage("testUser", testUser.getUserID());
        testUser.setProfilePicture("");
        S3Service.deleteFolder("testUser");
        S3Service.deleteImage("testPost", testPost.getPostId());
        testPost.setImage("");
        S3Service.deleteFolder("testPost");
    }
}
