import { Injectable } from '@angular/core';
import { HttpHeaders, HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { ADMIN_API, API } from 'src/app/globals';
import { Admin } from 'src/app/admin';
import { ArrayType } from '@angular/compiler';
import { Misc } from 'src/app/misc';

@Injectable({
  providedIn: 'root'
})
export class AdminsService {

  private headers = new HttpHeaders({'Content-Type': 'application/json'});

  constructor(private http: HttpClient) {

  }

  getAll(): Observable<any> {
    return this.http.get(ADMIN_API + 'all');
  }

  getAllA(): Observable<any> {
    return this.http.get(ADMIN_API + 'allA');
  }

  save(admin: Admin) { 
    return this.http.post(ADMIN_API + "add", admin, {headers: this.headers});
  } 

  remove(id: number) {
    return this.http.delete(ADMIN_API + id);
  }

  getAdmin(id: string) : any {
    return this.http.get(ADMIN_API + id);
  }

  update(admin: any) {
    return this.http.put(API + "userProfile/update", admin, {headers: this.headers});
  }

  updatePass(admin: any) {
    return this.http.put(ADMIN_API + "passUpdate", admin, {headers: this.headers});
  }

  updateAdminProfile(admin: any) {
    return this.http.put(ADMIN_API + "updateAdmin", admin, {headers: this.headers})
  }

  removeRac(id: number) : Observable<any> {
    return this.http.delete(ADMIN_API + "rac/" + id);
  }

  removeAir(id: number) : Observable<any> {
    return this.http.delete(ADMIN_API + "air/" + id);
  }

  getMisc(): any {
    return this.http.get(ADMIN_API + "misc");
  }

  getMiscAll(): Observable<any> {
    return this.http.get(ADMIN_API + "miscAll");
  }

  upMisc(m: Misc) {
    return this.http.post(ADMIN_API + "misc", m, {headers: this.headers});
  }

}
