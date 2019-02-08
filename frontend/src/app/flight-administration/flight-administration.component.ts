/// <reference types="@types/googlemaps" />
import { Component, OnInit, ViewChild, Input } from "@angular/core";
import { UserProfileService } from "src/services/user-profile.service";

@Component({
  selector: "app-flight-administration",
  templateUrl: "./flight-administration.component.html",
  styleUrls: ["./flight-administration.component.scss"]
})
export class FlightAdministrationComponent implements OnInit {
  constructor(private userProfileService: UserProfileService) {}

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
  airlineMock = { luggageClassPriceList: { luggageClassPrices: [] } };
  ngOnInit() {
    this.userProfileService.getLoggedUser().subscribe(res => {
      console.log(res["airline"]);
      this.airlineMock = res["airline"];
    });
  }
}
