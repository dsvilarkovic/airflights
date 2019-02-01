import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { HotelService } from 'src/services/hotel.service';
import { AdminsService } from 'src/services/admins.service';
import { ROLE_H } from 'src/app/globals';
import { TokenStorageService } from 'src/services/auth/token-storage.service';

@Component({
  selector: 'app-hotel-revenues',
  templateUrl: './hotel-revenues.component.html',
  styleUrls: ['./hotel-revenues.component.scss']
})
export class HotelRevenuesComponent implements OnInit {

  hotel: any;

  constructor(private route: ActivatedRoute, private adminService: AdminsService, private ts: TokenStorageService, private router: Router, private hotelservice : HotelService) { }


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
