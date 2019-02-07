import { API } from './../app/globals';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
let url : string = "//localhost:8080/api/auth/signin";

@Injectable({
  providedIn: 'root'
})
export class LoginService {

  constructor(private http: HttpClient) { }

  login(user: Object): Observable<any> {
    return this.http.post(url,user);
  }

  getLogged(username): Observable<any> {
    return this.http.get(API+"abstractUsers/logged",username);
  }

  getLoggedById(id): Observable<any> {
    return this.http.get(API + "abstractUsers/loggedById/"+id);
  }

  getLoggedByIdCompany(id): Observable<any> {
    return this.http.get(API + "abstractUsers/loggedByIdCompany/"+id);
  }

  getCheckVerify(id):  Observable<any> {
    return this.http.get(API + "abstractUsers/checkFirstAttemp/"+id);
  }

  setNewPassword(pass,id): Observable<any> {
    return this.http.get(API + "abstractUsers/setNewPassword/"+pass+"/"+id);
  }
}
