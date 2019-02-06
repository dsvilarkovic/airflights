import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { ROOM_API } from 'src/app/globals';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Room } from 'src/app/room';
import { PromoRoom } from 'src/app/PromoRoom';

@Injectable({
  providedIn: 'root'
})
export class RoomService {

  private headers = new HttpHeaders({'Content-Type': 'application/json'});

  constructor(private http: HttpClient) { }

  getRoomsInHotel  (hotelId : number) : Observable<any> {
    return this.http.get(ROOM_API + "hotel/" + hotelId);
  }

  save(room : Room): Observable<any> {
    let result: Observable<Object>;
    result = this.http.post(ROOM_API + "add", room, {headers: this.headers});

    return result;
  }

  remove(id: number) {
    let result: Observable<Object>;
    result = this.http.delete(ROOM_API + id);

    return result;
  }

  get(id: number) {
    return this.http.get(ROOM_API + id);
  }

  update(room : Room) {
    return this.http.put(ROOM_API, room, {headers: this.headers});
  }

  addPromoExtra(promo: PromoRoom) {
    return this.http.post(ROOM_API + 'promoExtra', promo, {headers: this.headers});
  }

  endPromo(room: Room) {
    return this.http.put(ROOM_API + 'endPromo', room, {headers: this.headers});
  }

  cleanPromo(id: number) {
    return this.http.put(ROOM_API + 'cleanPR/' + id, {headers: this.headers});
  }

  getPromoRooms(idh: number, sObj: any) : Observable<any> {
    return this.http.post(ROOM_API + idh + '/searchPromos', sObj, {headers: this.headers})
  }

  getSearchRooms(idh: number, sObj: any) : Observable<any> {
    return this.http.post(ROOM_API + idh + '/searchRooms', sObj, {headers: this.headers})
  }

}
