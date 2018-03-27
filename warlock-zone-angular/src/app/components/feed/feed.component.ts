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
    // this.currentUser = this.userComp.$user;
    this.currentUser = new User();
    this.currentUser.$username = "Cousin Seth";

    // Call service to get Posts from DB
    this.getPostsFromService();

    this.toggleService.curStateAsObserable.subscribe(
        resp=>{
            this.showCommentEntry = resp;
            console.log(resp);
        },
        err=>{
            console.log(err);
        }
    );
  }

  //hide/unhide hidden comment section
  unhidePost(){
    this.showCommentEntry = !this.showCommentEntry;
  }

  createPost(){

      let newPost = new Post();
      let user1 = new User();
      user1.$userID = 1;
      newPost.$user = user1;
      // newPost.$author = this.curUser; //gets currentUser from CurUserService
      newPost.$message = this.unsubmittedContent;
      newPost.$likes = 0;
      newPost.$image = null; //get postID from DB
      if(this.unsubmittedContent == ""){
        alert("Please enter a message before submitting.")
        return;
      }
      // this.postList.pop(); //removes last post (oldest post) from list
      // this.postList.unshift(newPost); //append to front of list
      this.unsubmittedContent = ""; //reset unsubmttedConent to nothing
      // this.showCommentEntry = false;
      //call addPost method, doPost sends request and returns resposne of 10 posts
      this.populatePostList(this.posts.addPost(newPost));
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
          let list = JSON.parse(resp.body);
          // console.log(list[0].user.username);
          for (var index = 0; index<list.length;index++){
            console.log(list[index]);
            let newPost = new Post();
            newPost.$user = list[index].user;
            newPost.$message = list[index].message;
            newPost.$postID = list[index].postId;
            if(list[index].likes == null ) {
              newPost.$likes = 0; 
            } else {
              newPost.$likes = list[index].likes; 
            }
            //Math.floor(Math.random() * 6) + 1;
            // newPost.$image = list[index].image;
            // newPost.$time = list[index].time;
            // console.log(newPost.$author.username);
            this.postList.push(newPost);
          }
          // console.log("Response: "+JSON.stringify(list[0]));
          // console.log("userID: "+list[0].user.userID);
        },
        err=>{
          console.error;
          console.log("error: feed componenet: response error");
        }
      );
    }

    testPosts(){
      this.posts.getTestPosts().subscribe(
        resp=>{ 
          let list = JSON.parse(resp.body);
          // console.log(list);
          //set maxposts to the smaller unit: maxposts or number of posts returned
          let localMaxPosts = (this.maxposts > list.length) ? list.length : this.maxposts;
          for(var index=0; index<localMaxPosts;index++){
            // console.log(index);
          }
        },
        err=>{
          // console.log("error")
        }
      )
    }

    likePost(post){
      //TODO: check whether (set) post.likedBy contains this.curUser.userID
        //if so return and do nothing
      
      post.$likes = post.$likes + 1;
      
    }
}

