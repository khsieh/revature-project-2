import { Component, OnInit } from '@angular/core';
import { NgbModal,NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {

    new_first_name:string;
    new_last_name:string;
    new_email:string;
    new_username:string;
    new_password1:string;
    new_password2:string;

    constructor(public activeModal: NgbActiveModal) { }

    ngOnInit() {
    }

    submit():void{
        console.log("submitting new user info");
    }
}
