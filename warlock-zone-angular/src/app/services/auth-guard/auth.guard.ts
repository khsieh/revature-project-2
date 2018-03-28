import { Injectable } from '@angular/core';
import { CanActivate, ActivatedRouteSnapshot, RouterStateSnapshot, Router } from '@angular/router';
import { Observable } from 'rxjs/Observable';
import { AuthTokenService } from '../cache/authToken/auth-token.service';


@Injectable()
export class AuthGuard implements CanActivate {

    constructor(private router:Router,private authService:AuthTokenService){}

    canActivate(
        next: ActivatedRouteSnapshot,
        state: RouterStateSnapshot): Observable<boolean> | Promise<boolean> | boolean {
        if(this.authService.checkToken()){
            return true;
        }
        this.router.navigate(['login']);
        alert("LIGHTNING BOLT!!");
        return false;
    }


}
