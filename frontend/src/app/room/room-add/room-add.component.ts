import { Component, OnInit } from '@angular/core';
import { Room } from 'src/app/room';
import { RoomService } from 'src/services/room.service';
import { Router, ActivatedRoute } from '@angular/router';
import { HotelService } from 'src/services/hotel.service';
import { NgForm } from '@angular/forms';
import { ROLE_H } from 'src/app/globals';
import { TokenStorageService } from 'src/services/auth/token-storage.service';

@Component({
  selector: 'app-room-add',
  templateUrl: './room-add.component.html',
  styleUrls: ['./room-add.component.scss']
})
export class RoomAddComponent implements OnInit {

  hotel : any;
  room : Room = new Room();

  constructor(private roomService : RoomService, private ts: TokenStorageService, private hotelService : HotelService, private router : Router, private ar : ActivatedRoute) { }

  ngOnInit() {
    
    if (!this.ts.getAuthorities().includes(ROLE_H)) {
      alert("Unauthorized");
      this.router.navigate(['/home']);
    }

    const hotelId : number = this.ar.snapshot.params['id'];
    this.hotelService.get(hotelId).subscribe(data =>{
      this.hotel = data;
    });
    this.room.balcony = false;
    this.room.promo = false;
  }

  gotoList() {
    this.router.navigate(['hotel/edit/rooms/list']);
  }

  save(form : NgForm) {
    this.room.hotel = this.hotel;
    this.roomService.save(this.room).subscribe(result => {
      this.gotoList();
    }, error => console.error(error));
  }

}
