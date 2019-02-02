/// <reference types="@types/googlemaps" />
import { Component, OnInit, ViewChild, Input } from "@angular/core";

@Component({
  selector: "app-airline-info",
  templateUrl: "./airline-info.component.html",
  styleUrls: ["./airline-info.component.scss"]
})
export class AirlineInfoComponent implements OnInit {
  constructor() {}
  @ViewChild("gmap") gmapElement: any;
  map: google.maps.Map;
  showMap = false;
  @Input() airlineMock;
  clickedLuggageId;
  clickedLuggage;
  luggageInfo = {};
  ngOnInit() {}

  onShowMap() {
    let mapProp = {
      center: new google.maps.LatLng(
        this.airlineMock.latitude,
        this.airlineMock.longitude
      ),
      zoom: 15,
      mapTypeId: google.maps.MapTypeId.ROADMAP
    };
    this.map = new google.maps.Map(this.gmapElement.nativeElement, mapProp);
  }

  saveChanges() {
    console.log(this.airlineMock);
  }

  luggageClicked(luggageId) {
    this.clickedLuggageId = luggageId;
    let res = this.airlineMock.luggageClassPriceList.luggageClassPrices.filter(
      f => f.id === luggageId
    );
    this.clickedLuggage = res[0];
  }

  addLuggageInfo() {
    this.airlineMock.luggageClassPriceList.luggageClassPrices.push(
      this.luggageInfo
    );
    // POST REQUEST - add luggage info
  }

  removeLuggage(luggageId) {
    console.log(luggageId);
    // DELETE REQUEST
    let filtered = this.airlineMock.luggageClassPriceList.luggageClassPrices.filter(
      luggage => {
        return luggage.id != luggageId;
      }
    );
    this.airlineMock.luggageClassPriceList.luggageClassPrices = filtered;
  }
}
