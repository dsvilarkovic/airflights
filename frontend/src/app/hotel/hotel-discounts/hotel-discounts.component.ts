import { Component, OnInit } from '@angular/core';
import { TokenStorageService } from 'src/services/auth/token-storage.service';
import { ActivatedRoute, Router } from '@angular/router';
import { AdminsService } from 'src/services/admins.service';
import { HotelService } from 'src/services/hotel.service';
import { ROLE_H } from 'src/app/globals';
import { Misc } from 'src/app/misc';
import { BaseRequestOptions } from '@angular/http';

@Component({
  selector: 'app-hotel-discounts',
  templateUrl: './hotel-discounts.component.html',
  styleUrls: ['./hotel-discounts.component.scss']
})
export class HotelDiscountsComponent implements OnInit {

  hotel: any = {};

  h: Misc = new Misc();
  m1: Misc = new Misc();
  m2: Misc = new Misc();

  constructor(private ts: TokenStorageService, private route: ActivatedRoute, private adminService: AdminsService, private router: Router, private hotelService: HotelService) { }

  ngOnInit() {
    if (!this.ts.getAuthorities().includes(ROLE_H)) {
      alert("Unauthorized");
      this.router.navigate(['/home']);
    }

    this.adminService.getAdmin(this.ts.getUser()).subscribe( r => {
      this.hotel = r.hotel;
    }, error => console.error(error));

    this.adminService.getMiscAll().subscribe( r => {
      r.forEach(element => {
       switch(element.id) {
         case 2:
          this.m1.id = element.id
          this.m1.b = element.b
          this.m1.bb = element.bb
          this.m1.bbb = element.bbb
          this.h.b = this.m1.b
          this.h.bb = this.m1.bb
          this.h.bbb = this.m1.bbb
         break;
         case 3:
          this.m2.id = element.id
          this.m2.b = element.b
          this.m2.bb = element.bb
          this.m2.bbb = element.bbb
         break;

       } 
      });

    }) 

  }

  logout() {
    this.ts.signOut();
    this.router.navigate(['/login']);
  }

  save1() {

    if (this.m1.b > 0) {

    } else {
      alert(1)
    }

    if (this.m1.bb >= this.m1.b) {

    } else {
      alert(2)
    }

    if (this.m1.bbb >= this.m1.bb) {

    } else {
      alert(3)
    }

    if (this.m1.b > 0 &&
      this.m1.bb >= this.m1.b && this.m1.bbb >= this.m1.bb) {
        this.adminService.upMisc(this.m1).subscribe( r => {
          window.location.reload()
        })
    } else {
      alert("Invalid sequence!")
    } 
  }

  save2() {
    if (this.m2.b && this.m2.bb && this.m2.bbb && this.m2.b > 0 &&
      this.m2.bb >= this.m2.b && this.m2.bbb >= this.m2.bb) {

          this.adminService.upMisc(this.m2).subscribe( r => {
            window.location.reload()
        });
      } else {
        alert("Invalid sequence!")
      }

  }

}
