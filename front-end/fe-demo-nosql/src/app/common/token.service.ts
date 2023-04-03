import { Injectable } from '@angular/core';
import { environment } from 'environments/environment';

@Injectable({
  providedIn: 'root'
})
export class TokenService {

  constructor() { }

  public setToken(token: string){
    localStorage.removeItem(environment.TOKEN_KEY);
    localStorage.setItem(environment.TOKEN_KEY, token);
  };
  
  public getToken(): string | null {
    return localStorage.getItem(environment.TOKEN_KEY);
  }

  public setName(name: string){
    localStorage.removeItem(environment.NAME_KEY);
    localStorage.setItem(environment.NAME_KEY, name);
  }

  public getName(): string | null{
    return localStorage.getItem(environment.NAME_KEY);
  }

  public setRole(role: string){
    localStorage.removeItem(environment.ROLE_KEY);
    localStorage.setItem(environment.ROLE_KEY, role);
  }
  
  public getRole(): string | null{
    return localStorage.getItem(environment.ROLE_KEY);
  }

  public removeDataLocalStorage(){
    localStorage.removeItem(environment.NAME_KEY)
    localStorage.removeItem(environment.ROLE_KEY)
    localStorage.removeItem(environment.TOKEN_KEY)
  }
}
