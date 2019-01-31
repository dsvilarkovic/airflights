import { Component, OnDestroy, OnInit } from '@angular/core';
import { Subscription } from 'rxjs';
import { ActivatedRoute, Router } from '@angular/router';
import { HotelService } from '../../../services/hotel.service';
import { NgForm } from '@angular/forms';
import { Hotel } from 'src/app/hotel';

@Component({
  selector: 'app-hotel-edit',
  templateUrl: './hotel-edit.component.html',
  styleUrls: ['./hotel-edit.component.scss']
})
export class HotelEditComponent implements OnInit, OnDestroy {
  hotel: any = {};
  hotelName : string;
  sub: Subscription;

  constructor(private route: ActivatedRoute, private router: Router, private hotelService: HotelService) { }

  ngOnInit() {
    const id = this.route.snapshot.params['id'];
    this.hotelService.get(id).subscribe(data =>{
      this.hotel = data;
      this.hotelName = this.hotel.name;
    });
    /*this.sub = this.route.params.subscribe(params => {
      const id = params['id'];
      if (id) {
        this.hotelService.get(id).subscribe((hotel: any) => {
          if (hotel) {
            this.hotel = hotel;
            //this.hotel.href = hotel._links.self.href;
          } else {
            console.log(`Hotel with id '${id}' not found, returning to list`);
            this.gotoList();
          }
        });
      }
    });*/
  }

  ngOnDestroy() {
    //this.sub.unsubscribe();
  }

  gotoList() {
    this.router.navigate(['/hotel/list']);
  }

  update() {
    this.hotelService.update(this.hotel).subscribe(result => {
      //this.gotoList();
      window.location.reload();
    }, error => console.error(error));
  }

  remove(href) {
    this.hotelService.remove(href).subscribe(result => {
      this.gotoList();
    }, error => console.error(error));
  }

}
