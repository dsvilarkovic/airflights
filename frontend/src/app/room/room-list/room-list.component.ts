import { Component, OnInit } from '@angular/core';
import { Hotel } from 'src/app/hotel';
import { RoomService } from 'src/services/room.service';
import { ActivatedRoute, Router } from '@angular/router';
import { HotelService } from 'src/services/hotel.service';
import { Room } from 'src/app/room';
import { ROLE_H } from 'src/app/globals';
import { TokenStorageService } from 'src/services/auth/token-storage.service';
import { AdminsService } from 'src/services/admins.service';

@Component({
  selector: 'app-room-list',
  templateUrl: './room-list.component.html',
  styleUrls: ['./room-list.component.scss']
})
export class RoomListComponent implements OnInit {

  rooms: Array<any>;
  hotel: any;
  temp: boolean;

  constructor(private ts: TokenStorageService, private route: ActivatedRoute, private router: Router, 
    private adminService: AdminsService, private roomService: RoomService, private hotelService: HotelService) { }

  ngOnInit() {
    
    if (!this.ts.getAuthorities().includes(ROLE_H)) {
      alert("Unauthorized");
      this.router.navigate(['/home']);
    }

    this.adminService.getAdmin(this.ts.getUser()).subscribe( r => {
      this.hotel = r.hotel;
      this.roomService.getRoomsInHotel(this.hotel.id).subscribe(data => {
        this.rooms = data;
        this.rooms.forEach(element => {
          this.roomService.isReserved(element.id).subscribe(data =>{
            element.reserved = data;
          });
        });
      });
    }, error => console.error(error));


  }

  delete(id : number) {
    this.roomService.remove(id).subscribe(result => {
      window.location.reload();
    }, error => console.error(error));
  }

  logout() {
    this.ts.signOut();
    this.router.navigate(['/login']);
  }

}
