import { Injectable } from '@angular/core';
import { AuthTokenService } from '../cache/authToken/auth-token.service';
import { User } from '../../models/user';
import { HttpClient, HttpParams, HttpHeaders} from '@angular/common/http';

@Injectable()
export class UpdateUserService {

    private url:string  = "http://ec2-13-58-228-189.us-east-2.compute.amazonaws.com/user";

    constructor(
        private authService:AuthTokenService,
        private httpClient:HttpClient
    ) {}

    update(newUser:User){
        let tUrl = this.authService.appendToken(this.url);
        const requestBody = {
            "userID":newUser.$userID,
            "username":newUser.$username,
            "password":newUser.$password,
            "firstName":newUser.$firstName,
            "lastName":newUser.$lastName,
            "email":newUser.$email,
            "profilePicture":newUser.$profilePicture,
            "token":this.authService.getToken()
        }
        const header = {
            headers:new HttpHeaders({
                ContentType:'application/json',
                responseType:'text',
                observe:'response'
            })
        }
        // console.log(requestBody);
        // return null;
        return this.httpClient.put(this.url,requestBody,header);
    }
}
