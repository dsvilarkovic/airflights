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

  getFriendsByKeyword(keyword:string,page: number = 0){
    return this.http.get(API + "userProfile/friends/search?page=" + page + "&size=3&" + "keyword=" + keyword);
  }
}
