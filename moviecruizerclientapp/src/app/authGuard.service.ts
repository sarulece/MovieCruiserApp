import { Injectable } from '@angular/core';
import { AuthenticationService } from './modules/authentication/authentication.service';
import { Router, CanActivate } from '@angular/router'

@Injectable()
export class AuthguardService implements CanActivate
{
    constructor (private router: Router, private authService: AuthenticationService)
    {

    }

    canActivate()
    {
       if(!this.authService.isTokenExpired()) 
       {
           return true;
       }
       this.router.navigate(['/login']);
       return false;
    }
}
