import { AuthenticationService } from '../authentication/authentication.service'
import { Injectable } from '@angular/core';
import  { Observable } from 'rxjs/Observable';
import { HttpEvent,
        HttpRequest,
        HttpHandler,
        HttpInterceptor } from '@angular/common/http';
@Injectable()
export class TokenInterceptorService implements HttpInterceptor
{

    constructor(private authService : AuthenticationService)
    {

    }

    intercept(request: HttpRequest<any>, next: HttpHandler) : Observable<HttpEvent<any>>
    {
        console.log('intercept...');
        console.log('interceptor: '+ this.authService.getLoginToken())
        request = request.clone(
            {
                setHeaders: 
                {
                    Authorization: `Bearer ${this.authService.getLoginToken()}`
                }
            }
        );
        return next.handle(request);
    }
}