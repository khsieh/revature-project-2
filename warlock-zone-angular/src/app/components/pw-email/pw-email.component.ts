import { Component, OnInit } from '@angular/core';

@Component({
    selector: 'app-pw-email',
    templateUrl: './pw-email.component.html',
    styleUrls: ['./pw-email.component.css']
})
export class PwEmailComponent implements OnInit {

    reset_email: string
    constructor() { }

    ngOnInit() {
    }
    
    sendEmail(){
        console.log("Sending email!");
    }
}
