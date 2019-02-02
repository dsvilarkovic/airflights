import { Component, OnInit, ChangeDetectorRef } from "@angular/core";

@Component({
  selector: "app-add-plane",
  templateUrl: "./add-plane.component.html",
  styleUrls: ["./add-plane.component.scss"]
})
export class AddPlaneComponent implements OnInit {
  constructor() {}

  errorMsg = "";
  Arr = Array;
  rows = 12;
  segmentNumber = 3;
  segmentNumbers = [2, 1, 2];
  airplaneName;
  seats = [];

  segmentNumbersMock = [];
  ngOnInit() {}

  cellClicked(row, col, seg) {
    this.seats
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
    let seat = this.seats.filter(
      seat => seat.row == row && seat.column == col && seat.segmentNum == seg
    );
    if (seat[0]) {
      return seat[0].airlineClassType.toLowerCase();
    } else {
      this.seats.push({
        id: 0,
        row: row,
        column: col,
        segmentNum: seg,
        airlineClassType: "ECONOMY"
      });
    }
    return "economy";
  }

  refreshView() {
    this.segmentNumbers = this.segmentNumbers.slice(0, this.segmentNumber);
  }

  saveChanges() {
    let finalSeats = [];

    for (let seat of this.seats) {
      if (
        seat.row <= this.rows &&
        seat.segmentNum <= this.segmentNumber &&
        seat.column <= this.segmentNumbers[seat.segmentNum - 1]
      ) {
        finalSeats.push(seat);
      }
    }
    if (this.airplaneName === "" || this.airplaneName === undefined) {
      this.errorMsg = "You have to enter airplane's name!";
    } else {
      console.log(this.airplaneName);
      console.log(finalSeats);
    }
  }
}
