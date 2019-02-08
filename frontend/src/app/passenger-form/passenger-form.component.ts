import { Component, OnInit } from '@angular/core';
import { UserToUpdate} from '../user-profile/user-to-update';
import { Router } from '@angular/router';
@Component({
  selector: 'app-passenger-form',
  templateUrl: './passenger-form.component.html',
  styleUrls: ['./passenger-form.component.scss']
})
export class PassengerFormComponent implements OnInit {

  constructor(private router : Router) { }

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
    //sacuvaj ih kao posebne rezervacije

    //idi na izvestaj o pozvanim korisnicima
    this.router.navigate(['/checkout']);
  }

  searchForHotel(){
    this.router.navigate(['/hotels']);
  }

  searchForRentacar(){
    this.router.navigate(['/rentacarPreview']);
  }

}
