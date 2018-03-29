import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import {NgbModal, NgbActiveModal} from '@ng-bootstrap/ng-bootstrap';

import { EditProfileComponent } from '../edit-profile/edit-profile.component';
import { ToggleNewPostService } from '../../services/util/toggle-new-post.service';
import { CurUserService } from '../../services/cache/curUser/cur-user.service';
import { User } from '../../models/user';
import { LogoutService } from '../../services/logout/logout.service';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent implements OnInit {

    private viewPost = false;
    currentUser:User = new User();
    constructor(
        private router:Router, 
        private editModal:NgbModal,
        private curUserService:CurUserService, 
        private tService:ToggleNewPostService,
        private logoutService:LogoutService
    ) { }

    ngOnInit() {
        this.curUserService.getUser().subscribe(
            resp=>{
                this.currentUser.setAll(resp);
                // this.currentUser.$image = "https://cdn.discordapp.com/attachments/419134919927922688/424223986424086530/thanks_kevin.png";
            },
            err=>{
                console.log(err);
            }
        );

        this.tService.curStateAsObserable.subscribe(
          resp=>{
            this.viewPost = resp;
          },
          err=>{
              
          }
        );

    }

    editProfile(){
        const modalRef = this.editModal.open(EditProfileComponent);
    }

    newPostControl(){
        this.viewPost = !this.viewPost;
        this.tService.changeDisplay(this.viewPost);
    }

    logout(){
        this.logoutService.bye();
    }

}
