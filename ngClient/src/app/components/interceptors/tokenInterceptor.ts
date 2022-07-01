import { HTTP_INTERCEPTORS, HttpEvent, HttpErrorResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import {
  HttpInterceptor,
  HttpHandler,
  HttpRequest,
} from '@angular/common/http';
import { BehaviorSubject, catchError, filter, Observable, retry, retryWhen, switchMap, take, throwError } from 'rxjs';
import { SecurityService } from "../../services/security.service";


@Injectable()
export class TokenInterceptor implements HttpInterceptor {
  constructor(private securityService: SecurityService) {}

  intercept(
    req: HttpRequest<any>,
    next: HttpHandler
  ): Observable<any> {
    const token = this.securityService.access_token;

    if (token) {
      req = this.addToken(req, token);
    }

    return next.handle(req).pipe(
      catchError((err) => {
        if ( err instanceof HttpErrorResponse && (err.status === 401 || err.status === 403) ) {
          console.log('refreshing token ...');
          return this.handle401Error(req, next);
        }
        const error = err.error.message || err.statusText;
        return throwError(() => new Error(error));
      })
    )
  }

  private addToken(request: HttpRequest<any>, token: string) {
    return request.clone({
      setHeaders: {
        'Authorization': `Bearer ${token}`
      }
    });
  }


  private isRefreshing = false;
  private refreshTokenSubject: BehaviorSubject<any> = new BehaviorSubject<any>(null);

  private handle401Error(request: HttpRequest<any>, next: HttpHandler) {
    if (!this.isRefreshing) {
      this.isRefreshing = true;
      this.refreshTokenSubject.next(null);

      return this.securityService.refreshToken().pipe(
        switchMap((token: any) => {
          this.isRefreshing = false;
          this.refreshTokenSubject.next(token.refresh_token);
          this.securityService.access_token = token.access_token
          this.securityService.refresh_token = token.refresh_token
          localStorage.setItem("access_token", token.access_token)
          localStorage.setItem("refresh_token", token.refresh_token)
          this.securityService.getUser();

          return next.handle(this.addToken(request, token.jwt));
        }));
    } else {
      return this.refreshTokenSubject.pipe(
        filter(token => token != null),
        take(1),
        switchMap(jwt => {
          return next.handle(this.addToken(request, jwt));
        }));
    }
  }
}




export const TokenInterceptorProvider = [
  { provide: HTTP_INTERCEPTORS, useClass: TokenInterceptor, multi: true },
];
