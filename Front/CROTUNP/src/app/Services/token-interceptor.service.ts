import { Injectable } from '@angular/core';
import {HttpInterceptor, HttpRequest, HttpHandler, HttpEvent} from "@angular/common/http";
import {Observable, throwError} from 'rxjs';
import {catchError} from "rxjs/operators";

@Injectable({
  providedIn: 'root'
})
export class TokenInterceptorService implements HttpInterceptor{

  constructor() { }

  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    const token=sessionStorage.getItem("token");
    if ( token) {

      req = req.clone({
        setHeaders: {
          Authorization: 'Bearer '+ token,
        },
      });
    }
    return next.handle(req).pipe(catchError(err => {
      if(err.status==401){
        console.log("----->t9adamna+ "+token);
      }
      const  error=err.error.message ||  err.statusText;
      return throwError(error);
    }))
  }
}
