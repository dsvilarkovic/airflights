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
    return this.http.get("//localhost:8080/api/rentacar/test");
  }

  getOne(id): Observable<any> {
    return this.http.get("//localhost:8080/api/rentacar/"+id);
  } 

  getAllVehicles(): Observable<any> {
    alert("OVDE SAM!!!");
    return this.http.get("//localhost:8080/api/vehicle/test");
  }

  getAllBranches(): Observable<any> {
    return this.http.get("//localhost:8080/api/branch/getAllBranches");
  }

  addVehicle(vehicle: Object, id1,id2): Observable<any> {
    alert("TUSAM");
    return this.http.post("//localhost:8080/api/vehicle/addVehicle/"+id1+"/"+id2,vehicle);
  }

  updateVehicle(vehicle: Object): Observable<any> {
    alert("Usao");
    return this.http.put("//localhost:8080/api/vehicle/update",vehicle);
  }

  deleteVehicle(id): Observable<any> {
    alert("Usao");
    return this.http.delete("//localhost:8080/api/vehicle/delete/"+id);
  }

  addBranch(branch: Branch,id): Observable<any> {
    return this.http.post("//localhost:8080/api/branch/addBranch/"+id,branch)
  }

  updateBranch(branch: Branch): Observable<any> {
    return this.http.put("//localhost:8080/api/branch/update",branch);
  }

  deleteBranch(id): Observable<any> {
    return this.http.delete("//localhost:8080/api/branch/delete/"+id);
  }

  getLogged(httOptions):Observable<any> {
    return this.http.get("//localhost:8080/api/abstractUsers/logged",httOptions);
  }

  search(name): Observable<any> {
    alert("Ovde!")
    return this.http.get("//localhost:8080/api/rentacar/search/"+name);
  }

  getAllById(id): Observable<any> {
    return this.http.get("//localhost:8080/api/vehicle/getAll/"+id);
  }

  searchBranch(id): Observable<any> {
    return this.http.get("//localhost8080/api/branch/search/"+id);
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
    return this.http.get("//localhost:8080/api/vehicle/getAllDiscount/"+id+"/"+name);
  }

  rateRac(rate,id,user): Observable<any> {
    return this.http.get("//localhost:8080/api/rating/rate/"+rate+"/"+id+"/"+user);
  }

  rateVehicle(rate,id,user): Observable<any> {
    return this.http.get("//localhost:8080/api/rating/rate/vehicle/"+rate+"/"+id+"/"+user);
  }
}
