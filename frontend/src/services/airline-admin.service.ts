import { Injectable } from "@angular/core";
import { HttpClient } from "@angular/common/http";
import { Observable } from "rxjs";
import { API } from "src/app/globals";
@Injectable({
  providedIn: "root"
})
export class AirlineAdminService {
  constructor(private http: HttpClient) {}

  updateAirline(airline): Observable<any> {
    return this.http.put(API + "airline/update/", airline);
  }

  getPlanes(pageNo, airline_id): Observable<any> {
    return this.http.get(
      API +
        "airline/find/airplanes/" +
        airline_id +
        "?page=" +
        pageNo +
        "&size=5"
    );
  }

  getFlights(pageNo, airline_id): Observable<any> {
    return this.http.get(
      API + "airline/find/flights/" + airline_id + "?page=" + pageNo + "&size=5"
    );
  }

  getDestinations(airline_id): Observable<any> {
    return this.http.get(API + "airline/find/airport/" + airline_id);
  }

  getPlane(planeId): Observable<any> {
    return this.http.get(API + "airline/airplane/" + planeId);
  }

  updateSeatConfig(seatConf): Observable<any> {
    return this.http.put(API + "seats/update/", seatConf);
  }

  updatePlaneInfo(plane): Observable<any> {
    return this.http.put(API + "airline/airplane/update", plane);
  }

  addPlane(plane): Observable<any> {
    return this.http.post(API + "airline/airplane/create", plane);
  }

  removePlane(planeId): Observable<any> {
    return this.http.delete(API + "airline/airplane/delete/" + planeId);
  }

  addFlight(flight): Observable<any> {
    return this.http.post(API + "flight/add/", flight);
  }

  editFlight(flight): Observable<any> {
    return this.http.put(API + "flight/update/", flight);
  }

  removeFlight(flightId): Observable<any> {
    return this.http.delete(API + "flight/delete/" + flightId);
  }

  addLuggage(luggage): Observable<any> {
    return this.http.post(API + "luggage/add/", luggage);
  }

  removeLuggage(luggageId): Observable<any> {
    return this.http.delete(API + "luggage/delete/" + luggageId);
  }

  addDestination(destination, airlineId): Observable<any> {
    return this.http.post(API + "airport/add/" + airlineId, destination);
  }

  removeDestination(destId): Observable<any> {
    return this.http.delete(API + "airport/delete/" + destId);
  }

  getFlightPlane(flightId): Observable<any> {
    throw this.http.get(API + "airline/airplane/flight/" + flightId);
  }
    
  getAllDestinations(): Observable<any> {
    return this.http.get(API + "airport/all/");
  }
}
