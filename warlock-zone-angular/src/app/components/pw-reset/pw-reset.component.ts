import { Component, OnInit } from '@angular/core';
import { ResetPwService } from '../../services/reset-pw/reset-pw.service';

@Component({
  selector: 'app-pw-reset',
  templateUrl: './pw-reset.component.html',
  styleUrls: ['./pw-reset.component.css']
})
export class PwResetComponent implements OnInit {

    constructor(private resetService:ResetPwService) { }

    new_password1:string
    new_password2:string

    ngOnInit() {}

    sendNewPW(){
        if(this.new_password1 === this.new_password2){
            //send to Spring with token
        }
        else{
            console.log("password does not match");
        }
    }

}
