import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import {NgbModal, NgbActiveModal} from '@ng-bootstrap/ng-bootstrap';

import { EditProfileComponent } from '../edit-profile/edit-profile.component';
import { ToggleNewPostService } from '../../services/util/toggle-new-post.service';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent implements OnInit {

    private viewPost = false;
    constructor(
        private router:Router, 
        private editModal:NgbModal, 
        private tService:ToggleNewPostService
    ) { }

    ngOnInit() {
    }

    editProfile(){
        const modalRef = this.editModal.open(EditProfileComponent);
    }

    newPostControl(){
        this.viewPost = !this.viewPost;
        this.tService.changeDisplay(this.viewPost);
    }
}
