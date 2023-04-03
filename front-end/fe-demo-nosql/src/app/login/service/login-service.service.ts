import { Injectable } from '@angular/core';
import { environment } from 'environments/environment';
import { TokenService } from 'src/app/common/token.service';
import { AdminDTO } from '../model/AdminDTO.model';
import { HttpClient } from '@angular/common/http';
import { BaseReponse } from 'src/app/common/basereponse';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class LoginServiceService {

  private URL_LOGIN = environment.API_LOCAL+'/auth/login'

  constructor(private tokenService: TokenService,
              private http: HttpClient) { }

  
  public login(requet: AdminDTO): Observable<BaseReponse<any>>{
       return this.http.post<BaseReponse<any>>(this.URL_LOGIN, requet);
  }
}
