import { Component, OnInit } from '@angular/core';
import { UserToUpdate} from '../user-profile/user-to-update';
import { Router } from '@angular/router';
import { FlightTicket } from './flight-ticket';
import { FlightService } from 'src/services/flight.service';
import { Flight } from '../flight';
@Component({
  selector: 'app-passenger-form',
  templateUrl: './passenger-form.component.html',
  styleUrls: ['./passenger-form.component.scss']
})
export class PassengerFormComponent implements OnInit {

  constructor(private router : Router,
              private flightService : FlightService) { }

  tabsCount = 0;
  passengers : UserToUpdate[] = [];
  loggedInUser : UserToUpdate = { id: 666, 
                                  index : '666', 
                                  address : 'Vuka Mandusica' , 
                                  firstName : 'Dusan',
                                  lastName : 'Svilarkovic',
                                  email : 'dule996@gmail',
                                  idRentACar : 1,
                                  newPassword : "",
                                  password : "",
                                  phoneNumber : "1331113",
                                  verify : true,
                                  passportNumber: null
                                }

  ngOnInit() {
    
    this.setUpComponent();
  }

  setUpComponent(){
    //napravi broj tabova da bude jednak broju sedista
    let seatItem = sessionStorage.getItem('userReservedSeats');
    let seats : Seat[] = JSON.parse(seatItem) as Seat[];
    this.tabsCount = seats.length;


    //ubaci ulogovanog korisnika
    this.getLoggedUser();
    this.passengers.push(this.loggedInUser);

    //postavi pozvane prijatelje
    let friendsItem = sessionStorage.getItem('invitedFriends');
    this.passengers.push(... JSON.parse(friendsItem) as UserToUpdate[]);

    //postavi ostale kao prazne korisnike
    //broj mesta - broj prijatelja - 1
    let totalRemainingSeats = this.tabsCount - this.passengers.length;
    console.log('Fali mi '  + totalRemainingSeats)
    for(let j = 0; j < totalRemainingSeats; j++){
      let unregisteredUser : UserToUpdate = new UserToUpdate;
      unregisteredUser.firstName = "Unregistered user";
      unregisteredUser.lastName = j.toString();
      this.passengers.push(unregisteredUser);
    }


    //podesi da je aktivni tag prvi
    
        
  }

  getLoggedUser(){
    //this.loggedInUser neki http
  }
  goBack(){
    this.router.navigate(['/flight-friend-invitation']);
  }

  saveChanges(){
    let seatItem = sessionStorage.getItem('userReservedSeats');
    let seats : Seat[] = JSON.parse(seatItem) as Seat[];
    //sacuvaj ih kao posebne rezervacije
    for (let i = 0; i < this.passengers.length; i++) {
      //uzmi odaberi sediste
      let seat = seats.pop();

      let airlineClass = seat.airlineClass;
      let numberClass = 0;
      if(airlineClass.includes("ECONOMY"))
        numberClass = 0;
      if(airlineClass.includes("FIRST"))
        numberClass = 1;
      if(airlineClass.includes("BUSINESS"))
        numberClass = 2;
      if(airlineClass.includes("PREMIUM"))
        numberClass = 3;

      let item =  sessionStorage.getItem('flight');
      let flight : Flight = JSON.parse(item) as Flight;

      let flightTicket : FlightTicket = {
        abstractUserDTO : this.passengers[i],
        airlineClassType : numberClass,
        airlineGrade : 0,
        arrivalDatetime : flight.arrivalDatetime,
        departureDatetime : flight.departureDateTime,
        flightClassPrice : 100,
        flightGrade : 0,
        arrivalDestination : flight.flightLegsDTO[0],
        departureDestination  :flight.flightLegsDTO[flight.legCount - 1],
        id : null,
        isAccepted : false,
        isFastReservation : false,
        seatDTO : seat,
        flightId : flight.id,
        priceReduction : 1.0,
        travelDistance : 10000,
        travelTime : 100
      }

      this.flightService.sendFlightTicket(flightTicket);
      
    }

    let seat = seats.pop();

      let airlineClass = seat.airlineClass;
      let numberClass = 0;
      if(airlineClass.includes("ECONOMY"))
        numberClass = 0;
      if(airlineClass.includes("FIRST"))
        numberClass = 1;
      if(airlineClass.includes("BUSINESS"))
        numberClass = 2;
      if(airlineClass.includes("PREMIUM"))
        numberClass = 3;

      let item =  sessionStorage.getItem('flight');
      let flight : Flight = JSON.parse(item) as Flight;

      let flightTicket : FlightTicket = {
        abstractUserDTO : this.loggedInUser,
        airlineClassType : numberClass,
        airlineGrade : 0,
        arrivalDatetime : flight.arrivalDatetime,
        departureDatetime : flight.departureDateTime,
        flightClassPrice : 100,
        flightGrade : 0,
        arrivalDestination : flight.flightLegsDTO[0],
        departureDestination  :flight.flightLegsDTO[flight.legCount - 1],
        id : null,
        isAccepted : false,
        isFastReservation : false,
        seatDTO : seat,
        flightId : flight.id,
        priceReduction : 1.0,
        travelDistance : 10000,
        travelTime : 100
      }

      this.flightService.sendFlightTicket(flightTicket);

    

    //idi na izvestaj o pozvanim korisnicima
    this.router.navigate(['/home']);
  }


  searchForHotel(){
    this.router.navigate(['/hotels']);
  }

  searchForRentacar(){
    this.router.navigate(['/rentacarPreview']);
  }

}
