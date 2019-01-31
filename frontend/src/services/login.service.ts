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

}
