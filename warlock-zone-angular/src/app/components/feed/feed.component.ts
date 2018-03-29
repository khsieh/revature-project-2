import { Component, OnInit } from '@angular/core';
import { Post } from '../../models/post';
import { PostsService } from '../../services/posts/posts.service';
import { User } from '../../models/user';
import { ToggleNewPostService } from '../../services/util/toggle-new-post.service';
import { CurUserService } from "../../services/cache/curUser/cur-user.service";


@Component({
  selector: 'app-feed',
  templateUrl: './feed.component.html',
  styleUrls: ['./feed.component.css']
})
export class FeedComponent implements OnInit {

  postList:Post[] = [];
  maxposts: number;
  currentUser: User;
  showCommentEntry: Boolean;

  unsubmittedContent: string;

  

  constructor(private posts:PostsService,private toggleService:ToggleNewPostService, private curUser: CurUserService) {} //, private userComp:ProfileComponent) { }

  ngOnInit() {
    this.maxposts = 10;
    // this.showCommentEntry = showCommentary;
    this.unsubmittedContent = "";
    this.currentUser;

    // Call service to get Posts from DB
    this.getPostsFromService();

    this.toggleService.curStateAsObserable.subscribe(
        resp=>{
            this.showCommentEntry = resp;
            // console.log(resp);
        },
        err=>{
            console.log(err);
        }
    );

    this.getCurrentUser();
  }

  getCurrentUser(){
    this.curUser.getUser().subscribe( resp =>{this.currentUser = resp}, err=>{console.log("Error: Cur-User Service failed to send back a User object")});
    // console.log("Current User: "+JSON.stringify(this.currentUser));
  }

  //hide/unhide hidden comment section
  unhidePost(){
    this.showCommentEntry = !this.showCommentEntry;
  }

  searchUsers(username: String){
    //take in string and ping DB to see if it exists

    //ping DB again with user to get posts
    //display posts
  }

  createPost(){
      //stop myself from doing a null user
      if(this.currentUser.$username == "" || this.currentUser.$username == null){
        alert("STOP EVERYTHING: You don't have a username and seth doesn't like it when you make a post without a user name.")
        this.showCommentEntry = false;
        return;
      }

      let newPost = new Post();
      newPost.$user = this.currentUser; //gets currentUser from CurUserService
      newPost.$message = this.unsubmittedContent;
      newPost.$likes = [];
      newPost.$image = null; //get postID from DB

      if(this.unsubmittedContent == ""){
        alert("Please enter a message before submitting.")
        return;
      }
 
      this.unsubmittedContent = ""; //reset unsubmttedConent to nothing
      this.showCommentEntry = false;
      
      //adds post through post/RecentPosts, waits to get a resp, then calls get /post for 1 most recent
      this.posts.addPost(newPost).subscribe(resp=>{this.postsFromSerice();},err=>{console.log("Error recieving posts from createPost")});
      
  }
  
    getPostsFromService(){
      this.postsFromSerice();
      // this.testPosts();
    }

    postsFromSerice(){
      this.populatePostList(this.posts.getPosts());
    }

    populatePostList(obs){

      obs.subscribe(
        resp=>{
          let list;
          try{
            list = JSON.parse(resp.body);
          } catch (e){
            //try catch to test if resp is a JSON or not
            //will throw error when getting back a JSON from addPost
            //catches it and sets list equal to list of JSON
            list = resp;
          }
          if(list == null){
            console.log("Error: Feed Componenet: populatePostList response body error");
          }

          this.postList = [];

          for (var index = 0; index<list.length;index++){
            console.log(list[index]);
            let newPost = new Post();
            newPost.$user = list[index].user;
            newPost.$message = list[index].message;
            newPost.$postID = list[index].postId;
            newPost.$likes = list[index].likes;
            // newPost.$likes = list[index].likes.length;
            
            // newPost.$image = list[index].image;
            // newPost.$time = list[index].time;
            
            this.postList.push(newPost);
          }
        },
        err=>{
          console.error;
          console.log("error: feed componenet: response error");
        }
      );
    }

    likePost(post){
    
      for(var index = 0; index < post.$likes.length; index++){
        if(post.$likes[0].userID == this.currentUser.$userID){
          //play an animation here?
          console.log("User has already liked this post");
          alert("Already liked this post");
          return;
        } 
      }
      //if no userID is found in post.$likes
      post.$likes.push(JSON.parse(JSON.stringify(this.currentUser)));
      this.posts.likedPost(post).subscribe(); //update post
    }
}

