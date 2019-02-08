import { Injectable } from "@angular/core";
import { HttpClient } from "@angular/common/http";
import { Observable } from "rxjs";
import { API } from 'src/app/globals';
@Injectable({
  providedIn: "root"
})
export class FlightService {
  constructor(private http: HttpClient) {}

  getAllFlights(): Observable<any> {
    return this.http.get("http://localhost:8080/api/flight/find/all");
  }

  getFlight(id: number) : Observable<any> {
    return this.http.get(API + "flight/" + id);
  }
}
