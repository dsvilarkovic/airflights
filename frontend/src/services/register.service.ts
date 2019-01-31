import { Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class RegisterService {

  constructor(private http: HttpClient) { }

  register(user : Object): Observable<any> { 
    return this.http.post("//localhost:8080/api/auth/signup",user);
  }

  sendMail(id) {
    return this.http.get("//localhost:8080/api/abstractUsers/mail/"+id);
  }

  verify(id): Observable<any> {
    return this.http.get("//localhost:8080/api/abstractUsers/verify/"+id);
  }


}
