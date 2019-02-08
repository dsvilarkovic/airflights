import { Component, OnInit } from "@angular/core";
import { AirlineAdminService } from "src/services/airline-admin.service";
import { UserProfileService } from "src/services/user-profile.service";

@Component({
  selector: "app-airplane-table",
  templateUrl: "./airplane-table.component.html",
  styleUrls: ["./airplane-table.component.scss"]
})
export class AirplaneTableComponent implements OnInit {
  clickedAirplaneId;
  pageNo = 1;
  collectionSize = 40;
  pageSize = 1;
  seatsMock = [
    { id: 1, row: 1, column: 1, segmentNum: 1, airlineClassType: "ECONOMY" },
    { id: 2, row: 1, column: 2, segmentNum: 1, airlineClassType: "ECONOMY" },
    { id: 3, row: 2, column: 1, segmentNum: 1, airlineClassType: "ECONOMY" },
    { id: 4, row: 2, column: 2, segmentNum: 1, airlineClassType: "BUSINESS" },
    { id: 5, row: 1, column: 1, segmentNum: 2, airlineClassType: "FIRST" },
    { id: 6, row: 2, column: 1, segmentNum: 2, airlineClassType: "PREMIUM" }
  ];
  airplanesMock = [
    {
      id: 1,
      fullName: "Boeing 787 Dreamliner",
      segmentConfig: { seats: [] },
      length: 0
    }
  ];
  airline;

  constructor(
    private airlineAdminService: AirlineAdminService,
    private userProfileService: UserProfileService
  ) {}

  ngOnInit() {
    // GET REQUEST: get first n airplanes from db
    this.userProfileService.getLoggedUser().subscribe(user => {
      this.airlineAdminService
        .getPlanes(0, user["airline"].id)
        .subscribe(res => {
          this.airline = user["airline"];
          console.log(res);
          this.airplanesMock = res.content;
          this.collectionSize = res.totalElements;
          this.pageSize = res.size;
        });
    });
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
    this.airlineAdminService.removePlane(airplaneId).subscribe(res => {
      console.log(res);
    });
  }

  onPageChange(pageNo) {
    this.airlineAdminService
      .getPlanes(pageNo - 1, this.airline.id)
      .subscribe(res => {
        console.log(res);

        this.airplanesMock = res.content;
      });
  }
}
