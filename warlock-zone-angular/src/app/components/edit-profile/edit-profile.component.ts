import { Component, OnInit } from '@angular/core';
import { NgbModal, NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { User } from '../../models/user';
import { CurUserService } from '../../services/cache/curUser/cur-user.service';
import { UpdateUserService } from '../../services/update-user/update-user.service';
import { ValidateService } from '../../services/validate/validate.service';
import { AlertService } from '../../services/alert/alert.service';

@Component({
    selector: 'app-edit-profile',
    templateUrl: './edit-profile.component.html',
    styleUrls: ['./edit-profile.component.css']
})
export class EditProfileComponent implements OnInit {


    editUser: User = new User();
    new_password1: string;
    new_password2: string;
    cur_password: string;
    new_img: any;

    constructor(
        public activeModal: NgbActiveModal,
        private curUser: CurUserService,
        private updateService: UpdateUserService,
        private validateService: ValidateService,
        private alertService: AlertService
    ) { }

    ngOnInit() {
        //this should be initalized by the current logged in user
        this.curUser.getUser().subscribe(
            resp => {
                this.editUser.setAll(resp);
            },
            err => {
                console.log(err);
            }
        );
    }

    validateUser() {
        console.log(this.cur_password)
        this.validateService.check(this.cur_password).subscribe(
            resp => {
                console.log("validating user");
                if (this.new_password1 === this.new_password2) {
                    console.log("new pw matched!");
                    this.updateUser();
                    this.activeModal.close('Close');
                }
                else {
                    this.alertService.error("new pw mismatched!");
                    // alert('new pw doesn\'t matched');
                }
            },
            err => {
                this.alertService.error("wrong pw!");
                //   alert('wrong password!');
            }
        )
    }

    updateUser(): void {
        console.log("Updating user information!");

        if(this.new_password1 != null){
            this.editUser.$password = this.new_password1;
        }
        else{
            this.editUser.$password = this.cur_password;
        }   
        // console.log("NEW PASSWORD WILL BE: " + this.editUser.$password)
        //set image to b64
        this.editUser.profilePicture = this.new_img;
        // console.log(this.editUser);
        this.updateService.update(this.editUser).subscribe(
            resp => {
                console.log("update user successfully!");
                //update curUser
                console.log(this.editUser);
                this.curUser.setUser(this.editUser);
            },
            err => {

                console.log(err);
                // console.log("you done fucked up!");
            });
    }

    onFileChange(event) {
        let reader = new FileReader();
        if (event.target.files && event.target.files.length > 0) {
            let file = event.target.files[0];
            reader.readAsDataURL(file);
            reader.onloadend = () => {
                file = reader.result;
                this.new_img = file;
            };
        }

    }

}
