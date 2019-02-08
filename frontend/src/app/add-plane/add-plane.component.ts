import { Component, OnInit, ChangeDetectorRef } from "@angular/core";
import { AirlineAdminService } from "src/services/airline-admin.service";
import { UserProfileService } from "src/services/user-profile.service";

@Component({
  selector: "app-add-plane",
  templateUrl: "./add-plane.component.html",
  styleUrls: ["./add-plane.component.scss"]
})
export class AddPlaneComponent implements OnInit {
  constructor(
    private airlineService: AirlineAdminService,
    private userProfileService: UserProfileService
  ) {}

  errorMsg = "";
  Arr = Array;
  rows = 12;
  segmentNumber = 3;
  segmentNumbers = [2, 1, 2];
  airplaneName;
  seats = [];
  airline;
  segmentNumbersMock = [];
  ngOnInit() {
    this.userProfileService.getLoggedUser().subscribe(res => {
      console.log(res["airline"]);
      this.airline = res["airline"];
    });
  }

  cellClicked(row, col, seg) {
    this.seats
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
    let seat = this.seats.filter(
      seat =>
        seat.seatRow == row && seat.seatColumn == col && seat.segmentNum == seg
    );
    if (seat[0]) {
      return seat[0].airlineClass.toLowerCase();
    } else {
      this.seats.push({
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
    let finalSeats = [];

    for (let seat of this.seats) {
      if (
        seat.seatRow <= this.rows &&
        seat.segmentNum <= this.segmentNumber &&
        seat.seatColumn <= this.segmentNumbers[seat.segmentNum - 1]
      ) {
        finalSeats.push(seat);
      }
    }
    if (this.airplaneName === "" || this.airplaneName === undefined) {
      this.errorMsg = "You have to enter airplane's name!";
    } else {
      console.log(this.airplaneName);

      let plane = {
        fullName: this.airplaneName,
        airline: this.airline,
        segmentConfig: { seats: finalSeats, segmentNum: this.segmentNumber }
      };
      console.log(plane);
      this.airlineService.addPlane(plane).subscribe(res => {
        console.log(res);
      });
    }
  }
}
