import { Branch } from './../app/branch';
import { rentacar } from './../app/rentacar';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class RentacarService {

  rac: rentacar;

  constructor(private http: HttpClient) { }

  getAll(): Observable<any> {
    return this.http.get("//localhost:8080/api/rentacar/test");
  }

  getOne(id): Observable<any> {
    return this.http.get("//localhost:8080/api/rentacar/"+id);
  } 

  getAllVehicles(): Observable<any> {
    alert("OVDE SAM");
    return this.http.get("//localhost:8080/api/vehicle/test");
  }

  getAllBranches(): Observable<any> {
    return this.http.get("//localhost:8080/api/branch/getAllBranches");
  }

  addVehicle(vehicle: Object): Observable<any> {
    alert("TUSAM");
    return this.http.post("//localhost:8080/api/vehicle/addVehicle",vehicle);
  }

  updateVehicle(vehicle: Object): Observable<any> {
    alert("Usao");
    return this.http.put("//localhost:8080/api/vehicle/update",vehicle);
  }

  deleteVehicle(id): Observable<any> {
    alert("Usao");
    return this.http.delete("//localhost:8080/api/vehicle/delete/"+id);
  }

  addBranch(branch: Branch): Observable<any> {
    return this.http.post("//localhost:8080/api/branch/addBranch",branch)
  }

  updateBranch(branch: Branch): Observable<any> {
    return this.http.put("//localhost:8080/api/branch/update",branch);
  }

  deleteBranch(id): Observable<any> {
    return this.http.delete("//localhost:8080/api/branch/delete/"+id);
  }

}
