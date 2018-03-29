import { Component, OnInit } from '@angular/core';
import { AuthTokenService } from '../../services/cache/authToken/auth-token.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  constructor(){}
    //   private authServ:AuthTokenService) { }

  ngOnInit() {
    //   console.log(this.authServ.getToken());
  }
  
}
