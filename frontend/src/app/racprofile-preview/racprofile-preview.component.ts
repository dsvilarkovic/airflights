import { TokenStorageService } from 'src/services/auth/token-storage.service';
import { VehicleType } from './../../vehicleType';
import { User } from './../user';
import { LoginService } from './../../services/login.service';
import { VehicleReservation } from './../vehicleReservation';
import { FormControl } from '@angular/forms';
import { Vehicle } from './../vehicle';
import { Component, OnInit, ModuleWithComponentFactories } from '@angular/core';
import {NgbDateStruct, NgbCalendar, NgbDate} from '@ng-bootstrap/ng-bootstrap'
import { RentacarService } from 'src/services/rentacar.service';
import { Route, Router, ActivatedRoute } from '@angular/router';
import { DatePipe } from '@angular/common';
import { Branch } from '../branch';
import { ReservationServiceService } from 'src/services/reservation-service.service';
import { Moment } from 'moment';
import * as moment from 'moment';
import { DomSanitizer } from '@angular/platform-browser';
import { rentacar } from '../rentacar';



@Component({
  selector: 'app-racprofile-preview',
  templateUrl: './racprofile-preview.component.html',
  styleUrls: ['./racprofile-preview.component.scss']
})
export class RacprofilePreviewComponent implements OnInit {

  id; //id rent a car-a
  modelPickUp: NgbDateStruct;
  modelDropOff: NgbDateStruct;
  vehicles : Array<Vehicle>;
  vehicles2 :Vehicle[] = [];
  vehicles3 :Vehicle[] = [];
  vehicles4 :Vehicle[] = [];
  branches : Array<Branch>
  branches2: Branch[] = [];
  date: {year: number, month: number};
  noreservationbutton: boolean = false;
  pickuploc = new FormControl("");
  dropoffloc = new FormControl("");
  filterFlag: boolean = false;


  //stringovi
  pickuplocStr: string;
  //filter za pretragu
  filter: Vehicle = new Vehicle();

  //trazena filijala
  branch: Branch = new Branch();

  //rezervacija
  reserv : VehicleReservation = new VehicleReservation();

  //lista rezervacija koje kupim oninit
  reservations : Array<VehicleReservation>;
  reservations2: VehicleReservation [] = [];

  //oznaceno vozilo za booking
  selectedVehicle: Vehicle = new Vehicle;

  //trenutno ulogovani user
  currentUser: User = new User();
  
  //succes reservatio
  succesRes: VehicleReservation = new VehicleReservation();

  //broj dana izmedju dva datuma
  days;

  //final address
  _finalAddress: string = "";
  _address: string = "";

  //pom rent a car
  rent: rentacar = new rentacar();

  //informacija da li je korisnik ulogovan, prikaz book mu se nece prikazati (moze samo da gleda)
  loggedFlag: boolean = false;

  //lista za search
  reservationsSearch : Array<VehicleReservation>;
  reservationsSearch2: VehicleReservation [] = [];

  //za datum:
  day1: number;
  day2: number;
  mon1: number;
  mon2: number;
  year1: number;
  year2: number;

  //za price range
  rangerFrom :number = 0;
  rangerTo:number = 999;
  //sedista
  seats:number;

  //tipovi
  types: VehicleType;
  typess:string[] = ["SMALL_CARS", "MEDIUM_CARS", "LARGE_CARS", "PREMIUM_CARS"];


  constructor(private racService: RentacarService, private router: Router,
    private route : ActivatedRoute, private datePipe: DatePipe,
    private resServise: ReservationServiceService,
    private loginService: LoginService,
    public sanitizer: DomSanitizer,
    private token: TokenStorageService,
    private calendar: NgbCalendar) {
      this.fromDate = calendar.getToday();
      this.toDate = calendar.getNext(calendar.getToday(), 'd', 10);
     }

  ngOnInit() {
    this.vehicles2 = [];
    this.branches2 = [];
    this.loggedFlag = false;
    this.id = this.route.snapshot.params.id;
    this.racService.getOne(this.id).subscribe(data => {
      this.rent = data;
      this._address += this.rent.address;
      this._address += " ";
      this._address += this.rent.address.replace(/ /g,'%20');
      this._finalAddress += "https://maps.google.com/maps?q="+this._address+"&t=&z=13&ie=UTF8&iwloc=&output=embed";
    })

   
    this.resServise.getAllById(this.id).subscribe(data => {
      this.reservationsSearch = data;
      for(let v of this.reservationsSearch) {
        this.reservationsSearch2.push(v);
    }
    })

  
    this.racService.getAllById(this.id).subscribe(data => {
      this.vehicles=data;
      for(let v of this.vehicles) {
          this.vehicles2.push(v);
      }
    })

    this.racService.getAllBranches().subscribe(data3 => {
      this.branches = data3;
      for(let b of this.branches) {
        if(this.id == b.rentACarId) {
          this.branches2.push(b);
        }
      }
    })

    this.resServise.getAllById(this.id).subscribe(data => {
      this.reservations = data;
      for(let r of this.reservations){
        this.reservations2.push(r);
      }
    })

    if(sessionStorage.getItem("AuthUsername") == null) {
      alert("Niko nije ulogovan!");
      this.loggedFlag = false;
    } else {
    this.loginService.getLogged(sessionStorage.getItem("AuthUsername")).subscribe(data => 
        {
        this.currentUser = data;
        alert("User: " + this.currentUser.firstName);
        this.loggedFlag = true;
        });
      }

  }

  openFilter() {
    this.filterFlag = true;
  }

  closeFilter() {
    this.filterFlag = false;
  }

  
  


//date picker
  hoveredDate: NgbDate;

  fromDate: NgbDate;
  toDate: NgbDate;

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

  search() {
    this.vehicles2 = [];
    this.vehicles3 = [];
    alert("Type? " + this.types);
    
    this.reserv.pickupdate = this.fromDate.year + "-" + this.fromDate.month + "-" + this.fromDate.day;
    this.reserv.dropoffdate = this.toDate.year + "-" + this.toDate.month + "-" + this.toDate.day;

    this.resServise.checkDate(this.reserv.pickupdate,this.reserv.dropoffdate,this.id).subscribe(data => {
      this.vehicles = data;
      for(let ve of this.vehicles) {
        this.vehicles3.push(ve);
      }
          //to implement validation of reservation for date range!
        //u reservationSearh ce biti svi slobodni
      for(let v of this.vehicles3) {
        if(v.branchOffice_id == this.pickuploc.value && v.type == this.types && v.seats == this.seats && v.price < this.rangerTo && v.price > this.rangerFrom) {
          this.vehicles2.push(v);
        }
      }

    })

   
  }

 
  

  reset() {
    this.ngOnInit();
  }


  //parametar id je id vozila koje korisnik bukira
  btnBook(ve) {
    
    
    for(let v of this.vehicles2) {
      if(v.id == ve) {
        this.selectedVehicle = v;
        break;
      }
    }

    for(let b of this.branches2) {
      if(b.id == this.pickuploc.value) {
        this.reserv.pickuplocation = b.address + " " + b.city;
      }
      if(b.id == this.dropoffloc.value) {
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
  /*  alert("Cena:  " + duration);
    alert("Cena:  " + this.days);
    alert("Cena:  " + this.selectedVehicle.price);*/

    this.reserv.price = this.days * this.selectedVehicle.price;
    alert("Cena:  " + this.reserv.price);

    this.resServise.book(this.id,this.reserv).subscribe(data => {
      alert("Rezervisano! ");
    })
    

  }

  logout() {
    this.token.signOut();
    this.router.navigate(['/login']);
  }

}

