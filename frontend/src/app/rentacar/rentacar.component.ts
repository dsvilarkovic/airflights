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
  niz : Array<any>;
  pom : String;
  
  constructor(private racService: RentacarService, private token: TokenStorageService, private router: Router) { }

  ngOnInit() {
    this.niz = this.token.getAuthorities();
    //alert("thistoken.. " + this.niz);
    this.pom = JSON.stringify(this.niz);
    //alert("SLDFJKSDLF " + this.pom);
    if(this.pom == "[\"ROLE_SYSTEMADMIN\"]" || this.pom == "[\"ROLE_RENTACARADMIN\"]") {
      alert("Neko je ulogovan")
      this.racService.getAll().subscribe(data=> {
        this.rac = data;
       
      })
    } else {
      alert("Niko nije ulogovan")
      this.router.navigate(['/error45']);
    }
    
    
  }

  logout() {
    this.token.signOut();
    this.router.navigate(['/login']);
  }



}
