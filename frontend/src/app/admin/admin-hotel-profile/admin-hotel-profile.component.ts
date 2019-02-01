import { Component, OnInit } from '@angular/core';
import { TokenStorageService } from 'src/services/auth/token-storage.service';
import { AdminsService } from 'src/services/admins.service';
import { HotelService } from 'src/services/hotel.service';
import { ROLE_H } from 'src/app/globals';
import { Location } from '@angular/common';
import { Router } from '@angular/router';

@Component({
  selector: 'app-admin-hotel-profile',
  templateUrl: './admin-hotel-profile.component.html',
  styleUrls: ['./admin-hotel-profile.component.scss']
})
export class AdminHotelProfileComponent implements OnInit {

  hotel: any;

  constructor(private ts: TokenStorageService, private adminService: AdminsService,
    private hService: HotelService, private router: Router) { }

  ngOnInit() {
    if (!this.ts.getAuthorities().includes(ROLE_H)) {
      alert("Unauthorized");
      this.router.navigate(['/home']);
    }

    this.adminService.getAdmin(this.ts.getUser()).subscribe( r => {
      this.hotel = r.hotel;
    }, error => console.error(error));

  }

  update() {

  }

}
