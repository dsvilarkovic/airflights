import { Injectable } from '@angular/core';
import { HttpHeaders, HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { ADMIN_API } from 'src/app/globals';

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
}
