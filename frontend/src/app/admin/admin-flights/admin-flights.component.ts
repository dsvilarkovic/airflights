import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { AirlineService } from 'src/services/airline.service';

@Component({
  selector: 'app-admin-flights',
  templateUrl: './admin-flights.component.html',
  styleUrls: ['./admin-flights.component.scss']
})
export class AdminFlightsComponent implements OnInit {

  airlines: Array<any>;

  constructor(private route: ActivatedRoute, private router: Router, private aService: AirlineService) { }

  ngOnInit() {
    this.aService.getAllAirlines().subscribe(data => {
      this.airlines = data;
    }, error => console.error(error));
  }

  delete(id: number) {
    this.aService.remove(id).subscribe( r => {
      window.location.reload();
    }, error => console.error(error));
  }

}
