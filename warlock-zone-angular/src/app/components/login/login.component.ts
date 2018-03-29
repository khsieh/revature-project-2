import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Router } from '@angular/router';
import {NgbModal, NgbActiveModal} from '@ng-bootstrap/ng-bootstrap';

import { User } from '../../models/user';

import { LoginService } from '../../services/login/login.service';
import { RegisterComponent } from '../register/register.component';
import { CurUserService } from '../../services/cache/curUser/cur-user.service';
import { AuthTokenService } from '../../services/cache/authToken/auth-token.service';
import { AlertService } from '../../services/alert/alert.service';

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
        private currentUser:CurUserService,
        private router:Router,
        private regModal:NgbModal,
        private authService:AuthTokenService,
        private alertService:AlertService
    ) { }

    ngOnInit() {
    }

    error(message:string){
        this.alertService.error(message);
    }
    
    validate():void {
        console.log('validating user: ' + this.login_username);
        this.loginService.validate(this.login_username,this.login_password).subscribe(
            resp=>{
                let respJSON = JSON.parse(resp.body);
                if(resp.status == 202){
                    //user successfully logged in
                    //storing data in cache obserable
                    let newUser = new User();
                    newUser.$userID = respJSON["userID"];
                    newUser.$firstName = respJSON["firstName"];
                    newUser.$lastName = respJSON["lastName"];
                    newUser.$email = respJSON["email"];
                    newUser.$profilePicture = respJSON["profilePicture"];
                    newUser.$username = respJSON["username"];
                    newUser.$password = respJSON["password"];
                    this.currentUser.setUser(newUser);
                    this.authService.setToken(respJSON["token"]);
                    // this.authService.setToken("true");
                }
                // redirect them to app-home
                this.router.navigate(["home"]);
            },
            err=>{
                //pop alert
                this.error("Wrong Password!");
                console.log(err.status);
            }
        );
    }

    register(){
        const modalRef = this.regModal.open(RegisterComponent);
        console.log("Registering user");
    }

    forgotPW(){
        this.router.navigate(["forgot-password"]);
    }

}
