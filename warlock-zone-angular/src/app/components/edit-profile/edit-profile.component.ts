import { Component, OnInit } from '@angular/core';
import { NgbModal,NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { User } from '../../models/user';

@Component({
  selector: 'app-edit-profile',
  templateUrl: './edit-profile.component.html',
  styleUrls: ['./edit-profile.component.css']
})
export class EditProfileComponent implements OnInit {


  editUser: User;
  new_password1: string;
  new_password2: string;

  constructor(public activeModal: NgbActiveModal)
  { }

  ngOnInit() {
      //this should be initalized by the current logged in user
      this.editUser = new User();
      this.editUser.$firstName = "Matt";
      this.editUser.$lastName = "Warlock";
      this.editUser.$email = "warlock@zone.org";
      this.editUser.$username = "warloccZ0ne";
  }
  updateUser():void{
    console.log("Updating user information!");
    console.log("check if current password is correct");
    console.log("check if new passwords match up");
  }
}
