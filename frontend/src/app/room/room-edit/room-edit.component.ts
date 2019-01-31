import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { RoomService } from 'src/services/room.service';
import { Room } from 'src/app/room';
import { NgForOf } from '@angular/common';
import { NgForm } from '@angular/forms';
import { HotelService } from 'src/services/hotel.service';

@Component({
  selector: 'app-room-edit',
  templateUrl: './room-edit.component.html',
  styleUrls: ['./room-edit.component.scss']
})
export class RoomEditComponent implements OnInit {

  room: any = {};
  hotel: any = {};
  roomNumber : string;

  constructor(private route: ActivatedRoute, private router: Router, private hotelservice : HotelService, private roomService: RoomService) { }

  ngOnInit() {
    const id = this.route.snapshot.params['id'];
    this.roomService.get(id).subscribe(data =>{
      this.room = data;
      this.roomNumber = this.room.number;
    });

    const id_h = this.route.snapshot.params['idh'];
    this.hotelservice.get(id_h).subscribe(data =>{
      this.hotel = data;
    });

  }

  gotoList() {
    this.router.navigate(['/hotel/edit/' + this.hotel.id + '/rooms']);
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
