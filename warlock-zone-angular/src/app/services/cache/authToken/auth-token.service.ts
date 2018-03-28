import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs/BehaviorSubject';
import { HttpParams, HttpClient } from '@angular/common/http';

@Injectable()
export class AuthTokenService {

    private token = new BehaviorSubject<string>("true");
    private observeToken = this.token.asObservable();
    
    constructor(private httpClient: HttpClient) { }

    setToken(newToken:string){
        this.token.next(newToken);
    }

    getToken(){
        return this.observeToken;
    }

    checkToken():boolean{
        // this.httpClient.post()
        // TODO:change this to httpClient post to check with DB?
        if(this.token.getValue() === "true")
            return true;
        else
            return false;
    }

}
