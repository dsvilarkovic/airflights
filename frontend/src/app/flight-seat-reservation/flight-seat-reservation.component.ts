import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute, Route } from "@angular/router";
import { AirlineAdminService } from "src/services/airline-admin.service";
import { FlightService}  from 'src/services/flight.service';

@Component({
  selector: 'app-flight-seat-reservation',
  templateUrl: './flight-seat-reservation.component.html',
  styleUrls: ['./flight-seat-reservation.component.scss']
})
export class FlightSeatReservationComponent implements OnInit {
  
  constructor(private activatedRoute: ActivatedRoute, private router : Router,
              private airlineAdminService : AirlineAdminService,
              private flightService: FlightService) { }
  id;
  errorMsg = "";
  Arr = Array;
  rows = 2;
  segmentNumber = 2;
  segmentNumbers = [];
  seatsMock = [
    { id: 1, seatRow: 1, seatColumn: 1, segmentNum: 1, airlineClass: "ECONOMY" },
    { id: 2, seatRow: 1, seatColumn: 2, segmentNum: 1, airlineClass: "ECONOMY" },
    { id: 3, seatRow: 2, seatColumn: 1, segmentNum: 1, airlineClass: "ECONOMY" },
    { id: 4, seatRow: 2, seatColumn: 2, segmentNum: 1, airlineClass: "BUSINESS"},
    { id: 5, seatRow: 1, seatColumn: 1, segmentNum: 2, airlineClass: "FIRST" },
    // { id: 6, seatRow: 2, seatColumn: 1, segmentNum: 2, airlineClass: "PREMIUM"}
  ];


  /**Ovo se misli na rezervisana sedista iz nekih drugih narudzbina */
  reservedSeatsMock = [
    // {
    //   seat: {
    //     id: 5,
    //     seatRow: 1,
    //     seatColumn: 1,
    //     segmentNum: 2,
    //     airlineClass: "FIRST"
    //   }
    // }
  ];

  airplaneMock = {
    fullName: null,
    segmentConfig: { seats: [], segmentNum: 3 }
  };
  seatConfigMock = {
    airplane: this.airplaneMock,
    segmentNum: this.segmentNumber,
    seats: this.seatsMock
  };
  segmentNumbersMock = [];

  ngOnInit() {
    this.id = this.activatedRoute.snapshot.params.id;
    this.id = 1;
    // this.airlineAdminService.getPlane(this.id).subscribe(res => {
    //   this.airplaneMock = res;
    //   console.log(this.airplaneMock);
    //   this.getNumberOfSeatsPerSegment();
    //   this.rows = this.getNumberOfRows();
    // });

    
    // this.airplaneMock.fullName = "Neki avion";
    // this.airplaneMock.segmentConfig = this.seatConfigMock;
    // this.getNumberOfSeatsPerSegment();
    // this.rows = this.getNumberOfRows();

    this.flightService.getPlane(this.id).subscribe(res => {
        this.airplaneMock = res;
        console.log(this.airplaneMock);
        this.getNumberOfSeatsPerSegment();
        this.rows = this.getNumberOfRows();
      });
    /**
     * ovde proveri ima li  u session storage nesto prethodno rezervisano
     */
    if(sessionStorage.getItem('userReservedSeats')){
      //podesi sedista u nasem mock airplane-u
      let item = sessionStorage.getItem('userReservedSeats');
      let userReservedSeats : Seat[] = JSON.parse(item) as Seat[];
      this.restoreSeats(userReservedSeats);
    }
  }

