import { Component, OnInit, Input } from "@angular/core";
import { FlightService } from "src/services/flight.service";
import { AirlineAdminService } from "src/services/airline-admin.service";
import { UserProfileService } from "src/services/user-profile.service";

@Component({
  selector: "app-flight-table",
  templateUrl: "./flight-table.component.html",
  styleUrls: ["./flight-table.component.scss"]
})
export class FlightTableComponent implements OnInit {
  @Input() airlineMock;
  clickedFlightId;
  airline;
  Arr = Array;
  pageNo = 1;
  collectionSize = 40;
  pageSize;
  newDestination;
  clickedDestinationId;
  clickedFlight = {
    flightLegsDTO: [{ fullName: "Belgrade" }, { fullName: "Moscow" }],
    legCount: 0,
    flightClassPricesMap: {},
    airlineId: null
  };
  destinationsMock = [
    { id: 1, fullName: "Belgrade" },
    { id: 2, fullName: "London" },
    { id: 3, fullName: "Moscow" },
    { id: 4, fullName: "Helsinki" }
  ];
  flightsMock = [
    {
      id: 1,
      legCount: 0,
      departureDatetime: "2020-02-03T01:06",
      arrivalDatetime: "2020-02-06T01:32",
      airline: this.airlineMock,
      travelTime: 220,
      travelDistance: 1305,
      flightDiscount: 10,
      airlineId: null,
      flightLegsDTO: [
        { id: 1, fullName: "Belgrade" },
        { id: 2, fullName: "London" }
      ],
      flightClassPricesMap: { BUSINESS: 23, FIRST: 434 }
    }
  ];
  constructor(
    private airlineAdminService: AirlineAdminService,
    private userProfileService: UserProfileService
  ) {}

  ngOnInit() {
    this.userProfileService.getLoggedUser().subscribe(user => {
      this.airlineAdminService
        .getDestinations(user["airline"].id)
        .subscribe(dest => {
          this.airlineAdminService
            .getFlights(0, user["airline"].id)
            .subscribe(res => {
              console.log(res);
              this.destinationsMock = dest.content;
              this.flightsMock = res.content;
              this.flightsMock.map(f => {
                f.legCount = f.legCount - 2;
                f.airline = user["airline"];
              });
              this.collectionSize = res.totalElements;
              this.pageSize = res.size;
              this.airline = user["airline"];
            });
        });
    });
  }

  flightClicked(flightId) {
    this.clickedFlightId = flightId;
    let res = this.flightsMock.filter(f => f.id === flightId);
    this.clickedFlight = res[0];

    console.log(this.clickedFlight);
  }

  destinationClicked(id) {
    this.clickedDestinationId = id;
  }

  removeFlight(flightId) {
    let filtered = this.flightsMock.filter(f => {
      return f.id !== flightId;
    });
    this.flightsMock = filtered;
    this.airlineAdminService.removeFlight(flightId).subscribe(res => {
      console.log(res);
    });
  }

  addFlight(flight) {
    this.flightsMock.push(flight);
    console.log(flight);
    this.airlineAdminService.addFlight(flight).subscribe(res => {
      console.log(res);
    });
  }

  editFlight(flight) {
    this.flightsMock.map(f => {
      f.id === flight.id;
      f = flight;
      let filtered = f.flightLegsDTO.filter(city => {
        return city.fullName !== "";
      });
      f.flightLegsDTO = filtered;
      console.log(f);
    });
    flight.airlineId = this.airline.id;
    this.airlineAdminService.editFlight(flight).subscribe(res => {
      console.log(res);
    });
  }

  onPageChange(pageNo) {
    this.airlineAdminService
      .getFlights(pageNo - 1, this.airline.id)
      .subscribe(res => {
        console.log(res);

        this.flightsMock = res.content;
        this.flightsMock.map(f => {
          f.legCount = f.legCount - 2;
          f.airline = this.airline;
        });
      });
  }
  addFlightBtnClicked() {
    this.clickedFlight = {
      flightLegsDTO: [{ fullName: "Belgrade" }, { fullName: "Moscow" }],
      legCount: 0,
      flightClassPricesMap: { ECONOMY: 0, BUSINESS: 0, FIRST: 0, PREMIUM: 0 },
      airlineId: this.airline["id"]
    };
  }

  removeDestination(id) {
    let filtered = this.destinationsMock.filter(d => {
      return d.id !== id;
    });
    this.destinationsMock = filtered;
    this.airlineAdminService.removeDestination(id).subscribe(res => {
      console.log(res);
    });
  }
  addDestination() {
    this.destinationsMock.push({ id: null, fullName: this.newDestination });
    this.airlineAdminService
      .addDestination({ fullName: this.newDestination }, this.airline.id)
      .subscribe(res => {
        console.log(res);
      });
  }
}
