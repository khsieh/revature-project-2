import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs/BehaviorSubject';
import { User } from '../../../models/user';



@Injectable()
export class CurUserService {

    constructor() { }

    private curUser = new BehaviorSubject(new User());
    private observeCurUser = this.curUser.asObservable();

    //sets the variable to be observed
    setUser(newUser:User){
        this.curUser.next(newUser);
    }

    getStaticUser(){
        return this.curUser.getValue();
    }
    
    //use this to subscribe to the user object
    getUser(){
        return this.observeCurUser;
    }

}