  /**
   * Vraca prethodno rezervisana sedista
   * @param userReservedSeats - rezervisana sedista
   */
  restoreSeats(userReservedSeats :  any[]){
    for(let i = 0; i < userReservedSeats.length; i++){
      let reservedSeat = userReservedSeats[i];

      for(let j = 0; j < this.airplaneMock.segmentConfig.seats.length; j++){
        let foundSeat = this.airplaneMock.segmentConfig.seats[j];
        if(foundSeat.seatRow == reservedSeat.seatRow &&
           foundSeat.seatColumn == reservedSeat.seatColumn &&
           foundSeat.segmentNum == reservedSeat.segmentNum){
            this.airplaneMock.segmentConfig.seats[j].airlineClass = reservedSeat.airlineClass;
        }
      }
    }

    //refreshView
    this.refreshView();
  }
  getNumberOfRows() {
    let rows = [];
    for (let j = 1; j <= this.airplaneMock.segmentConfig.seats.length; j++) {
      
      rows.push(this.airplaneMock.segmentConfig.seats[j - 1].seatRow);
    }
    return Math.max.apply(null, rows);
  }
  getNumberOfSeatsPerSegment() {
    for (let i = 1; i <= this.airplaneMock.segmentConfig.segmentNum; i++) {
      let columns = [];
      for (let j = 1; j <= this.airplaneMock.segmentConfig.seats.length; j++) {
        if (this.airplaneMock.segmentConfig.seats[j - 1].segmentNum === i) {
          columns.push(this.airplaneMock.segmentConfig.seats[j - 1].seatColumn);
        }
      }
      this.segmentNumbers.push(Math.max.apply(null, columns));
    }
  }


  isReserved(row, col, seg) {
    let seat = this.reservedSeatsMock.filter(
      rseat =>
        rseat.seat.seatRow == row &&
        rseat.seat.seatColumn == col &&
        rseat.seat.segmentNum == seg
    );
    if (seat[0]) {
      return true;
    }
  }

  cellClicked(row, col, seg) {
    if (this.isReserved(row, col, seg)) return null;
    this.airplaneMock.segmentConfig.seats
      .filter(
        seat =>
          seat.seatRow == row &&
          seat.seatColumn == col &&
          seat.segmentNum == seg
      )
      .map(seat => {
        console.log(seat);
        let reserved = seat.airlineClass.includes("reserved")? "" : " reserved";

        if (seat.airlineClass.includes("BUSINESS")) seat.airlineClass = "BUSINESS";
        else if (seat.airlineClass.includes("FIRST")) seat.airlineClass = "FIRST";
        else if (seat.airlineClass.includes("PREMIUM")) seat.airlineClass = "PREMIUM";
        else if (seat.airlineClass.includes("ECONOMY")) seat.airlineClass = "ECONOMY";

        seat.airlineClass = seat.airlineClass + reserved;

      });
  }

  checkSeat(row, col, seg) {
    let seat = this.airplaneMock.segmentConfig.seats.filter(
      seat =>
        seat.seatRow == row && seat.seatColumn == col && seat.segmentNum == seg
    );
    if (seat[0]) {
      return seat[0].airlineClass.toLowerCase();
    } else {
      this.airplaneMock.segmentConfig.seats.push({
        id: null,
        seatRow: row,
        seatColumn: col,
        segmentNum: seg,
        airlineClass: "ECONOMY"
      });
    }
    return "economy";
  }

  refreshView() {
    this.segmentNumbers = this.segmentNumbers.slice(0, this.segmentNumber);
  }

  saveChanges() {
        //nista jos ne raditi, potrebno ubaciti da se cuva u contextu
        let userReservedSeats : 
            Seat[] = new Array();

        //preleti kroz seats i vidi koji imaju reserved u class type  
        for(let j = 0; j < this.airplaneMock.segmentConfig.seats.length; j++){
            let observedSeat = this.airplaneMock.segmentConfig.seats[j];

            if(observedSeat.airlineClass.includes('reserved')){
                userReservedSeats.push(observedSeat);
            }
        }
        //i ubaci u session storage
        sessionStorage.setItem('userReservedSeats', JSON.stringify(userReservedSeats));
        //ubaci i id-leta (flightDTO) za koji se podesava, u slucaju da se korisnik vrati na stranicu
        //TODO: u let ubaciti kao flightDTO da ima airplaneDTO

        //nastavi na sledecu stranu

        sessionStorage.setItem('previous-seat-reservation-window', window.location.href);
        this.router.navigate(['/flight-friend-invitation']);

        //TODO: pri submitu cele pozivnice konacno izbaci klase koje su sacuvane, ako je potrebno
  }
}
