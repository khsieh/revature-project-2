import { Component, OnInit } from '@angular/core';
import { NgbModal,NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { User } from '../../models/user';
import { CurUserService } from '../../services/cache/curUser/cur-user.service';
import { UpdateUserService } from '../../services/update-user/update-user.service';
import { ValidateService } from '../../services/validate/validate.service';

@Component({
  selector: 'app-edit-profile',
  templateUrl: './edit-profile.component.html',
  styleUrls: ['./edit-profile.component.css']
})
export class EditProfileComponent implements OnInit {


  editUser: User = new User();
  new_password1: string;
  new_password2: string;
  new_img;

  constructor(
      public activeModal: NgbActiveModal,
      private curUser:CurUserService,
      private updateService:UpdateUserService,
      private validateService:ValidateService
    ){}

  ngOnInit() {
      //this should be initalized by the current logged in user
      this.curUser.getUser().subscribe(
          resp=>{
            this.editUser.setAll(resp);
          },
          err=>{
            console.log(err);
          }
      );
  }

  validateUser(){
      if(this.validateService.check(this.editUser.$password)){
        if(this.new_password1 === this.new_password2){
            this.updateUser();
        }
        else{
            alert('new pw doesn\'t matched');
        }
      }
      else{
          alert('wrong password!');
      }
  }

  updateUser():void{
    console.log("Updating user information!");
    // console.log(this.editUser);
    //TODO need a way to check pw only? or should I just do /username
    this.updateService.update(this.editUser).subscribe(
        resp=>{
            console.log("update user successfully!");
            //update curUser
            this.curUser.setUser(this.editUser);
        },
        err=>{
            console.log(err);
            // console.log("you done fucked up!");
        });
  }
}
