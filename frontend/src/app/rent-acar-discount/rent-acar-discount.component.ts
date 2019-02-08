import { DatePipe } from '@angular/common';
import { LoginService } from './../../services/login.service';
import { User } from './../user';
import { VehicleReservation } from './../vehicleReservation';
import { FormControl } from '@angular/forms';
import { DomSanitizer } from '@angular/platform-browser';
import { Vehicle } from './../vehicle';
import { Component, OnInit } from '@angular/core';
import { RentacarService } from 'src/services/rentacar.service';
import { Route } from '@angular/compiler/src/core';
import { Router, ActivatedRoute } from '@angular/router';
import { rentacar } from '../rentacar';
import { Branch } from '../branch';
import { Moment } from 'moment';
import * as moment from 'moment';
import { ReservationServiceService } from 'src/services/reservation-service.service';
import { TokenStorageService } from 'src/services/auth/token-storage.service';
import { NgbCalendar, NgbDate } from '@ng-bootstrap/ng-bootstrap';
import { isNumeric } from 'rxjs/util/isNumeric';

@Component({
  selector: 'app-rent-acar-discount',
  templateUrl: './rent-acar-discount.component.html',
  styleUrls: ['./rent-acar-discount.component.scss']
})
export class RentACarDiscountComponent implements OnInit {

  id;
  vehicles : Array<Vehicle>;
  vehicles2 :Vehicle[] = [];
  vehicles3 :Vehicle[] = [];
  rent: rentacar = new rentacar();
  _address: string = "";
  _finalAddress: string = "";
  city:string = "";

  daysP: number;
  date: {year: number, month: number};
  date2: {year: number, month: number};

  fromDate: NgbDate;
  fromDateP: NgbDate;
  toDate: NgbDate;
  toDateP: NgbDate;
  //date picker
  hoveredDate: NgbDate;
  hoveredDate2: NgbDate;

  selectedVehicle: Vehicle = new Vehicle;
  branches : Array<Branch>
  branches2: Branch[] = [];
  pickuploc = new FormControl("");
  dropoffloc = new FormControl("");
  reserv : VehicleReservation = new VehicleReservation();
  currentUser: User = new User();
  days;

  boolLog: boolean = false;
  boolLogOff: boolean = false;


  constructor(private racService: RentacarService, 
    private loginService: LoginService,
    private resServise: ReservationServiceService,
    public sanitizer: DomSanitizer, private route: ActivatedRoute, private router: Router
    , private datePipe: DatePipe,
    private ts: TokenStorageService,
    private calendar: NgbCalendar
    ) {
      this.fromDate = calendar.getToday();
      this.fromDateP = calendar.getToday();
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

  ngOnInit() {
    
    this.id = this.route.snapshot.params.id;
    //this.date1 = this.route.snapshot.params.discount;
   // this.date2 = this.route.snapshot.params.date2;

    //alert(this.date1 + " " + this.date2);

    this.racService.getOne(this.id).subscribe(data => {
      this.rent = data;
      //alert("DAFLDKJAF " + this.rent.city);
      this.city += this.rent.name;
      this._address += this.rent.address;
      this._address += " ";
      this._address += this.rent.address.replace(/ /g,'%20');
      this._finalAddress += "https://maps.google.com/maps?q="+this._address+"&t=&z=13&ie=UTF8&iwloc=&output=embed";


         // alert("IDAFJD " + this.rent.address);
         // alert("IDAFJD " + this.rent.city);
          this.racService.getAllDiscount(this.id,this.rent.city).subscribe(data => {
          this.vehicles = data;
          for(let v of this.vehicles) {
            this.vehicles2.push(v);
          }

          this.racService.getAllBranches().subscribe(data => {
            this.branches = data;
            for(let b of this.branches) {
              this.branches2.push(b);
            }
          })
      
        })

        /*this.resServise.checkDate(this.date1,this.date2,this.id).subscribe(data => {
          this.vehicles = data;
          for(let ve of this.vehicles) {
            if(ve.discount != 0) {
              alert("Jesu na popustu");
              this.vehicles3.push(ve);
            } else {
              alert("Nisu na popustu");
            }
           
          }

        })/*/

    })

    if(sessionStorage.getItem("AuthUsername") == null) {
     // alert("Niko nije ulogovan!");
      this.boolLog = true;
      this.boolLogOff = false;
     
    } else {
    this.loginService.getLogged(sessionStorage.getItem("AuthUsername")).subscribe(data => 
        {
        this.currentUser = data;
        //alert("User: " + this.currentUser.firstName);
        this.boolLog = false;
        this.boolLogOff = true;
        
        });
      }


   

  }


 
  btnBook(ve) {

    for(let v of this.vehicles2) {
      if(v.id == ve) {
        this.selectedVehicle = v;
        break;
      }
    }

    
    //alert("Usao " + this.selectedVehicle.branchOffice_id);

    for(let b of this.branches2) {
      if(b.id == this.selectedVehicle.branchOffice_id) {
        //alert("Usao")
        this.reserv.pickuplocation = b.address + " " + b.city;
        this.reserv.dropofflocation = b.address + " " + b.city;
      }

    }

    this.reserv.pickupdate = this.fromDate.year + "-" + this.fromDate.month + "-" + this.fromDate.day;
    this.reserv.dropoffdate = this.toDate.year + "-" + this.toDate.month + "-" + this.toDate.day;

    this.reserv.reservationdate = this.datePipe.transform(new Date(), 'yyyy-MM-dd');

    //alert("ADLFKJD " + this.reserv.reservationdate);

    this.reserv.vehicle = this.selectedVehicle;
    this.reserv.user_id = this.currentUser.id;

    //za ukupnu cenu cu posle izracunati 
    var now = moment(this.reserv.dropoffdate); 
    var end = moment(this.reserv.pickupdate); 
    var duration = moment.duration(now.diff(end));
    this.days = duration.asDays();
    alert("Cena:  " + duration);
    alert("Cena:  " + this.days);
    alert("Cena:  " + this.selectedVehicle.price);

    this.reserv.price = this.days *(this.selectedVehicle.price - this.selectedVehicle.price * this.selectedVehicle.discount );
    alert("Cena:  " + this.reserv.price);

    this.resServise.book(this.id,this.reserv).subscribe(data => {
      alert("Rezervisano! ");
    })

  }


  


  //days: number;

  promos() {
    
      this.reserv.pickupdate = this.fromDate.year + "-" + this.fromDate.month + "-" + this.fromDate.day;
      this.reserv.dropoffdate = this.toDate.year + "-" + this.fromDate.month + "-" + this.fromDate.day;

      alert("DAni su bez broja : " + this.reserv.pickupdate);
      alert("DAni su bez broja : " + this.reserv.dropoffdate);

      this.resServise.checkDate(this.reserv.pickupdate,this.reserv.dropoffdate,this.id).subscribe(data => {
        this.vehicles = data;
        for(let ve of this.vehicles) {
          if(ve.discount != 0) {
            alert("Jesu na popustu");
            this.vehicles3.push(ve);
          } else {
            alert("Nisu na popustu");
          }
         
        }

      })
    }


    logout() {
      this.ts.signOut();
      this.router.navigate(['/login']);
    }

    login() {
      this.ts.signOut();
      this.router.navigate(['/login']);
    }

 

}
