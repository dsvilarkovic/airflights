import { TokenStorageService } from 'src/services/auth/token-storage.service';
import { Route } from '@angular/compiler/src/core';
import { Vehicle } from './../vehicle';
import { Component, OnInit } from '@angular/core';
import {NgbDateStruct, NgbCalendar} from '@ng-bootstrap/ng-bootstrap'
import { RentacarService } from 'src/services/rentacar.service';
import { rentacar } from '../rentacar';
import { Branch } from '../branch';
import { Router } from '@angular/router';
import { ROLE_USER } from '../globals';

@Component({
  selector: 'app-rentacar-preview',
  templateUrl: './rentacar-preview.component.html',
  styleUrls: ['./rentacar-preview.component.scss']
})
export class RentacarPreviewComponent implements OnInit {

  modelPickUp: NgbDateStruct;
  modelDropOff: NgbDateStruct;
  searchAttemp: String;
  searchFlag: boolean = false;
  rentacars: Array<rentacar>;
  rentacars2: rentacar[] = [];
  branches: Array<Branch>;
  branches2: Branch[] = [];
  vehicles : Array<Vehicle>;
  vehicles2 :Vehicle[] = [];
  date: {year: number, month: number};

  //boolean flagovi za promenu stanja
  branchFlag: boolean = false;
  boolLogOff: boolean = false;
  boolLog: boolean = false;

  constructor(private calendar: NgbCalendar, private racService: RentacarService, private router: Router, private token: TokenStorageService) { }

  logged: boolean = false;
  ngOnInit() {

    if (this.token.getAuthorities().includes(ROLE_USER)) {
      this.logged = true;
    } else {
      this.logged = false;
    }

    this.racService.getAll().subscribe(data => {
      this.rentacars = data;
      for(let v of this.rentacars) {
        this.rentacars2.push(v);
      }
    })

    this.racService.getAllBranches().subscribe(data => {
      this.branches = data;
      for(let b of this.branches) {
        this.branches2.push(b);
      }
    })

    if(this.token.getUser() == null) {
      this.boolLogOff = false;
      this.boolLog = true;
    } else {
      this.boolLogOff = true;
      this.boolLog = false;
    }


  }

  search() {
    this.rentacars2 = [];

     this.racService.search(this.searchAttemp).subscribe(data => {
      this.rentacars = data;
      for(let v of this.rentacars) {
            this.rentacars2.push(v);
      }
    })
  }

  btnVisit(id) {
    this.router.navigate(['/rentacarPreview/'+id]);
  }

  
  logout() {
    this.token.signOut();
    this.router.navigate(['/login']);
  }

  login() {
    this.router.navigate(['/login']);
  }
}


