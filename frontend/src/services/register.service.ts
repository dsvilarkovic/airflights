import { API } from './../app/globals';
import { Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class RegisterService {

  constructor(private http: HttpClient) { }

  register(user : Object): Observable<any> { 
    return this.http.post(API + "auth/signup",user);
  }

  sendMail(id) {
    return this.http.get(API + "abstractUsers/mail/"+id);
  }

  verify(id): Observable<any> {
    return this.http.get(API + "abstractUsers/verify/"+id);
  }


}
