import { Injectable } from '@angular/core';
import { Http } from '@angular/http';
import 'rxjs/add/operator/map'
import { HttpClient, HttpParams, HttpHeaders } from '@angular/common/http';
import { Post } from '../../models/post';

@Injectable()
export class PostsService {

  constructor(public http:Http, public httpClient:HttpClient) {
    console.log("Fetching Posts");

  }
    
  getTestPosts(){    
    let jsonUrl:string = 'https://jsonplaceholder.typicode.com/posts';

    return this.http.get(jsonUrl).map(res => res.json())
    // console.log("Returned: "+(returned));
    // return returned;
    //TODO:
    //Will call servlets here to get posts
  }

  getPosts(){

     let resp = this.httpClient.post('http://13.58.228.189/RecentPosts',{},{responseType: 'text', observe: 'response'});
     console.log("getPosts() resp: " + JSON.stringify(resp));
     return resp;
  }

  addPost(post: Post){
    let url = 'http://ec2-13-58-228-189.us-east-2.compute.amazonaws.com/post/RecentPosts';
    // console.log("Post JSON.stringify(): "+JSON.stringify(post.getJSON()));
    // let resp = this.httpClient.post(url,post.getJSON());

    const requestBody = {
      "message":post.$message,
      "likes":post.$likes,
      "image":post.$image,
      "user":post.$user,
      // "email":newUser.$email,
      // "profilePicture":newUser.$profilePicture
  }
    const header = {
        headers:new HttpHeaders({
            ContentType:'application/json',
            responseType:'text',
            observe:'response'
        })
    }
    console.log(JSON.parse(JSON.stringify(post)));
    let resp = this.httpClient.post(url,JSON.parse(JSON.stringify(post)),header);
    // this.likedPost(post).subscribe(resp=>{},err=>{console.log("Error in addPost: error occured while adding post to DB")});
    // let resp = this.getPosts();
    // console.log('resp addPost(): '+JSON.stringify(resp));
    // let resp = this.getPosts();
    return resp;
  }

  likedPost(post){
    let url = 'http://13.58.228.189/post';
    // console.log(JSON.stringify(post));
      
    // const requestBody = {
      // JSON.parse(JSON.stringify(post));
    // }
    const header = {
        headers:new HttpHeaders({
            ContentType:'application/json',
            responseType:'text',
            // observe:'response'
        })
    }


    return this.httpClient.put(url,JSON.parse(JSON.stringify(post)), header);
    // let resp = this.httpClient.put(url,{"postId":6, "likes":[{},{}]});
    // console.log(resp);
  }

}
