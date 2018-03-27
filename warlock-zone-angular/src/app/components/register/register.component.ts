import { Component, OnInit } from '@angular/core';
import { NgbModal,NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { User } from '../../models/user';
import { RegisterService } from '../../services/register/register.service';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {

    newUser: User = new User();
    new_first_name:string;
    new_last_name:string;
    new_email:string;
    new_username:string;
    new_password1:string;
    new_password2:string;
    new_ProfilePicture;

    constructor(
        private regService:RegisterService,
        public activeModal: NgbActiveModal
    ) { }

    ngOnInit() {
    }

    submit():void{
        console.log("submitting new user info...");
        this.newUser.$firstName = this.new_first_name;
        this.newUser.$lastName = this.new_last_name;
        this.newUser.$email = this.new_email;
        this.newUser.$username = this.new_username;
        //TODO:handle pictures with S3?
        this.newUser.$profilePicture = null;//this.new_ProfilePicture;
        this.newUser.$password = this.new_password1;
        if(this.newUser.$password === this.new_password2){
            this.regService.submit(this.newUser).subscribe(
                resp=>{
                    //TODO: set status in backend
                    console.log("register success!");
                },
                err=>{
                    console.log(err);
                }
            );
            console.log("matching passwords");
            console.log("go!");
        }
        else{
            //alert
            console.log("passwords did not match")
            return //get out of the function
        }
        
    }
}
