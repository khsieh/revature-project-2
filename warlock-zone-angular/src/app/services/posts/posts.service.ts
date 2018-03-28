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

    return this.httpClient.post('http://13.58.228.189/RecentPosts',{},{responseType: 'text', observe: 'response'});

  }

  addPost(post: Post){
    let url = 'http://ec2-13-58-228-189.us-east-2.compute.amazonaws.com/post/RecentPosts';
    console.log("Post JSON.stringify(): "+JSON.stringify(post.getJSON()));
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
    console.log(requestBody);
    let resp = this.httpClient.post(url,requestBody,header);

    console.log('resp: '+JSON.stringify(resp));
    return resp;
  }

  likedPost(post){
    let url = 'http://13.58.228.189/post';
    let resp = this.httpClient.put(url,post.getJSON());
    console.log(resp);
  }

}
