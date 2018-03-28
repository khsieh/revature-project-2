import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs/BehaviorSubject';
import { HttpParams, HttpClient } from '@angular/common/http';

@Injectable()
export class AuthTokenService {

    private token = new BehaviorSubject<string>(null);
    private observeToken = this.token.asObservable();
    
    constructor(private httpClient: HttpClient) { }

    setToken(newToken:string){
        this.token.next(newToken);
    }

    getToken():string{
        return this.token.getValue();
    }

    appendToken(url:string):string{
        return url + "?token=" +this.getToken();
    }

    //get rid of this later
    checkToken():boolean{
        // this.httpClient.post()
        // TODO:change this to httpClient post to check with DB?
        if(this.token.getValue() != null)
            return true;
        else
            return false;
    }
}
