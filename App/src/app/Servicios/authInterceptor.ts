import { HTTP_INTERCEPTORS } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { HttpInterceptor, HttpHandler, HttpRequest } from '@angular/common/http';
 
import { TokenServiceService } from './token-service.service';
 
const TOKEN_HEADER_KEY = 'Authorization';
 
@Injectable()
export class authInterceptor implements HttpInterceptor {
 
    constructor(private token: TokenServiceService) { }
 
    intercept(req: HttpRequest<any>, next: HttpHandler) {
        let authReq = req;
        const tokenc = this.token.getToken();
        var codToken = tokenc.split(" ",2);
        const token = codToken[1];
        if (token != null) {
            authReq = req.clone({ headers: req.headers.set(TOKEN_HEADER_KEY, 'Bearer ' + token) });
        }
        return next.handle(authReq);
    }
}
 
export const httpInterceptorProviders = [
    { provide: HTTP_INTERCEPTORS, useClass: authInterceptor, multi: true }
];