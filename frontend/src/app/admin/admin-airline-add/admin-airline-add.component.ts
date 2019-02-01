import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { RentacarService } from 'src/services/rentacar.service';
import { AirlineService } from 'src/services/airline.service';
import { NgForm } from '@angular/forms';
import { Airline } from 'src/app/airline';

@Component({
  selector: 'app-admin-airline-add',
  templateUrl: './admin-airline-add.component.html',
  styleUrls: ['./admin-airline-add.component.scss']
})
export class AdminAirlineAddComponent implements OnInit {

  airline: Airline = new Airline();

  constructor(private route: ActivatedRoute, private router: Router, private aService: AirlineService) { }

  ngOnInit() {
  }

  save(form: NgForm) {
    this.aService.save(this.airline).subscribe(result => {
      this.router.navigate(['admin/flights']);
    }, error => console.error(error));
  }

}
