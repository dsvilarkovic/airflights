import { API } from 'src/app/globals';
import { SignUpInfo } from './../../app/forms/registerForm';
import { JwtResponse } from './../../app/jwt-response';
import { Observable } from 'rxjs';
import { AuthLoginInfo } from './../../app/forms/loginForm';
import { Injectable } from '@angular/core';
import { HttpHeaders, HttpClient } from '@angular/common/http';

const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};


@Injectable({
  providedIn: 'root'
})
export class AuthService {
  private loginUrl = API + 'auth/signin';
  private signupUrl = API + 'auth/signup';

  constructor(private http: HttpClient) {
  }

  attemptAuth(credentials: AuthLoginInfo): Observable<JwtResponse> {
    return this.http.post<JwtResponse>(this.loginUrl, credentials, httpOptions);
  }

  signUp(info: SignUpInfo): Observable<any> {
    return this.http.post<string>(this.signupUrl, info, httpOptions);
  }
}
