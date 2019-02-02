import { LoginService } from './../../services/login.service';
import { User } from './../user';
import { Component, OnInit } from '@angular/core';
import { ReservationServiceService } from 'src/services/reservation-service.service';
import { VehicleReservation } from '../vehicleReservation';
import { TokenStorageService } from 'src/services/auth/token-storage.service';
import { RentacarService } from 'src/services/rentacar.service';
import { rentacar } from '../rentacar';


@Component({
  selector: 'app-authhomepage',
  templateUrl: './authhomepage.component.html',
  styleUrls: ['./authhomepage.component.scss']
})
export class AuthhomepageComponent implements OnInit {

  id;
  vehicleRes: VehicleReservation[] = [];
  tempRent: rentacar = new rentacar();
  selected = 0;
  hovered = 0;

  pom1: number;
  pom2: number;

  user: User = new User();
  markedFlag: boolean = true;


  constructor(private resServise: ReservationServiceService,
    private tokenService: TokenStorageService,
    private racService: RentacarService,
    private loginService: LoginService) { }

  ngOnInit() {
    this.id = this.tokenService.getUser();
    alert("Id? " + this.id);

    this.resServise.getAllByUserId(this.id).subscribe(data => {
      this.vehicleRes = data;
    })

    this.loginService.getLoggedById(this.id).subscribe(data => {
      this.user = data;
      alert("flsdkjf " + this.user.marked);
      if(this.user.marked) {
        this.markedFlag = false;
      }

    })

  }

  cancel(id) {
    this.resServise.cancel(id).subscribe(data => {
      alert("Obrisao");
      this.vehicleRes = data;

    })
  }

  pobedi(id,last) {
    alert("Id " + id);
    alert("last " + last);
    
    this.racService.rateRac(last,id,this.id).subscribe(data => {
      this.tempRent = data;
      this.user.marked = true;
      this.markedFlag = false;
      alert("Vracamo ocenjeni: " + this.tempRent.rating);
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
