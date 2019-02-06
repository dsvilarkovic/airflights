import { DatePipe } from '@angular/common';
import { LoginService } from './../../services/login.service';
import { User } from './../user';
import { Component, OnInit } from '@angular/core';
import { ReservationServiceService } from 'src/services/reservation-service.service';
import { VehicleReservation } from '../vehicleReservation';
import { TokenStorageService } from 'src/services/auth/token-storage.service';
import { RentacarService } from 'src/services/rentacar.service';
import { rentacar } from '../rentacar';
import { Moment } from 'moment';
import * as moment from 'moment';


@Component({
  selector: 'app-authhomepage',
  templateUrl: './authhomepage.component.html',
  styleUrls: ['./authhomepage.component.scss']
})
export class AuthhomepageComponent implements OnInit {

  id;
  vehicleRes: VehicleReservation[] = [];
  tempRes: VehicleReservation = new VehicleReservation();
  selected = 0;
  hovered = 0;

  pom1: number;
  pom2: number;

  user: User = new User();
  cancelFlag: boolean = true;
  today: string;
  compareDate: string;


  constructor(private resServise: ReservationServiceService,
    private tokenService: TokenStorageService,
    private racService: RentacarService,
    private datePipe: DatePipe,
    private loginService: LoginService) { }

  ngOnInit() {
    this.id = this.tokenService.getUser();
    alert("Id? " + this.id);

    this.resServise.getAllByUserId(this.id).subscribe(data => {
      this.vehicleRes = data;
    })

    this.loginService.getLoggedById(this.id).subscribe(data => {
      this.user = data;

    })

  }

  days;
  cancel(id,date) {

    this.today = this.datePipe.transform(new Date(), 'yyyy-MM-dd');
    this.compareDate = date;


    var now = moment(this.today); 
    var end = moment(this.compareDate); 
    var duration = moment.duration(end.diff(now));
    this.days = duration.asDays();

    alert("Days? Ako je vece od 2, ne sme rezervacija da se izvrsi! " + this.days);

    if(this.days > 2) {
      this.resServise.cancel(id).subscribe(data => {
        alert("Obrisao");
        this.vehicleRes = data;
      })
    } else {

    }

    
  }

  pobediVehicle(id,last,idRes) {
      alert("Id vozila " + id);
      alert("ocena " + last);
      alert("Id rez " + idRes);
      
      this.racService.rateVehicle(last,id,idRes).subscribe(data => {
       // this.tempRes = data;
       // alert("Vracamo ocenjeni: " + this.tempRent.ratingsSum/this.tempRent.ratingsCount);
      })
    }


  pobediRentacar(id,last,idRes) {
  //  alert("Id " + id);
    //alert("last " + last);
    
    this.racService.rateRac(last,id,idRes).subscribe(data => {
      this.tempRes = data;
     // alert("Vracamo ocenjeni: " + this.tempRent.ratingsSum/this.tempRent.ratingsCount);
    })
  }

 /* rateThisRac(id,last) {
    alert("Id " + id);
    alert("last " + last);
    
    this.racService.rateRac(last,id,this.id).subscribe(data => {
      this.tempRent = data;
      this.user.marked = true;
      this.markedFlag = false;
      alert("Vracamo ocenjeni: " + this.tempRent.rating);
    })
  }*/

}