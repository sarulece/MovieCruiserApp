import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { map } from 'rxjs/operators/map';
import { User } from './user';
import  { Observable } from 'rxjs/Observable';
import * as jwt_decode from 'jwt-decode';
export const LOGIN_TOKEN:string = "jwt_token";
@Injectable()
export class AuthenticationService {

    authServiceEndPoint: string;
    token : string;

    constructor(private http: HttpClient)
    {
        this.authServiceEndPoint = "http://localhost:8082/user";
    } 

    loginUser(user: User) : Observable<any>
    {
        const url = this.authServiceEndPoint + "/login";
        return this.http.post(url, user);
    }

    registerUser(user: User)
    {
        const url =  this.authServiceEndPoint + "/register"
        return this.http.post(url, user, {responseType: 'text'});
    }

    setLoginToken(token)
    {
        localStorage.setItem(LOGIN_TOKEN, token);
    }

    getLoginToken()
    {
        return localStorage.getItem(LOGIN_TOKEN);
    }

    removeLoginToken()
    {
        localStorage.removeItem(LOGIN_TOKEN)
    }
    
    getTokenExpirationDate() : Date
    {
        const token = this.getLoginToken();
        const decoded = jwt_decode(token);
        if(decoded.exp == undefined)
            return null;
        const date = new Date(0);
        date.setUTCSeconds(decoded.exp)
        return date;
    }

    isTokenExpired() : boolean
    {
        const token = this.getLoginToken();
        if(!token) true;
       // const date = this.getTokenExpirationDate();
        //if(date == undefined || date == null) return false;
        //return (date.valueOf() < new Date().valueOf());
        return false;
    }
}