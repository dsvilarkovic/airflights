import { Component, OnInit, Input } from "@angular/core";

@Component({
  selector: "app-flight-table",
  templateUrl: "./flight-table.component.html",
  styleUrls: ["./flight-table.component.scss"]
})
export class FlightTableComponent implements OnInit {
  @Input() airlineMock;
  clickedFlightId;
  Arr = Array;
  pageNo = 1;
  collectionSize = 40;
  newDestination;
  clickedDestinationId;
  clickedFlight = {
    flightLegs: [{ fullName: "Belgrade" }, { fullName: "Moscow" }],
    legCount: 0,
    flightClassPrices: [
      { price: 0, airlineClassType: "ECONOMY" },
      { price: 0, airlineClassType: "BUSINESS" },
      { price: 0, airlineClassType: "FIRST" },
      { price: 0, airlineClassType: "PREMIUM" }
    ]
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
      departureDateTime: "2020-02-03T01:06",
      arrivalDateTime: "2020-02-06T01:32",
      airline: this.airlineMock,
      travelTime: 220,
      travelDistance: 1305,
      flightDiscount: 10,
      flightLegs: [
        { id: 1, fullName: "Belgrade" },
        { id: 2, fullName: "London" }
      ],
      flightClassPrices: [
        { price: 440.5, airlineClassType: "ECONOMY" },
        { price: 540.5, airlineClassType: "BUSINESS" },
        { price: 750, airlineClassType: "FIRST" },
        { price: 940, airlineClassType: "PREMIUM" }
      ]
    },
    {
      id: 2,
      legCount: 1,
      departureDateTime: "2020-02-01T01:02",
      arrivalDateTime: "2020-03-13T01:26",
      airline: this.airlineMock,
      travelTime: 390,
      travelDistance: 1455,
      flightDiscount: 0,
      flightLegs: [
        { id: 1, fullName: "Belgrade" },
        { id: 4, fullName: "Helsinki" },
        { id: 3, fullName: "Moscow" }
      ],
      flightClassPrices: [
        { price: 440.5, airlineClassType: "ECONOMY" },
        { price: 540.5, airlineClassType: "BUSINESS" },
        { price: 750, airlineClassType: "FIRST" },
        { price: 940, airlineClassType: "PREMIUM" }
      ]
    }
  ];
  constructor() {}

  ngOnInit() {}

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
  }

  addFlight(flight) {
    this.flightsMock.push(flight);
  }

  editFlight(flight) {
    this.flightsMock.map(f => {
      f.id === flight.id;
      f = flight;
      let filtered = f.flightLegs.filter(city => {
        return city.fullName !== "";
      });
      f.flightLegs = filtered;
      console.log(f);
    });
  }
  addFlightBtnClicked() {
    this.clickedFlight = {
      flightLegs: [{ fullName: "Belgrade" }, { fullName: "Moscow" }],
      legCount: 0,
      flightClassPrices: [
        { price: 0, airlineClassType: "ECONOMY" },
        { price: 0, airlineClassType: "BUSINESS" },
        { price: 0, airlineClassType: "FIRST" },
        { price: 0, airlineClassType: "PREMIUM" }
      ]
    };
  }

  removeDestination(id) {
    let filtered = this.destinationsMock.filter(d => {
      return d.id !== id;
    });
    this.destinationsMock = filtered;
  }
  addDestination() {
    this.destinationsMock.push({ id: null, fullName: this.newDestination });
  }
}
