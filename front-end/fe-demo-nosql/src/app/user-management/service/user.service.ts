import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from 'environments/environment';
import { Observable } from 'rxjs';
import { BaseReponse } from 'src/app/common/basereponse';
import { Paginator } from 'src/app/common/paginator';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  private URL = environment.API_LOCAL+'/user/find-user'

  constructor(private http: HttpClient) { }


  public findUser(input: Paginator): Observable<BaseReponse<any>>{
    return this.http.post<BaseReponse<any>>(this.URL, input);
  }
}
