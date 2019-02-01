import { Component, OnInit } from '@angular/core';
import { HotelService } from '../../../services/hotel.service';
import { ActivatedRoute, Router } from '@angular/router';
import { DomSanitizer } from '@angular/platform-browser';

@Component({
  selector: 'app-hotel-list',
  templateUrl: './hotel-list.component.html',
  styleUrls: ['./hotel-list.component.scss']
})
export class HotelListComponent implements OnInit {
  
  hotels: Array<any>;
  _finalAddress: string = "";
  _address: string = "";
  
  constructor(public sanitizer: DomSanitizer, private route: ActivatedRoute, private router: Router, private hotelService: HotelService) { }

  ngOnInit() {
    this.hotelService.getAll().subscribe(data => {
      this.hotels = data;
      
      this._address += this.hotels[1].address;
      this._address += ",";
      this._address += this.hotels[1].city;
      this._address = this._address.replace(/ /g,'%20');
      this._address = this._address.replace(",",'%2C');
      this._finalAddress = "https://maps.google.com/maps?q="+this._address+"&t=&z=13&ie=UTF8&iwloc=&output=embed";
    });
  }

  delete(id : number) {
    this.hotelService.remove(id).subscribe(result => {
      window.location.reload();
    }, error => console.error(error));
  }
}