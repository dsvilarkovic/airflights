import { Component, OnInit } from '@angular/core';
import { FriendsService } from 'src/services/friends.service';
import { Router } from '@angular/router';
import { Location } from '@angular/common';

@Component({
  selector: 'app-flight-friend-invitation',
  templateUrl: './flight-friend-invitation.component.html',
  styleUrls: ['./flight-friend-invitation.component.scss']
})
export class FlightFriendInvitationComponent implements OnInit {

  constructor(private friendsService: FriendsService, 
              private router : Router,
              private location : Location) { }
  mockFriends = [
    { id: 1, firstName: "Biljan", lastName: "Ristic" },
    { id: 2, firstName: "Dragan", lastName: "Torbica" },
    { id: 3, firstName: "Zivan", lastName: "Radosavljevic" }
  ];
  invitedFriends = [];
  friends = [];

  seatRemaining = 0;

  pageNo = 1;
  collectionSize = 40;
  pageNo2 = 1;
  collectionSize2 = 30;
  pageNo3 = 1;
  collectionSize3 = 10;

  ngOnInit() {
    // this.friendsService.getFriends().subscribe(res => {
    //   this.friends = res;
      
    // });
    this.friends = this.mockFriends;


    this.setUpComponent();
  }

  setUpComponent(){
    //ako su vec postavljeni prijatelji dodaj ih u listu
    //i na osnovu toga postavi pocetni broj dozvoljenih ubacenih sedista
    if(sessionStorage.getItem('invitedFriends')){
      let friendsItem = sessionStorage.getItem('invitedFriends');
      this.invitedFriends = JSON.parse(friendsItem);
    }

    //izbaci broj stolica koje su uzete, i ubaci u brojac
    let seatItem = sessionStorage.getItem('userReservedSeats');
    let seats : Seat[] = JSON.parse(seatItem) as Seat[];
    let friendsLength = this.invitedFriends.length;

    this.seatRemaining = (seats.length - friendsLength > 1) ? seats.length - friendsLength -  1: 0;
  }
  removeFriend(friendId) {
    console.log("Remove " + friendId);
    /*
    this.invitedFriends.map(user => {
      if (user.id === friendId) this.friends.push(user);
    });
    */
    this.invitedFriends = this.invitedFriends.filter(u => {
      return friendId !== u.id;
    });
    this.seatRemaining++;
  }

  addFriend(friendId) {
    console.log("Add " + friendId);
    //ne daj dalje ako je nula
    if(this.seatRemaining == 0){
      return;
    }
    if(this.isInvited(friendId)){
      return;
    }
    this.friends.map(user => {
      if (user.id === friendId) this.invitedFriends.push(user);
    });
    
    this.seatRemaining--;
  } 

  /**
   *  Provera da li je prijatelj vec pozvan
   * @param friendId - id prijatelja za kojeg se proverava
   */
  isInvited(friendId){
    console.log('pozvana');
    let result = false;
    this.invitedFriends.map(user => {if(user.id == friendId) result = true});
    return result;
  }

  goBack(){
    // let link = sessionStorage.getItem('previous-seat-reservation-window');
    // this.router.navigate([link]);
    this.location.back();
  }

  saveChanges(){
    //uzmi sve korisnike koji su u invitedFriends
    let stringInvitedFriends = JSON.stringify(this.invitedFriends); 
    //strpaj ih u sesiju
    sessionStorage.setItem('invitedFriends', stringInvitedFriends);

    this.router.navigate(['/passenger-form']);
  }


}
