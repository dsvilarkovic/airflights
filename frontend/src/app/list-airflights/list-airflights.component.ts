import { Vehicle } from './../vehicle';
import { Component, OnInit, ViewChildren } from '@angular/core';

@Component({
  selector: 'app-list-airflights',
  templateUrl: './list-airflights.component.html',
  styleUrls: ['./list-airflights.component.scss']
})
export class ListAirflightsComponent implements OnInit {

  vehicles : Array<Vehicle>;
  vehicles2 :Vehicle[] = [];

  
 
  constructor() { }

  ngOnInit() {
  }
  

}
