import { Injectable } from "@angular/core";
import { HttpClient } from "@angular/common/http";
import { Observable } from "rxjs";
import { API } from "src/app/globals";
@Injectable({
  providedIn: "root"
})
export class FriendsService {
  constructor(private http: HttpClient) {}

  getFriends(): Observable<any> {
    return this.http.get(API + "userProfile/friends/find/all");
  }

  getPending(): Observable<any> {
    return this.http.get(API + "userProfile/friends/pending");
  }
}
