import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs/BehaviorSubject';

@Injectable()
export class ToggleNewPostService {

    curState = new BehaviorSubject(false);
    curStateAsObserable = this.curState.asObservable();

    constructor() { }


    changeDisplay(cState:boolean){
        this.curState.next(cState);
    }
    // getDisplay(){
    //     return this.curStateAsObserable;
    // }
}
