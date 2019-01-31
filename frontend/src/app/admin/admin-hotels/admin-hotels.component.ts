import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { HotelService } from 'src/services/hotel.service';

@Component({
  selector: 'app-admin-hotels',
  templateUrl: './admin-hotels.component.html',
  styleUrls: ['./admin-hotels.component.scss']
})
export class AdminHotelsComponent implements OnInit {

  hotels: Array<any>;

  constructor(private route: ActivatedRoute, private router: Router, private hotelService: HotelService) { }

  ngOnInit() {
    this.hotelService.getAll().subscribe(data => {
      this.hotels = data;
    }, error => console.error(error));
  }

}
