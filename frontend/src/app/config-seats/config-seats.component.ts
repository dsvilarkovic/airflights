import { Component, OnInit } from "@angular/core";
import { ActivatedRoute } from "@angular/router";
import { AirlineAdminService } from "src/services/airline-admin.service";

@Component({
  selector: "app-config-seats",
  templateUrl: "./config-seats.component.html",
  styleUrls: ["./config-seats.component.scss"]
})
export class ConfigSeatsComponent implements OnInit {
  constructor(
    private route: ActivatedRoute,
    private airlineAdminService: AirlineAdminService
  ) {}

  id;
  errorMsg = "";
  Arr = Array;
  rows = 2;
  segmentNumber = 2;
  segmentNumbers = [];
  seatsMock = [
    { id: 1, row: 1, column: 1, segmentNum: 1, airlineClassType: "ECONOMY" },
    { id: 2, row: 1, column: 2, segmentNum: 1, airlineClassType: "ECONOMY" },
    { id: 3, row: 2, column: 1, segmentNum: 1, airlineClassType: "ECONOMY" },
    { id: 4, row: 2, column: 2, segmentNum: 1, airlineClassType: "BUSINESS" },
    { id: 5, row: 1, column: 1, segmentNum: 2, airlineClassType: "FIRST" },
    { id: 6, row: 2, column: 1, segmentNum: 2, airlineClassType: "PREMIUM" }
  ];
  reservedSeatsMock = [
    {
      seat: {
        id: 5,
        seatRow: 1,
        seatColumn: 1,
        segmentNum: 2,
        airlineClass: "FIRST"
      }
    }
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
    this.id = this.route.snapshot.params.id;
    this.airlineAdminService.getPlane(this.id).subscribe(res => {
      this.airplaneMock = res;
      console.log(this.airplaneMock);
      this.getNumberOfSeatsPerSegment();
      this.rows = this.getNumberOfRows();
    });
    // this.seatConf = this.getSeatConfig(airplaneId);
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
        if (seat.airlineClass == "ECONOMY") seat.airlineClass = "BUSINESS";
        else if (seat.airlineClass == "BUSINESS") seat.airlineClass = "FIRST";
        else if (seat.airlineClass == "FIRST") seat.airlineClass = "PREMIUM";
        else if (seat.airlineClass == "PREMIUM") seat.airlineClass = "ECONOMY";
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

  refreshView() {
    this.segmentNumbers = this.segmentNumbers.slice(0, this.segmentNumber);
  }

  saveChanges() {
    let finalSeats = [];
    let reservedSeatsNo = this.reservedSeatsMock.length;
    let reservedSeatsNoFinal = 0;
    for (let seat of this.airplaneMock.segmentConfig.seats) {
      if (
        seat.seatRow <= this.rows &&
        seat.segmentNum <= this.segmentNumber &&
        seat.seatColumn <= this.segmentNumbers[seat.segmentNum - 1]
      ) {
        this.reservedSeatsMock.map(rs => {
          if (rs.seat.id === seat.id) reservedSeatsNoFinal++;
        });
        finalSeats.push(seat);
      }
    }

    this.errorMsg = "";
    if (reservedSeatsNo !== reservedSeatsNoFinal) {
      this.errorMsg =
        "You cannot remove already reserved seats! Take them back please.";
      console.log("Error! You cannot edit reserved seats!");
    } else if (
      this.airplaneMock.fullName === "" ||
      this.airplaneMock.fullName === undefined
    ) {
      this.errorMsg = "You have to provide airplane name!";
    } else {
      console.log(this.airplaneMock.fullName);
      console.log(this.airplaneMock.segmentConfig);

      // this.airplaneMock.segmentConfig.seats = finalSeats;
      this.airlineAdminService
        .updatePlaneInfo(this.airplaneMock)
        .subscribe(res => {
          console.log("RES1: ");
          console.log(res);
        });

      //config.seats = finalSeats;
      this.airlineAdminService
        .updateSeatConfig(this.airplaneMock.segmentConfig)
        .subscribe(res => {
          console.log("RES2: ");
          console.log(res);
        });
    }
  }
}
