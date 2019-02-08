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

  getAllAirlines(): Observable<any> {
    return this.http.get(API + "airline/find/all");
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

  findFlights(
    pageNo,
    arrivalId,
    departureId,
    arrivalDate,
    departureDate
  ): Observable<any> {
    return this.http.get(
      API +
        "flight/search/find/all" +
        "?page=" +
        pageNo +
        "&size=5&arrival_id=" +
        arrivalId +
        "&departure_id=" +
        departureId
    );
  }

  getAirlineInfo(id): Observable<any> {
    return this.http.get(API + "airline/get/" + id);
  }
}
