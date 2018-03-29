import { Injectable } from '@angular/core';
import { HttpClient, HttpParams, HttpHeaders } from '@angular/common/http';

@Injectable()
export class ResetPwService {

    private urlEmail:string  = "http://ec2-13-58-228-189.us-east-2.compute.amazonaws.com/forgot-password";
    private urlRPW:string  = "http://ec2-13-58-228-189.us-east-2.compute.amazonaws.com/reset-password"
    constructor(private httpClient: HttpClient) { }

    requestEmail(email:string) {
        const requestBody = email;
        const header = {
            headers:new HttpHeaders({
                ContentType:'text',
                responseType:'text',
                observe:'response'
            })
        }
        console.log(email);
        return this.httpClient.post(this.urlEmail,requestBody,header);
    }

    resetPW(newPassword:string, token:string ){
        const requestBody = newPassword;
        const header = {
            headers:new HttpHeaders({
                ContentType:'text'
            })
        }
        this.urlRPW += "?token=";
        this.urlRPW += token;
        return this.httpClient.post(this.urlRPW,requestBody,header);
    }

}
