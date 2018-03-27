import { Injectable } from '@angular/core';
import { Http } from '@angular/http';
import 'rxjs/add/operator/map'
import { HttpClient, HttpParams } from '@angular/common/http';
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
    let url = 'http://13.58.228.189/post/RecentPosts';
    console.log("Post JSON.stringify(): "+JSON.stringify(post.getJSON()));
    // let resp = this.httpClient.post(url,post.getJSON());
    let resp = this.httpClient.post(url,post.getJSON());

    console.log('resp: '+resp);
    return resp;
  }

  likedPost(post){
    let url = 'http://13.58.228.189/post';
    let resp = this.httpClient.put(url,post.getJSON());
    console.log(resp);
  }

}
