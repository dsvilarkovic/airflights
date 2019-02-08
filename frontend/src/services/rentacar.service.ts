import { VEHICLE_API, BRANCH_API, USER_API, RATING_API } from './../app/globals';
import { Branch } from './../app/branch';
import { rentacar } from './../app/rentacar';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { RENT_A_CAR_API } from 'src/app/globals';

@Injectable({
  providedIn: 'root'
})
export class RentacarService {

  rac: rentacar;

  constructor(private http: HttpClient) { }

  getAll(): Observable<any> {
    return this.http.get(RENT_A_CAR_API + "test");
  }

  getOne(id): Observable<any> {
    return this.http.get(RENT_A_CAR_API +id);
  } 

  getAllVehicles(): Observable<any> {
    return this.http.get(VEHICLE_API + "test");
  }

  getAllBranches(): Observable<any> {
    return this.http.get(BRANCH_API + "getAllBranches");
  }

  addVehicle(vehicle: Object, id1,id2): Observable<any> {

    return this.http.post(VEHICLE_API + "addVehicle/"+id1+"/"+id2,vehicle);
  }

  updateVehicle(vehicle: Object): Observable<any> {
    return this.http.put(VEHICLE_API + "update",vehicle);
  }

  deleteVehicle(id): Observable<any> {
    return this.http.delete(VEHICLE_API + "delete/"+id);
  }

  addBranch(branch: Branch,id): Observable<any> {
    return this.http.post(BRANCH_API + "addBranch/"+id,branch)
  }

  updateBranch(branch: Branch): Observable<any> {
    return this.http.put(BRANCH_API + "update",branch);
  }

  deleteBranch(id): Observable<any> {
    return this.http.delete(BRANCH_API + "delete/"+id);
  }

  getLogged(httOptions):Observable<any> {
    return this.http.get(USER_API + "logged",httOptions);
  }

  search(name): Observable<any> {
    return this.http.get(RENT_A_CAR_API + "search/"+name);
  }

  getAllById(id): Observable<any> {
    return this.http.get(VEHICLE_API + "getAll/"+id);
  }

  searchBranch(id): Observable<any> {
    return this.http.get(BRANCH_API + "search/"+id);
  }

  save(rac: Object) : Observable<any> {
    return this.http.post(RENT_A_CAR_API, rac);
  }

  remove(id: number) : Observable<any> {
    return this.http.delete(RENT_A_CAR_API + id);
  }

  getAllRacs(): Observable<any> {
    return this.http.get(RENT_A_CAR_API + "allRacs");
  }

  getAllDiscount(id,name): Observable<any> {
    return this.http.get(VEHICLE_API + "getAllDiscount/"+id+"/"+name);
  }

  rateRac(rate,id,user): Observable<any> {
    return this.http.get(RATING_API + "rate/"+rate+"/"+id+"/"+user);
  }

  rateVehicle(rate,id,user): Observable<any> {
    return this.http.get(RATING_API + "rate/vehicle/"+rate+"/"+id+"/"+user);
  }

  lastWeek(id: number) {
    return this.http.get(RENT_A_CAR_API + 'chartWeek/' + id);
  }

  lastM(id: number) {
    return this.http.get(RENT_A_CAR_API + 'lastM/' + id);
  }

  lastYear(id: number) {
    return this.http.get(RENT_A_CAR_API + 'lastYear/' + id);
  }

}
  