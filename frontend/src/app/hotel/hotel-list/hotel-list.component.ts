import { Component, OnInit } from '@angular/core';
import { HotelService } from '../../../services/hotel.service';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-hotel-list',
  templateUrl: './hotel-list.component.html',
  styleUrls: ['./hotel-list.component.scss']
})
export class HotelListComponent implements OnInit {
  
  hotels: Array<any>;

  constructor(private route: ActivatedRoute, private router: Router, private hotelService: HotelService) { }

  ngOnInit() {
    this.hotelService.getAll().subscribe(data => {
      this.hotels = data;
    });
  }

  delete(id : number) {
    this.hotelService.remove(id).subscribe(result => {
      window.location.reload();
    }, error => console.error(error));
  }
}