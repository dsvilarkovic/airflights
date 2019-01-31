import { Injectable } from '@angular/core';
import { HttpHeaders, HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { ADMIN_API } from 'src/app/globals';
import { Admin } from 'src/app/admin';

@Injectable({
  providedIn: 'root'
})
export class AdminsService {

  private headers = new HttpHeaders({'Content-Type': 'application/json'});

  constructor(private http: HttpClient) {

  }

  getAll(): Observable<any> {
    return this.http.get(ADMIN_API + 'all');
  }

  save(admin: Admin) { 
    return this.http.post(ADMIN_API + "add", admin, {headers: this.headers});
  } 

  remove(id: number) {
    return this.http.delete(ADMIN_API + id);
  }

}
