import { Component, OnDestroy, OnInit } from '@angular/core';
import { Subscription } from 'rxjs';
import { ActivatedRoute, Router } from '@angular/router';
import { HotelService } from '../../../services/hotel.service';
import { AdminsService } from 'src/services/admins.service';
import { ROLE_H } from 'src/app/globals';
import { TokenStorageService } from 'src/services/auth/token-storage.service';

@Component({
  selector: 'app-hotel-edit',
  templateUrl: './hotel-edit.component.html',
  styleUrls: ['./hotel-edit.component.scss']
})
export class HotelEditComponent implements OnInit {
  hotel: any = {};
  hotelName : string;
  sub: Subscription;

  constructor(private ts: TokenStorageService, private route: ActivatedRoute, private adminService: AdminsService, private router: Router, private hotelService: HotelService) { }

  ngOnInit() {

    if (!this.ts.getAuthorities().includes(ROLE_H)) {
      alert("Unauthorized");
      this.router.navigate(['/home']);
    }

    this.adminService.getAdmin(this.ts.getUser()).subscribe( r => {
      this.hotel = r.hotel;
      this.hotelName = this.hotel.name;
      //alert(this.hotel)
    }, error => console.error(error));

  }

  gotoList() {
    this.router.navigate(['/hotel/list']);
  }

  update() {
    this.hotelService.update(this.hotel).subscribe(result => {
      //this.gotoList();
      window.location.reload();
    }, error => alert("Somebody update hotel, please reload"));
  }

  remove(href) {
    this.hotelService.remove(href).subscribe(result => {
      this.gotoList();
    }, error => console.error(error));
  }

  logout() {
    this.ts.signOut();
    this.router.navigate(['/login']);
  }

}
