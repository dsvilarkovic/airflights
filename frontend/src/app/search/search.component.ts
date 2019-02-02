import { Component, OnInit, ViewChild } from "@angular/core";
import {
  NgbTypeahead,
  NgbDateAdapter,
  NgbDate
} from "@ng-bootstrap/ng-bootstrap";
import { Observable, Subject, merge } from "rxjs";
import { NgbDateStruct, NgbCalendar } from "@ng-bootstrap/ng-bootstrap";

import {
  debounceTime,
  distinctUntilChanged,
  filter,
  map
} from "rxjs/operators";

@Component({
  selector: "app-search",
  templateUrl: "./search.component.html",
  styleUrls: ["./search.component.scss"]
})
export class SearchComponent implements OnInit {
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
  airlinesMock = [this.airlineMock, this.airlineMock];

  pageNo = 1;
  collectionSize = 40;
  oneWay = true;
  navigation = "select";
  dateFrom: NgbDate = null;
  dateTo: NgbDate = null;
  dateFromCopy;
  dateToCopy;
  hoveredDate: NgbDate;
  searchResults = false;
  errorMsg = "";
  flightTypes = ["One-Way", "Round-Trip"];
  flightClasses = ["Economy", "Business", "First", "Premium"];
  cities = [];
  cityNames = [];
  clickedFlight = {
    flightLegs: null,
    flightClassPrices: null,
    airline: { luggageClassPriceList: {} }
  };
  lastSearch = {};
  searchModel = {
    cityFrom: null,
    cityTo: null,
    flightType: null,
    personNo: null,
    airlineClassType: null,
    multiCity: true,
    dateFrom: null,
    dateTo: null
  };
  filterModel = {
    airlineFilter: null,
    travelTimeFilter: null,
    searchModel: null
  };
  Arr = Array;
  @ViewChild("instanceFrom") instanceFrom: NgbTypeahead;
  @ViewChild("instanceTo") instanceTo: NgbTypeahead;
  @ViewChild("flightType") flightType;
  @ViewChild("flightClass") flightClass;
  @ViewChild("personNo") personNo;
  @ViewChild("airlineFilter") airlineFilter;
  @ViewChild("travelTimeFilter") travelTimeFilter;

  focus1$ = new Subject<string>();
  click1$ = new Subject<string>();
  focus2$ = new Subject<string>();
  click2$ = new Subject<string>();

  constructor(private calendar: NgbCalendar) {}

  ngOnInit() {
    this.cities = [
      { fullName: "Belgrade" },
      { fullName: "London" },
      { fullName: "Lisabon" }
    ];
    this.cities.map(c => this.cityNames.push(c.fullName));
  }

  flightTypeChanged() {
    if (this.flightType.nativeElement.value === "One-Way") {
      this.oneWay = true;
    } else this.oneWay = false;
  }

  onSearch() {
    if (this.validateSearch()) {
      this.searchModel.flightType = this.flightType.nativeElement.value.toUpperCase();

      if (this.flightClass.nativeElement.value === "All Classes") {
        this.searchModel.airlineClassType = null;
      } else {
        this.searchModel.airlineClassType = this.flightClass.nativeElement.value.toUpperCase();
      }
      this.searchModel.personNo = parseInt(
        this.personNo.nativeElement.value,
        10
      );
      //this.dateFrom = this.calendar.getToday();
      this.searchModel.dateFrom = new Date(
        this.dateFrom.year,
        this.dateFrom.month - 1,
        this.dateFrom.day
      );
      if (this.dateTo !== null) {
        this.searchModel.dateTo = new Date(
          this.dateTo.year,
          this.dateTo.month - 1,
          this.dateTo.day
        );
      }
      this.dateFromCopy = this.dateFrom;
      this.dateToCopy = this.dateTo;
      console.log(this.searchModel);
      console.log(this.dateTo);
      console.log(this.searchModel.dateFrom);
      this.lastSearch = this.searchModel;
      this.searchResults = true;
    } else this.searchResults = false;
  }

  onFilter() {
    if (this.airlineFilter == "All Airlines") {
      this.filterModel.airlineFilter = null;
    } else
      this.filterModel.airlineFilter = this.airlineFilter.nativeElement.value;
    if (this.airlineFilter == "Any") {
      this.filterModel.travelTimeFilter = null;
    } else
      this.filterModel.travelTimeFilter = this.travelTimeFilter.nativeElement.value;
    this.filterModel.searchModel = this.lastSearch;
    console.log(this.filterModel);
  }
  validateSearch() {
    if (
      !(
        this.cityNames.includes(this.searchModel.cityFrom) &&
        this.cityNames.includes(this.searchModel.cityTo)
      )
    ) {
      this.errorMsg = "There is no such destination available.";
      return false;
    } else this.errorMsg = "";
    if (this.searchModel.cityFrom === this.searchModel.cityTo) {
      this.errorMsg = "You cannot have same starting and ending destination.";
      return false;
    } else {
      this.errorMsg = "";
    }
    if (
      this.dateFrom == null ||
      (this.flightType.nativeElement.value == "Round-Trip" &&
        this.dateTo == null)
    ) {
      this.errorMsg = "You must pick a date.";
      return false;
    }

    return true;
  }

  flightClicked(flightId) {
    let res = this.flightsMock.filter(f => f.id === flightId);
    this.clickedFlight = res[0];
  }

  search1 = (text$: Observable<string>) => {
    const debouncedText$ = text$.pipe(
      debounceTime(200),
      distinctUntilChanged()
    );
    const clicksWithClosedPopup$ = this.click1$.pipe(
      filter(() => !this.instanceFrom.isPopupOpen())
    );
    const inputFocus$ = this.focus1$;

    return merge(debouncedText$, inputFocus$, clicksWithClosedPopup$).pipe(
      map(term =>
        (term === ""
          ? this.cityNames
          : this.cityNames.filter(
              v => v.toLowerCase().indexOf(term.toLowerCase()) > -1
            )
        ).slice(0, 10)
      )
    );
  };
  search2 = (text$: Observable<string>) => {
    const debouncedText$ = text$.pipe(
      debounceTime(200),
      distinctUntilChanged()
    );
    const clicksWithClosedPopup$ = this.click2$.pipe(
      filter(() => !this.instanceFrom.isPopupOpen())
    );
    const inputFocus$ = this.focus2$;

    return merge(debouncedText$, inputFocus$, clicksWithClosedPopup$).pipe(
      map(term =>
        (term === ""
          ? this.cityNames
          : this.cityNames.filter(
              v => v.toLowerCase().indexOf(term.toLowerCase()) > -1
            )
        ).slice(0, 10)
      )
    );
  };

  onDateSelection(date: NgbDate) {
    if (!this.dateFrom && !this.dateTo) {
      this.dateFrom = date;
    } else if (this.dateFrom && !this.dateTo && date.after(this.dateFrom)) {
      this.dateTo = date;
    } else {
      this.dateTo = null;
      this.dateFrom = date;
    }
  }

  isHovered(date: NgbDate) {
    return (
      this.dateFrom &&
      !this.dateTo &&
      this.hoveredDate &&
      date.after(this.dateFrom) &&
      date.before(this.hoveredDate)
    );
  }

  isInside(date: NgbDate) {
    return date.after(this.dateFrom) && date.before(this.dateTo);
  }

  isRange(date: NgbDate) {
    return (
      date.equals(this.dateFrom) ||
      date.equals(this.dateFrom) ||
      this.isInside(date) ||
      this.isHovered(date)
    );
  }
}
