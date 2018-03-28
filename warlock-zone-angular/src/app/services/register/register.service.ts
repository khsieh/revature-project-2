import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { User } from '../../models/user';
// import { ContentType } from '@angular/http/src/enums';

@Injectable()
export class RegisterService {

    private url:string  = "http://ec2-13-58-228-189.us-east-2.compute.amazonaws.com/user";

    constructor(private htttpClient:HttpClient) { }

    submit(newUser:User){
        console.log(newUser);
        const requestBody = {
            "username":newUser.$username,
            "password":newUser.$password,
            "firstName":newUser.$firstName,
            "lastName":newUser.$lastName,
            "email":newUser.$email,
            "profilePicture":newUser.$profilePicture
        }
        const header = {
            headers:new HttpHeaders({
                ContentType:'application/json',
                responseType:'text',
                observe:'response'
            })
        }
        // console.log("REQUEST BODY: " + requestBody);
        // let newUserJSON = JSON.parse(JSON.stringify(newUser));
        // console.log(newUserJSON);
        return this.htttpClient.post(this.url,requestBody,header);
    }

}
