import { Component, OnInit } from '@angular/core';
import { HotelService } from '../../../services/hotel.service';
import { ActivatedRoute, Router } from '@angular/router';
import { DomSanitizer } from '@angular/platform-browser';
import { Hotel } from 'src/app/hotel';
import { DatePipe } from '@angular/common';
import { TokenStorageService } from 'src/services/auth/token-storage.service';
import { NgbCalendar, NgbDate } from '@ng-bootstrap/ng-bootstrap';
import { SearchObject } from 'src/app/searchObject';

@Component({
  selector: 'app-hotel-list',
  templateUrl: './hotel-list.component.html',
  styleUrls: ['./hotel-list.component.scss']
})
export class HotelListComponent implements OnInit {
  
  hotels: Array<any>;
  hs: Hotel = new Hotel();
  _finalAddress: string = "";
  _address: string = "";
  asd: string = "";
  date: {year: number, month: number};
  fromDate: NgbDate;
  toDate: NgbDate;
  //date picker
  hoveredDate: NgbDate;

  sObj: SearchObject = new SearchObject();

  //broj dana izmedju dva datuma
  days;
  
  constructor(public sanitizer: DomSanitizer, private route: ActivatedRoute, private router: Router, private hotelService: HotelService
    , private datePipe: DatePipe,
    private token: TokenStorageService,
    private calendar: NgbCalendar) {
      this.fromDate = calendar.getToday();
      this.toDate = calendar.getNext(calendar.getToday(), 'd', 10);
      this.sObj.location = "";
      this.sObj.name = "";
     }

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

  search() {
    if (!this.fromDate) {
      alert("Please enter date range");
      return;
    }

    if (!this.toDate) {
      alert("Please enter date range");
      return;
    }

    this.sObj.startD = this.fromDate.day;
    this.sObj.startM = this.fromDate.month;
    this.sObj.startY = this.fromDate.year;
    this.sObj.endD = this.toDate.day;
    this.sObj.endM = this.toDate.month;
    this.sObj.endY = this.toDate.year;

    this.hotelService.search(this.sObj).subscribe(r => {
      this.hotels = r;
    }, e => console.error(e));
    
  }

  onDateSelection(date: NgbDate) {
    if (!this.fromDate && !this.toDate) {
      this.fromDate = date;
    } else if (this.fromDate && !this.toDate && date.after(this.fromDate)) {
      this.toDate = date;
    } else {
      this.toDate = null;
      this.fromDate = date;
    }
  }

  isHovered(date: NgbDate) {
    return this.fromDate && !this.toDate && this.hoveredDate && date.after(this.fromDate) && date.before(this.hoveredDate);
  }

  isInside(date: NgbDate) {
    return date.after(this.fromDate) && date.before(this.toDate);
  }

  isRange(date: NgbDate) {
    return date.equals(this.fromDate) || date.equals(this.toDate) || this.isInside(date) || this.isHovered(date);
  }

  reset() {
    this.ngOnInit();
  }

  details(id: number) {
    this.router.navigate(['/hotel/' + id + '/profile'])
  }
}