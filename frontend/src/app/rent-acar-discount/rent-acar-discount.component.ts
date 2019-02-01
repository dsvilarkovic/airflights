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
  date1: string;
  date2: string;


  constructor(private racService: RentacarService, 
    private route: ActivatedRoute,
    public sanitizer: DomSanitizer,
    private loginService: LoginService,
    private resServise: ReservationServiceService,
    private datePipe: DatePipe) { }

  ngOnInit() {

    this.id = this.route.snapshot.params.id;
    this.date1 = this.route.snapshot.params.date1;
    this.date2 = this.route.snapshot.params.date2;

    alert(this.date1 + " " + this.date2);

    this.racService.getOne(this.id).subscribe(data => {
      this.rent = data;
      alert("DAFLDKJAF " + this.rent.city);
      this.city += this.rent.city;
      this._address += this.rent.address;
      this._address += " ";
      this._address += this.rent.address.replace(/ /g,'%20');
      this._finalAddress += "https://maps.google.com/maps?q="+this._address+"&t=&z=13&ie=UTF8&iwloc=&output=embed";


          alert("IDAFJD " + this.rent.address);
          alert("IDAFJD " + this.rent.city);
          this.racService.getAllDiscount(this.id,this.rent.city).subscribe(data => {
          this.vehicles = data;
          for(let v of this.vehicles) {
            this.vehicles2.push(v);
          }
        })

        this.resServise.checkDate(this.date1,this.date2,this.id).subscribe(data => {
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

    })

    if(sessionStorage.getItem("AuthUsername") == null) {
      alert("Niko nije ulogovan!");
     
    } else {
    this.loginService.getLogged(sessionStorage.getItem("AuthUsername")).subscribe(data => 
        {
        this.currentUser = data;
        alert("User: " + this.currentUser.firstName);
        
        });
      }


   

  }


  selectedVehicle: Vehicle = new Vehicle;
  branches : Array<Branch>
  branches2: Branch[] = [];
  pickuploc = new FormControl("");
  dropoffloc = new FormControl("");
  reserv : VehicleReservation = new VehicleReservation();
  currentUser: User = new User();
  days;
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

    this.reserv.pickupdate = this.date1;
    this.reserv.dropoffdate = this.date2;

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

    this.reserv.price = this.days *(this.selectedVehicle.price - this.selectedVehicle.price * this.selectedVehicle.discount );
    alert("Cena:  " + this.reserv.price);

    this.resServise.book(this.id,this.reserv).subscribe(data => {
      alert("Rezervisano! ");
    })

  }


 

}
