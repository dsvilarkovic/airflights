import { Component, OnInit } from '@angular/core';
import { TokenStorageService } from 'src/services/auth/token-storage.service';
import { AdminsService } from 'src/services/admins.service';
import { HotelService } from 'src/services/hotel.service';
import { ROLE_H } from 'src/app/globals';
import { Location, NgForOf } from '@angular/common';
import { Router } from '@angular/router';
import { NgForm } from '@angular/forms';

@Component({
  selector: 'app-admin-hotel-profile',
  templateUrl: './admin-hotel-profile.component.html',
  styleUrls: ['./admin-hotel-profile.component.scss']
})
export class AdminHotelProfileComponent implements OnInit {

  hotel: any;
  admin: any;
  passwordNew1 :string;
  passwordNew2 :string;
  pm2: boolean;
  pm: boolean;

  constructor(private ts: TokenStorageService, private adminService: AdminsService,
    private hService: HotelService, private router: Router) { }

  ngOnInit() {
    if (!this.ts.getAuthorities().includes(ROLE_H)) {
      alert("Unauthorized");
      this.router.navigate(['/home']);
    }

    this.adminService.getAdmin(this.ts.getUser()).subscribe( r => {
      this.admin = r;
      this.hotel = r.hotel;
    }, error => console.error(error));

  }

  update() {
    this.adminService.updateAdminProfile(this.admin).subscribe(r => {
      window.location.reload();
    }, e => console.error(e));
  } 

  logout() {
    this.ts.signOut();
    this.router.navigate(['/login']);
  }

  focusn() {
    this.pm = false;
    this.pm2 = false;
  }

  changeP(f: NgForm) {

    if (this.passwordNew1!=this.passwordNew2) {
      this.pm2 = true;
      return;
    }

    this.admin.newPassword = this.passwordNew1;
    this.adminService.updatePass(this.admin).subscribe(r => {
      window.location.reload();
    }, e => console.error(e));

  }

}
