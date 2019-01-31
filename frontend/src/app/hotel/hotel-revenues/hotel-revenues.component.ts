import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { HotelService } from 'src/services/hotel.service';

@Component({
  selector: 'app-hotel-revenues',
  templateUrl: './hotel-revenues.component.html',
  styleUrls: ['./hotel-revenues.component.scss']
})
export class HotelRevenuesComponent implements OnInit {

  hotel: any;

  constructor(private route: ActivatedRoute, private router: Router, private hotelservice : HotelService) { }


  ngOnInit() {
    const id_h = this.route.snapshot.params['id'];
    this.hotelservice.get(id_h).subscribe(data =>{
      this.hotel = data;
    });
  }

}
