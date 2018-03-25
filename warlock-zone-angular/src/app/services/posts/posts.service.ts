import { Injectable } from '@angular/core';
import { Http } from '@angular/http';
import 'rxjs/add/operator/map'


@Injectable()
export class PostsService {

  constructor(public http:Http) {
    console.log("Fetching Posts");

  }
    
  getPosts(){
    var returned = this.http.get('https://jsonplaceholder.typicode.com/posts').map(res => res.json());
    console.log("Returned: "+(returned));
    return returned;
    //TODO:
    //Will call servlets here to get posts
  }

}
