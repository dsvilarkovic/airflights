import { Component, OnDestroy, OnInit } from '@angular/core';
import { Subscription } from 'rxjs';
import { ActivatedRoute, Router } from '@angular/router';
import { HotelService } from '../../../services/hotel.service';
import { NgForm } from '@angular/forms';
import { TokenStorageService } from 'src/services/auth/token-storage.service';
import { Hotel } from 'src/app/hotel';
import { FRONT } from 'src/app/globals';

@Component({
  selector: 'app-hotel-add',
  templateUrl: './hotel-add.component.html',
  styleUrls: ['./hotel-add.component.scss']
})
export class HotelAddComponent implements OnInit, OnDestroy {
  public API = '//localhost:8080/api/';
  public HOTEL_API = this.API + 'hotel/';
  
  hotel: Hotel = new Hotel();

  sub: Subscription;
  pom: String;


  constructor(private route: ActivatedRoute, private router: Router, private hotelService: HotelService,  private token: TokenStorageService) { }

  ngOnInit() {
    //this.pom = JSON.stringify(this.token.getAuthorities());
    //alert(this.pom);
    /*this.sub = this.route.params.subscribe(params => {
      const id = params['id'];
      this.hotelService.get(id).subscribe((hotel: any) => {
        if (hotel) {
          this.hotel = hotel;
          //this.hotel.href = hotel._links.self.href;
        } else {
          console.log(`Hotel with id '${id}' not found, returning to list`);
          this.gotoList();
        }
      });
    });*/
  }

  ngOnDestroy() {
    //this.sub.unsubscribe();
  }

  gotoList() {
    this.router.navigate(['hotel/list']);
  }

  save(form: NgForm) {
    this.hotelService.save(this.hotel).subscribe(result => {
      this.gotoList();
    }, error => console.error(error));
  }

  remove(href) {
    this.hotelService.remove(href).subscribe(result => {
      this.gotoList();
    }, error => console.error(error));
  }

}
