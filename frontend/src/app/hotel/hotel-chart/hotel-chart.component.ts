import { Component, OnInit } from '@angular/core';
import { Hotel } from 'src/app/hotel';
import { ActivatedRoute, Router } from '@angular/router';
import { HotelService } from 'src/services/hotel.service';
import { TokenStorageService } from 'src/services/auth/token-storage.service';
import { AdminsService } from 'src/services/admins.service';
import { ROLE_H } from 'src/app/globals';

@Component({
  selector: 'app-hotel-chart',
  templateUrl: './hotel-chart.component.html',
  styleUrls: ['./hotel-chart.component.scss']
})
export class HotelChartComponent implements OnInit {

  hotel: any;

  constructor(private route: ActivatedRoute, private router: Router, private adminService: AdminsService, private ts: TokenStorageService, private hotelservice : HotelService) { }


  ngOnInit() {
    
    if (!this.ts.getAuthorities().includes(ROLE_H)) {
      alert("Unauthorized");
      this.router.navigate(['/home']);
    }

    this.adminService.getAdmin(this.ts.getUser()).subscribe( r => {
      this.hotel = r.hotel;
    }, error => console.error(error));
  }

}
