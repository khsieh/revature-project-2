import { Component, OnInit } from '@angular/core';
import { ResetPwService } from '../../services/reset-pw/reset-pw.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-pw-reset',
  templateUrl: './pw-reset.component.html',
  styleUrls: ['./pw-reset.component.css']
})
export class PwResetComponent implements OnInit {

    constructor(
        private resetService:ResetPwService,
        private router:Router
    ) { }

    new_password1:string
    new_password2:string
    private token:string

    ngOnInit() {
        this.token = this.router.parseUrl(this.router.url).queryParams.token;
    }

    sendNewPW(){
        if(this.new_password1 === this.new_password2){
            this.resetService.resetPW(this.new_password1,this.token);
        }
        else{
            console.log("password does not match");
        }
    }

}
