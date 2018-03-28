import { Injectable } from '@angular/core';
import { AuthTokenService } from '../cache/authToken/auth-token.service';
import { Router } from '@angular/router';
import { HttpClient } from '@angular/common/http';
import { CurUserService } from '../cache/curUser/cur-user.service';
import { User } from '../../models/user';

@Injectable()
export class LogoutService {

    private url:string = "http://ec2-13-58-228-189.us-east-2.compute.amazonaws.com/logout";

    constructor(
        private httpClient:HttpClient,
        private router:Router, 
        private authService:AuthTokenService,
        private curUser:CurUserService
    ) { }

    bye(){
        console.log("Bye!!!");
        let tUrl = this.authService.appendToken(this.url);
        // let tUrl = this.url + "?token=" + this.authService.getToken();
        console.log(tUrl.toString());
        this.httpClient.post(tUrl,{});
        this.curUser.setUser(new User());
        this.authService.setToken(null);//reset token to nothing
        this.router.navigate(['login']);
    }

}
