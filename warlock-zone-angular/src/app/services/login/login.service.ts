import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';

@Injectable()
export class LoginService {

  private url:string  = "http://ec2-13-58-228-189.us-east-2.compute.amazonaws.com/login";

  constructor(private httpClient:HttpClient) { }

  validate(username:string, password:string){
    return this.httpClient.post(this.url, {username, password});
  }
}
