import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { RoomService } from 'src/services/room.service';
import { HotelService } from 'src/services/hotel.service';
import { HotelExtrasService } from 'src/services/hotel-extras.service';

@Component({
  selector: 'app-extras-list',
  templateUrl: './extras-list.component.html',
  styleUrls: ['./extras-list.component.scss']
})
export class ExtrasListComponent implements OnInit {

  extras: Array<any>;
  hotel: any;

  constructor(private route: ActivatedRoute, private router: Router, 
    private extraService: HotelExtrasService, private hotelService: HotelService) { }

  ngOnInit() {
    const hotelId : number = this.route.snapshot.params['id'];
    this.hotelService.get(hotelId).subscribe(data =>{
      this.hotel = data;
    });
    this.extraService.getHotelExtras(hotelId).subscribe(data => {
      this.extras = data;
    })
  }

  delete(id: number) {
    this.extraService.delete(id).subscribe(result => {
      window.location.reload();
    }, error => console.error(error));
  }

}
