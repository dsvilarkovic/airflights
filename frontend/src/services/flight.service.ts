import { Injectable } from "@angular/core";
import { HttpClient } from "@angular/common/http";
import { Observable, ObservableLike } from "rxjs";
import { API } from "src/app/globals";
import { Flight} from '../app/flight'
import { Airplane } from 'src/app/airplane';
import { FlightTicket } from 'src/app/passenger-form/flight-ticket';
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

  getReservedSeats(flightId) : Observable<any>{
    return this.http.get(API + "flight/reserved-seats/" + flightId);
  } 

  sendFlightTicket(flightTicket : FlightTicket){
    let link =API + "flight-ticket/create/fast";
    this.http.post(link, JSON.stringify(flightTicket));
  }
}
