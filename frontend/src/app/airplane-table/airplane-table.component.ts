import { Component, OnInit } from "@angular/core";

@Component({
  selector: "app-airplane-table",
  templateUrl: "./airplane-table.component.html",
  styleUrls: ["./airplane-table.component.scss"]
})
export class AirplaneTableComponent implements OnInit {
  clickedAirplaneId;
  seatsMock = [
    { id: 1, row: 1, column: 1, segmentNum: 1, airlineClassType: "ECONOMY" },
    { id: 2, row: 1, column: 2, segmentNum: 1, airlineClassType: "ECONOMY" },
    { id: 3, row: 2, column: 1, segmentNum: 1, airlineClassType: "ECONOMY" },
    { id: 4, row: 2, column: 2, segmentNum: 1, airlineClassType: "BUSINESS" },
    { id: 5, row: 1, column: 1, segmentNum: 2, airlineClassType: "FIRST" },
    { id: 6, row: 2, column: 1, segmentNum: 2, airlineClassType: "PREMIUM" }
  ];
  airplanesMock = [
    { id: 1, fullName: "Boeing 787 Dreamliner", segmentConfig: this.seatsMock },
    { id: 2, fullName: "Žiška - AirBus A320", segmentConfig: this.seatsMock }
  ];

  constructor() {}

  ngOnInit() {
    // GET REQUEST: get first n airplanes from db
  }

  airplaneClicked(airplaneId) {
    this.clickedAirplaneId = airplaneId;
  }

  removeAirplane(airplaneId) {
    // DELETE REQUEST
    let filtered = this.airplanesMock.filter(plane => {
      return plane.id != airplaneId;
    });
    this.airplanesMock = filtered;
  }
}
