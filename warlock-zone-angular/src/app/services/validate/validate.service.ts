import { Injectable } from '@angular/core';
import { HttpClient, HttpParams, HttpHeaders } from '@angular/common/http';
import { CurUserService } from '../cache/curUser/cur-user.service';
import { AuthTokenService } from '../cache/authToken/auth-token.service';

@Injectable()
export class ValidateService {

    private url:string = "http://ec2-13-58-228-189.us-east-2.compute.amazonaws.com/validateUser";

    constructor(
        private httpClient:HttpClient,
        private curUser:CurUserService,
        private authService:AuthTokenService
    ) { }

    check(pw:string){

        let tUrl = this.authService.appendToken(this.url);
        const requestBody = {
            "username":this.curUser.getStaticUser().$username,
            "password":pw
        }
        const header = {
            headers: new HttpHeaders({
                ContentType: 'application/json',
                responseType: 'text'
            })
        }
        this.httpClient.post(this.url,requestBody,header).subscribe(
            resp=>{
                return true;
            },
            err=>{
                return false;
            }
        );
    }

}
