import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { HotelService } from 'src/services/hotel.service';
import { AdminsService } from 'src/services/admins.service';
import { ROLE_H } from 'src/app/globals';
import { TokenStorageService } from 'src/services/auth/token-storage.service';
import { NgbDate, NgbCalendar } from '@ng-bootstrap/ng-bootstrap';
import { DatePipe } from '@angular/common';
import { SearchObject } from 'src/app/searchObject';

@Component({
  selector: 'app-hotel-revenues',
  templateUrl: './hotel-revenues.component.html',
  styleUrls: ['./hotel-revenues.component.scss']
})
export class HotelRevenuesComponent implements OnInit {

  hotel: any;

  
  date: {year: number, month: number };
  hoveredDate: NgbDate;
  fromDate: NgbDate;
  toDate: NgbDate;

  revenues: any;
  showD: boolean = false

  sObj: SearchObject = new SearchObject();

  days;

  constructor(private route: ActivatedRoute, private datePipe: DatePipe, private calendar: NgbCalendar, private adminService: AdminsService, private ts: TokenStorageService, private router: Router, private hotelservice : HotelService) { 
    this.fromDate = calendar.getToday();
    this.toDate = calendar.getNext(calendar.getToday(), 'd', 10);
  }


  ngOnInit() {

    if (!this.ts.getAuthorities().includes(ROLE_H)) {
      alert("Unauthorized");
      this.router.navigate(['/home']);
    }

    this.adminService.getAdmin(this.ts.getUser()).subscribe( r => {
      this.hotel = r.hotel;
    }, error => console.error(error));
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



  logout() {
    this.ts.signOut();
    this.router.navigate(['/login']);
  }

  go() {

    this.sObj.startD = this.fromDate.day;
    this.sObj.startM = this.fromDate.month;
    this.sObj.startY = this.fromDate.year;
    this.sObj.endD = this.toDate.day;
    this.sObj.endM = this.toDate.month;
    this.sObj.endY = this.toDate.year;

    this.hotelservice.revenues(this.sObj, this.hotel.id).subscribe( r => {
      this.revenues = r;
      this.showD = true;
    })
    
    
  }

}
