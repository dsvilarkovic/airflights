import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import {Observable,of, from } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class TestService {

  constructor(private http: HttpClient) {
  }
  
  test(): String {
  	return this.http.get('//localhost:8080/api/abstractUsers/test');
  }
  
}
