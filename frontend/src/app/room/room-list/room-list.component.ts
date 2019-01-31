import { Component, OnInit } from '@angular/core';
import { Hotel } from 'src/app/hotel';
import { RoomService } from 'src/services/room.service';
import { ActivatedRoute, Router } from '@angular/router';
import { HotelService } from 'src/services/hotel.service';
import { Room } from 'src/app/room';

@Component({
  selector: 'app-room-list',
  templateUrl: './room-list.component.html',
  styleUrls: ['./room-list.component.scss']
})
export class RoomListComponent implements OnInit {

  rooms: Array<any>;
  hotel: any;
  temp: boolean;

  constructor(private route: ActivatedRoute, private router: Router, 
    private roomService: RoomService, private hotelService: HotelService) { }

  ngOnInit() {
    this.temp = true;
    const hotelId : number = this.route.snapshot.params['id'];
    this.hotelService.get(hotelId).subscribe(data =>{
      this.hotel = data;
    });
    this.roomService.getRoomsInHotel(hotelId).subscribe(data => {
      this.rooms = data;
    });
  }

  delete(id : number) {
    this.roomService.remove(id).subscribe(result => {
      window.location.reload();
    }, error => console.error(error));
  }

}
