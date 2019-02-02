import { Subscription } from 'rxjs';
import { Vehicle } from './../vehicle';
import { Component, OnInit, ViewChildren } from '@angular/core';
import { RentacarService } from 'src/services/rentacar.service';


@Component({
  selector: 'app-list-airflights',
  templateUrl: './list-airflights.component.html',
  styleUrls: ['./list-airflights.component.scss']
})
export class ListAirflightsComponent implements OnInit {

  vehicles : Array<Vehicle>;
  vehicles2 :Vehicle[] = [];
  vehicles3 :Vehicle[] = [];
 /* filtered: any[];
  subscription: Subscription;



  tableResource: DataTableResource<Vehicle>;
  items: Vehicle[] = [];
  itemCount: number;*/
 
    constructor(private racService: RentacarService) { }
 /*   this.subscription = this.racService.getAllVehicles().subscribe(data => {
      this.filtered = this.vehicles2 = data;
      this.initializeTable(data);
      
    });

  }

  private initializeTable(vehicles4: Vehicle[]) {

    this.tableResource = new DataTableResource(vehicles4);
    this.tableResource.query( {
      offset: 0
    }).then(items => this.items = items);
    this.tableResource.count()
    .then(count => this.itemCount = count);
  }

  reloadItems(params) {
    if(!this.tableResource) return;
    this.tableResource.query(params).then(items => this.items = items);
  }*/

  ngOnInit() {

    this.racService.getAllVehicles().subscribe(data2 => {
      this.vehicles = data2;
      for(let v of this.vehicles) {
        this.vehicles2.push(v);
      }
    })
  }
  

}
