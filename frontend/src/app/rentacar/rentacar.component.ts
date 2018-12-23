import { TokenStorageService } from './../../services/auth/token-storage.service';
import { User } from './../user';
import { Component, OnInit } from '@angular/core';
import { RentacarService } from 'src/services/rentacar.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-rentacar',
  templateUrl: './rentacar.component.html',
  styleUrls: ['./rentacar.component.scss']
})
export class RentacarComponent implements OnInit {

  rac : Array<any>;
  
  constructor(private racService: RentacarService, private token: TokenStorageService, private router: Router) { }

  ngOnInit() {
    
      this.racService.getAll().subscribe(data=> {
        this.rac = data;
       
      })
   
    
  }

  logout() {
    
    this.token.signOut();
    this.router.navigate(['/login']);
  }



}
