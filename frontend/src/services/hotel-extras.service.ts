import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { EXTRAS_API } from 'src/app/globals';
import { HotelExtra } from 'src/app/hotel-extras';

@Injectable({
  providedIn: 'root'
})
export class HotelExtrasService {

  private headers = new HttpHeaders({'Content-Type': 'application/json'});

  constructor(private http: HttpClient) { }

  getHotelExtras(hotelId: number) : Observable<any> {
    return this.http.get(EXTRAS_API + 'hotel/' + hotelId);
  }

  save(extra : HotelExtra): Observable<any> {
    let result: Observable<Object>;
    result = this.http.post(EXTRAS_API + "add", extra, {headers: this.headers});

    return result;
  }

  delete(id: number) {
    let result: Observable<Object>;
    result = this.http.delete(EXTRAS_API + id);

    return result;
  }

  get(id: number) {
    return this.http.get(EXTRAS_API + id);
  }

  update(extra : HotelExtra) {
    return this.http.put(EXTRAS_API, extra, {headers: this.headers});
  }

}
