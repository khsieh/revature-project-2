import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Router } from '@angular/router';
import {NgbModal, NgbActiveModal} from '@ng-bootstrap/ng-bootstrap';

import { LoginService } from '../../services/login/login.service';
import { RegisterComponent } from '../register/register.component';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

    login_username: string;
    login_password: string;

    constructor(
        private loginService:LoginService,
        private router:Router,
        private regModal:NgbModal
    ) { }

    ngOnInit() {
    }


    validate():void {
        console.log('validating user: ' + this.login_username);
        this.loginService.validate(this.login_username,this.login_password).subscribe(
            resp=>{
                console.log(resp);
            },
            err=>{
                console.log(err);
            }
        );
    }

    register(){
        const modalRef = this.regModal.open(RegisterComponent);
        console.log("Registering user");
    }

}
