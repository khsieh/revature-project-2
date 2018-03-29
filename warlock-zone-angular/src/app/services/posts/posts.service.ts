import { Injectable, OnInit } from '@angular/core';
import { Http } from '@angular/http';
import 'rxjs/add/operator/map'
import { HttpClient, HttpParams, HttpHeaders } from '@angular/common/http';
import { Post } from '../../models/post';
import { AuthTokenService } from '../cache/authToken/auth-token.service';

@Injectable()
export class PostsService implements OnInit {

    private url:string = "http://ec2-13-58-228-189.us-east-2.compute.amazonaws.com/";

    constructor(
        private httpClient: HttpClient,
        private authService: AuthTokenService
    ) { }

    ngOnInit() {
    }

    getPosts() {
        let tUrl:string = this.url + "RecentPosts";
        tUrl = this.authService.appendToken(tUrl);

        let resp = this.httpClient.post(tUrl, {}, { responseType: 'text', observe: 'response' });
        console.log("getPosts() resp: " + JSON.stringify(resp));
        return resp;
    }

    addPost(post: Post) {
        let tUrl:string = this.url + "post";
        tUrl = this.authService.appendToken(tUrl);

        const requestBody = {
            "message": post.$message,
            "likes": post.$likes,
            "image": post.$image,
            "user": post.$user,
            // "profilePicture":newUser.$profilePicture
        }
        const header = {
            headers: new HttpHeaders({
                ContentType: 'application/json',
                responseType: 'text',
                observe: 'response'
            })
        }
        console.log(JSON.parse(JSON.stringify(post)));
        let resp = this.httpClient.post(tUrl, JSON.parse(JSON.stringify(post)), header);
        return resp;
    }

    likedPost(post) {
        let tUrl = this.url + "post";
        tUrl = this.authService.appendToken(tUrl);

        const header = {
            headers: new HttpHeaders({
                ContentType: 'application/json',
                responseType: 'text'
            })
        }
        return this.httpClient.put(tUrl, JSON.parse(JSON.stringify(post)), header);
    }

}
