import { Component, OnInit } from '@angular/core';
import { Hotel } from 'src/app/hotel';
import { ActivatedRoute, Router } from '@angular/router';
import { HotelService } from 'src/services/hotel.service';

@Component({
  selector: 'app-hotel-chart',
  templateUrl: './hotel-chart.component.html',
  styleUrls: ['./hotel-chart.component.scss']
})
export class HotelChartComponent implements OnInit {

  hotel: any;

  constructor(private route: ActivatedRoute, private router: Router, private hotelservice : HotelService) { }


  ngOnInit() {
    const id_h = this.route.snapshot.params['id'];
    this.hotelservice.get(id_h).subscribe(data =>{
      this.hotel = data;
    });
  }

}
