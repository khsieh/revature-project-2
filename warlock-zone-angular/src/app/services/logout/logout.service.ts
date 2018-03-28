import { Injectable } from '@angular/core';
import { AuthTokenService } from '../cache/authToken/auth-token.service';
import { Router } from '@angular/router';

@Injectable()
export class LogoutService {

    constructor(private router:Router, private authService:AuthTokenService) { }

    bye(){
        console.log("Bye!!!");
        this.authService.setToken("false");
        this.router.navigate(['login']);
    }

}
