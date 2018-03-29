import { Component, OnInit } from '@angular/core';
import { ResetPwService } from '../../services/reset-pw/reset-pw.service';

@Component({
    selector: 'app-pw-email',
    templateUrl: './pw-email.component.html',
    styleUrls: ['./pw-email.component.css']
})
export class PwEmailComponent implements OnInit {

    reset_email: string
    constructor(private emailRService:ResetPwService) { }

    ngOnInit() {
    }
    
    sendEmail(){
        this.emailRService.requestEmail(this.reset_email).subscribe(
            resp=>{
                console.log(resp);
            },
            err=>{
                console.log(err);
            }
        );
        console.log("Sending email!");
    }
}
