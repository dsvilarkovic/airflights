import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { AIRLINE_API } from 'src/app/globals';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class AirlineService {

  constructor(private http: HttpClient) {}

  getAllAirlines() : Observable<any> {
    return this.http.get(AIRLINE_API + "find/all");
  }

  remove(id: number) {
    return this.http.delete(AIRLINE_API + "delete/" + id);
  }

  save(airline: any) : any {
    return this.http.post(AIRLINE_API + "add", airline);
  }

  getOne(id: number) {
    return this.http.get(AIRLINE_API + "get/" + id);
  }

}
