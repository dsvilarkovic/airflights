import { Component, OnInit } from '@angular/core';
import { NgbDate, NgbCalendar } from '@ng-bootstrap/ng-bootstrap';
import { SearchObject } from 'src/app/searchObject';
import { DomSanitizer } from '@angular/platform-browser';
import { ActivatedRoute, Router } from '@angular/router';
import { HotelService } from 'src/services/hotel.service';
import { DatePipe } from '@angular/common';
import { TokenStorageService } from 'src/services/auth/token-storage.service';
import { HotelExtrasService } from 'src/services/hotel-extras.service';
import { Options } from 'ng5-slider';
import { Condition } from 'src/app/condition';
import { isNumeric } from "rxjs/util/isNumeric"
import { RoomService } from 'src/services/room.service';
import { ROLE_USER } from 'src/app/globals';


@Component({
  selector: 'app-hotel-profile',
  templateUrl: './hotel-profile.component.html',
  styleUrls: ['./hotel-profile.component.scss']
})
export class HotelProfileComponent implements OnInit {

  value: number = 0;
  highValue: number = 0;
  options: Options = {
    floor: 0,
    ceil: 1000
  };

  days: number;
  daysP: number;
  days0: number;
  persons: number;
  cons: Array< Condition> = new Array<Condition>();

  hotel: any;
  _finalAddress: string = "";
  date: {year: number, month: number};
  date2: {year: number, month: number};
  fromDate: NgbDate;
  fromDateP: NgbDate;
  toDate: NgbDate;
  toDateP: NgbDate;
  //date picker
  hoveredDate: NgbDate;
  hoveredDate2: NgbDate;
  asd: string;
  extras: Array<any>
  rooms: Array<any>
  pRooms: Array<any>
  noR: number;
  noB: number;
  pf: boolean;
  logged: boolean;

  sObj: SearchObject = new SearchObject();

  constructor(public sanitizer: DomSanitizer, private route: ActivatedRoute, private router: Router, private hotelService: HotelService
    , private datePipe: DatePipe, private extrasService: HotelExtrasService, private rService: RoomService,
    private ts: TokenStorageService,
    private calendar: NgbCalendar) {
      this.fromDate = calendar.getToday();
      this.fromDateP = calendar.getToday();
      //this.toDate = calendar.getNext(calendar.getToday(), 'd', 10);
      this.sObj.location = "";
      this.sObj.name = "";
    }

  ngOnInit() {

    if (!this.ts.getAuthorities().includes(ROLE_USER)) {
      //alert("Not signed in");
      this.logged = false;
    } else {
      this.logged = true;
    }

    const id = this.route.snapshot.params['id'];
    this.hotelService.get(id).subscribe( r => {
      this.hotel = r;
      let address = this.hotel.address;
      address += ",";
      address += this.hotel.city;
      address = address.replace(/ /g,'%20');
      address = address.replace(",",'%2C');
      this._finalAddress = "https://maps.google.com/maps?q="+address+"&t=&z=13&ie=UTF8&iwloc=&output=embed";
    }, e => console.error(e));

    this.extrasService.getHotelExtras(id).subscribe( r => {
      this.extras = r
      this.extras.forEach(element => {
        element.unit = this.extrasService.convert(element.unit)
      });
    }, e => console.error(e))

    // this.rService.getRoomsInHotel(id).subscribe( r => {
    //   this.rooms = r;
    // }, e => console.error(e))

  }

  onDateSelection(date: NgbDate) {
    /*if (!this.fromDate && !this.toDate) {
      this.fromDate = date;
    } else if (this.fromDate && !this.toDate && date.after(this.fromDate)) {
      this.toDate = date;
    } else {
      this.toDate = null;
      this.fromDate = date;
    }*/
    //alert(date.day)
    this.fromDate = date;
  }

  onDateSelection2(date2: NgbDate) {
    //alert(2)
    this.fromDateP = date2;
  }

  isHovered2(date: NgbDate) {
    //alert(3)
    return this.fromDateP && !this.toDateP && this.hoveredDate && date.after(this.fromDateP) && date.before(this.hoveredDate);
  }

  isInside2(date: NgbDate) {
    //alert(4)
    return date.after(this.fromDateP) && date.before(this.toDateP);
  }

  isRange2(date: NgbDate) {
    //alert(5)
    return date.equals(this.fromDateP) || date.equals(this.toDateP) || this.isInside(date) || this.isHovered(date);
  }

  isHovered(date: NgbDate) {
    //alert(6)
    return this.fromDate && !this.toDate && this.hoveredDate && date.after(this.fromDate) && date.before(this.hoveredDate);
  }

  isInside(date: NgbDate) {
    //alert(7)
    return date.after(this.fromDate) && date.before(this.toDate);
  }

  isRange(date: NgbDate) {
    //alert(8)
    return date.equals(this.fromDate) || date.equals(this.toDate) || this.isInside(date) || this.isHovered(date);
  }

  reset() {
    this.ngOnInit();
  }

  addCon() {
    if (isNumeric(this.noR) && this.noR > 0 && isNumeric(this.noB) && this.noB > 0 ) {
      let c : Condition = new Condition()
      c.nr = this.noR;
      c.nb = this.noB;
      var b = true;
      this.cons.forEach(element => {
        if (element.nb == c.nb) {
          b = false;
          element.nr += c.nr;
        }
      });

      if (b) {
        this.cons.push(c)
      }

    } else {
      alert("Numbers of rooms and beds must be positive integers!")
    }
  }

  search() {
    if (isNumeric(this.days) && this.days > 0) {

      if (this.cons.length > 0) {
        let sum = 0;
        this.cons.forEach(element => {
          sum += element.nr * element.nb
        });

        if (sum < this.sObj.persons) {
          alert("Number of beds is smaller than number of persons!")
        }
      }




      let d : Date = new Date()
      d.setFullYear(this.fromDate.year, this.fromDate.month, this.fromDate.day)
      let d2 : Date = new Date()

      d2.setDate( d.getDate() + this.days );

      this.sObj.startD = this.fromDate.day
      this.sObj.startM = this.fromDate.month
      this.sObj.startY = this.fromDate.year
      
      this.sObj.endD = d2.getDate()
      this.sObj.endM = d2.getMonth() + 1
      this.sObj.endY = d2.getFullYear()

      this.sObj.conditions = this.cons

      this.days0 = this.days

      
      if (this.pf) {
        this.sObj.pf = this.value
        this.sObj.pt = this.highValue
      }

      this.rService.getSearchRooms(this.hotel.id, this.sObj).subscribe( r => {
        this.pRooms = null;
        this.rooms = r;
      }, error => alert(error))

    } else {
      alert("Please enter number of days")
    }
  }

  book() {

  }

  promos() {
    if (isNumeric(this.daysP) && this.daysP > 0) {
      let d : Date = new Date()
      d.setFullYear(this.fromDate.year, this.fromDate.month, this.fromDate.day)
      let d2 : Date = new Date()

      d2.setDate( d.getDate() + this.daysP );

      this.sObj.startD = this.fromDate.day
      this.sObj.startM = this.fromDate.month
      this.sObj.startY = this.fromDate.year
      
      this.sObj.endD = d2.getDate()
      this.sObj.endM = d2.getMonth() + 1
      this.sObj.endY = d2.getFullYear()

      this.rService.getPromoRooms(this.hotel.id, this.sObj).subscribe( r => {
        this.pRooms = r;
        this.rooms = null;
      }, error => console.error(error))

    } else {
      alert("Number of days must be positive integer!")
    }
  }

  login() {
    this.router.navigate(['/login'])
  }

  reserve() {

  }

}
