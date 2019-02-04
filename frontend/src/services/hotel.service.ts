import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Hotel } from 'src/app/hotel';
import { HOTEL_API } from '../app/globals';

@Injectable()
export class HotelService {

  //public API = '//localhost:8080/api/';
  //public HOTEL_API = this.API + 'hotel/';
  private headers = new HttpHeaders({'Content-Type': 'application/json'});

  constructor(private http: HttpClient) {
  }

  getAll(): Observable<any> {
    return this.http.get(HOTEL_API + 'list');
  }

  get(id: number) {
    return this.http.get(HOTEL_API + id);
  }

  save(hotel: Hotel): Observable<any> {
    let result: Observable<Object>;
    result = this.http.post(HOTEL_API + 'add', hotel, {headers: this.headers});

    return result;
  }

  update(hotel: Hotel): Observable<any> {
    let result: Observable<Object>;
    result = this.http.put(HOTEL_API, hotel, {headers: this.headers});

    return result;
  }

  remove(id: number) {
    let result: Observable<Object>;
    result = this.http.delete(HOTEL_API + id);

    return result;
  }

  search(obj: any) : Observable<any> {
    return this.http.post(HOTEL_API + 'search', obj, {headers: this.headers});
  }

  /*getAllRooms(id: number) {
    let result: Observable<any>;
    result = this.http.get(HOTEL_API + id + '/rooms');
    return result;
  }*/

}
