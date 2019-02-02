/// <reference types="@types/googlemaps" />
import { Component, OnInit, ViewChild, Input } from "@angular/core";

@Component({
  selector: "app-flight-administration",
  templateUrl: "./flight-administration.component.html",
  styleUrls: ["./flight-administration.component.scss"]
})
export class FlightAdministrationComponent implements OnInit {
  constructor() {}

  myData = [
    ["2018-25-01", 8136000],
    ["2018-26-01", 8538000],
    ["2018-27-01", 2244000],
    ["2018-28-01", 3470000],
    ["2018-29-01", 19500000]
  ];
  chart = {
    data: this.myData,
    title: "Income Chart",
    type: "AreaChart",
    height: 600,
    width: 600
  };
  airlineMock = {
    fullName: "Nikola Tesla Airport",
    promoInfo: "Promotivni opis aerodroma",
    longitude: 20.290972,
    latitude: 44.820209,
    luggageClassPriceList: {
      luggageClassPrices: [
        {
          id: 1,
          length: 30,
          width: 38,
          height: 55,
          weight: 15,
          airlineClassType: "ECONOMY",
          price: 49.9
        },
        {
          id: 2,
          length: 40,
          width: 48,
          height: 65,
          weight: 25,
          airlineClassType: "ECONOMY",
          price: 69.9
        }
      ]
    }
  };
  ngOnInit() {}
}
