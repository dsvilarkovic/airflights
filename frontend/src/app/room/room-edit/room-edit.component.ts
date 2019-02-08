import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { RoomService } from 'src/services/room.service';
import { Room } from 'src/app/room';
import { NgForOf } from '@angular/common';
import { NgForm } from '@angular/forms';
import { HotelService } from 'src/services/hotel.service';
import { ROLE_H } from 'src/app/globals';
import { TProjectionNode } from '@angular/core/src/render3/interfaces/node';
import { TokenStorageService } from 'src/services/auth/token-storage.service';

@Component({
  selector: 'app-room-edit',
  templateUrl: './room-edit.component.html',
  styleUrls: ['./room-edit.component.scss']
})
export class RoomEditComponent implements OnInit {

  room: any = {};
  hotel: any = {};
  roomNumber : string;

  constructor(private route: ActivatedRoute, private router: Router, private ts: TokenStorageService, private hotelservice : HotelService, private roomService: RoomService) { }

  reserved: any = false;

  ngOnInit() {

    if (!this.ts.getAuthorities().includes(ROLE_H)) {
      alert("Unauthorized");
      this.router.navigate(['/home']);
    }

    const id = this.route.snapshot.params['id'];
    this.roomService.get(id).subscribe(data =>{
      this.room = data;
      this.roomNumber = this.room.number;
    });

    const id_h = this.route.snapshot.params['idh'];
    this.hotelservice.get(id_h).subscribe(data =>{
      this.hotel = data;
    });

    this.roomService.isReserved(id).subscribe(data =>{
      this.reserved = data;
    });

  }

  gotoList() {
    this.router.navigate(['/hotel/edit/rooms/list']);
  }

  update(from : NgForm) {
    this.roomService.update(this.room).subscribe(result => {
      this.gotoList();
    }, error => console.error(error));
  }

  endPromo() {
    this.roomService.endPromo(this.room).subscribe( result => {
      //this.gotoList();
    }, error => console.error(error));

    this.roomService.cleanPromo(this.room.id).subscribe( result => {
      this.gotoList();
    }, error => console.error(error));
  }

}
