import { User } from './../user';
import { Component, OnInit } from '@angular/core';
import { RentacarService } from 'src/services/rentacar.service';

@Component({
  selector: 'app-rentacar',
  templateUrl: './rentacar.component.html',
  styleUrls: ['./rentacar.component.scss']
})
export class RentacarComponent implements OnInit {

  rac : Array<any>;
  
  constructor(private racService: RentacarService) { }

  ngOnInit() {
    
      this.racService.getAll().subscribe(data=> {
        this.rac = data;
        alert("OKK");
      })
   
    
  }



}
