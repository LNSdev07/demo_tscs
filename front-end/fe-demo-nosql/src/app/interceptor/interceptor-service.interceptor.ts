import { Injectable } from '@angular/core';
import {
  HttpRequest,
  HttpHandler,
  HttpEvent,
  HttpInterceptor
} from '@angular/common/http';
import { Observable } from 'rxjs';
import { TokenService } from '../common/token.service';

@Injectable()
export class InterceptorServiceInterceptor implements HttpInterceptor {

  constructor(private tokenService: TokenService) {}

  intercept(request: HttpRequest<unknown>, next: HttpHandler): Observable<HttpEvent<unknown>> {
    const token = this.tokenService.getToken();

    const authReq = request.clone({
      headers: request.headers.set('Authorization', 'Bearer ' + token)
    });
    console.log('Đã đính token vào request. Token = '+ token);
    return next.handle(authReq);
  }
}
