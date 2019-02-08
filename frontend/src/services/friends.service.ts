import { Injectable } from "@angular/core";
import { HttpClient } from "@angular/common/http";
import { Observable } from "rxjs";
import { API } from "src/app/globals";
@Injectable({
  providedIn: "root"
})
export class FriendsService {
  constructor(private http: HttpClient) {}

  // getFriends(): Observable<any> {
  //   return this.http.get(API + "userProfile/friends/find/all");
  // }

  getFriends(pageNo = 0): Observable<any> {
    return this.http.get(
      API + "userProfile/friends/find/all?page=" + pageNo + "&size=5"
    );
  }


  // getPending(): Observable<any> {
  //   return this.http.get(API + "userProfile/friends/pending");
  // }


  getPending(pageNo = 0): Observable<any> {
    return this.http.get(
      API + "userProfile/friends/pending?page=" + pageNo + "&size=5"
    );
  }

  getFriendsByKeyword(keyword:string,page: number = 0){
    return this.http.get(API + "userProfile/friends/search?page=" + page + "&size=3&" + "keyword=" + keyword);
  }

  getFriendRequests(pageNo): Observable<any> {
    return this.http.get(
      API + "userProfile/friends/requests?page=" + pageNo + "&size=5"
    );
  }

  search(pageNo, keyword): Observable<any> {
    return this.http.get(
      API +
        "userProfile/friends/search?keyword=" +
        keyword +
        "&page=" +
        pageNo +
        "&size=5&accepted=true"
    );
  }

  sendFriendRequest(friendshipDTO): Observable<any> {
    return this.http.post(API + "userProfile/friends/send", friendshipDTO);
  }

  removeFriend(userId): Observable<any> {
    return this.http.delete(API + "userProfile/friends/revert/" + userId, {});
  }

  acceptFriend(userId): Observable<any> {
    return this.http.post(API + "userProfile/friends/accept/" + userId, {});
  }
}
