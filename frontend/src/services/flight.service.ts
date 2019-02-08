import { Injectable } from "@angular/core";
import { HttpClient } from "@angular/common/http";
import { Observable } from "rxjs";
import { API } from "src/app/globals";
import { Flight} from '../app/flight'
import { Airplane } from 'src/app/airplane';
@Injectable({
  providedIn: "root"
})
export class FlightService {
  
  constructor(private http: HttpClient) {}

  getAllFlights(): Observable<any> {
    return this.http.get(API + "flight/find/all");
  }

  getFlight(id: number) : Observable<any> {
    return this.http.get(API + "flight/" + id);
  }


  getPlane(planeId): Observable<any> {
    return this.http.get(API + "flight/airplane/" + planeId);
  } 
  getFlightPlane(flightId): Observable<any> {
    throw this.http.get(API + "flight/airplane/" + flightId);
  }
}
