import { VehicleReservation } from './../app/vehicleReservation';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { RESERVATION_API } from 'src/app/globals';

@Injectable({
  providedIn: 'root'
})
export class ReservationServiceService {

  constructor(private http: HttpClient) { }

  getAllById(id): Observable<any> {
    return this.http.get(RESERVATION_API + "getAll/"+id);
  }

  getAllByUserId(id): Observable<any> {
    return this.http.get(RESERVATION_API + "getAllByUserId/"+id);
  }

  cancel(id): Observable<any> {
    return this.http.delete(RESERVATION_API + "cancel/"+id);
  }

  checkDate(date1,date2,id): Observable<any> {
    return this.http.get(RESERVATION_API + "checkDate/"+date1+"/"+date2+"/"+id);//rent a car id je id
  }

  book(id,reservation): Observable<any> {
    return this.http.post(RESERVATION_API + "book/" +id,reservation)
  }

  getAllByDate(date1,date2,id): Observable<any> {
    return this.http.get(RESERVATION_API + "getAllByDate/"+date1+"/"+date2+"/"+id);
  }
}
