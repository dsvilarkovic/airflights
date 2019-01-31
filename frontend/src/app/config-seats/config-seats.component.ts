import { Component, OnInit } from "@angular/core";

@Component({
  selector: "app-config-seats",
  templateUrl: "./config-seats.component.html",
  styleUrls: ["./config-seats.component.scss"]
})
export class ConfigSeatsComponent implements OnInit {
  constructor() {}

  errorMsg = "";
  Arr = Array;
  rows = 2;
  segmentNumber = 2;
  segmentNumbers = [2, 1];
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
        row: 1,
        column: 1,
        segmentNum: 2,
        airlineClassType: "FIRST"
      }
    }
  ];
  airplaneMock = { id: 1, fullName: "Žiška (AirBus A320)" };
  seatConfigMock = {
    airplane: this.airplaneMock,
    segmentNum: this.segmentNumber,
    seats: this.seatsMock
  };
  segmentNumbersMock = [];
  ngOnInit() {
    // this.seatConf = this.getSeatConfig(airplaneId);
    this.getNumberOfSeatsPerSegment();
  }
  getNumberOfSeatsPerSegment() {
    for (let i = 1; i <= this.segmentNumber; i++) {
      let columns = [];
      for (let j = 1; j <= this.seatsMock.length; j++) {
        if (this.seatsMock[j - 1].segmentNum === i) {
          columns.push(this.seatsMock[j - 1].column);
        }
      }
      this.segmentNumbersMock.push(Math.max.apply(null, columns));
    }
  }

  cellClicked(row, col, seg) {
    if (this.isReserved(row, col, seg)) return null;
    this.seatsMock
      .filter(
        seat => seat.row == row && seat.column == col && seat.segmentNum == seg
      )
      .map(seat => {
        if (seat.airlineClassType == "ECONOMY")
          seat.airlineClassType = "BUSINESS";
        else if (seat.airlineClassType == "BUSINESS")
          seat.airlineClassType = "FIRST";
        else if (seat.airlineClassType == "FIRST")
          seat.airlineClassType = "PREMIUM";
        else if (seat.airlineClassType == "PREMIUM")
          seat.airlineClassType = "ECONOMY";
      });
  }

  checkSeat(row, col, seg) {
    let seat = this.seatsMock.filter(
      seat => seat.row == row && seat.column == col && seat.segmentNum == seg
    );
    if (seat[0]) {
      return seat[0].airlineClassType.toLowerCase();
    } else {
      this.seatsMock.push({
        id: 0,
        row: row,
        column: col,
        segmentNum: seg,
        airlineClassType: "ECONOMY"
      });
    }
    return "economy";
  }
  isReserved(row, col, seg) {
    let seat = this.reservedSeatsMock.filter(
      rseat =>
        rseat.seat.row == row &&
        rseat.seat.column == col &&
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
    for (let seat of this.seatsMock) {
      if (
        seat.row <= this.rows &&
        seat.segmentNum <= this.segmentNumber &&
        seat.column <= this.segmentNumbers[seat.segmentNum - 1]
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
      console.log(finalSeats);
    }
  }
}
