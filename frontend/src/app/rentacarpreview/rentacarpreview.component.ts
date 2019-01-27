import { Vehicle } from './../vehicle';
import { Component, OnInit } from '@angular/core';
import {NgbDateStruct, NgbCalendar} from '@ng-bootstrap/ng-bootstrap'

import { RentacarService } from 'src/services/rentacar.service';

@Component({
  selector: 'app-rentacarpreview',
  templateUrl: './rentacarpreview.component.html',
  styleUrls: ['./rentacarpreview.component.scss']
})
export class RentacarpreviewComponent implements OnInit {

  modelPickUp: NgbDateStruct;
  modelDropOff: NgbDateStruct;
  searchAttemp: String;
  searchFlag: boolean = false;
  vehicles : Array<Vehicle>;
  vehicles2 :Vehicle[] = [];
  date: {year: number, month: number};

  constructor(private calendar: NgbCalendar, private racService: RentacarService) { }

  ngOnInit() {
  }

  search() {

/*
    this.racService.searchRentACars(this.searchAttemp).subscribe(data => {
      this.vehicles = data;
      alert("USao!")
      for(let v of this.vehicles) {
            this.vehicles2.push(v);
      }
    })
*/
  }

}
