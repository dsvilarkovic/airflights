import { TokenStorageService } from './../../services/auth/token-storage.service';
import { ModalService } from './../../services/modal.service';
import { rentacar } from './../rentacar';
import { ActivatedRoute, Router } from '@angular/router';
import { RentacarService } from './../../services/rentacar.service';
import { Component, OnInit } from '@angular/core';
import { Vehicle } from '../vehicle';
import { Branch } from '../branch';
import { FormControl } from '@angular/forms';

@Component({
  selector: 'app-racprofile',
  templateUrl: './racprofile.component.html',
  styleUrls: ['./racprofile.component.scss']
})
export class RacprofileComponent implements OnInit {

  id;
  rac: rentacar = new rentacar();
  vehicles : Array<Vehicle>;
  vehicles2 :Vehicle[] = []; //ovde se nalaze sva vozila iz izabranog servisa
  branches: Array<Branch>;
  branches2: Branch[] = []; // ovde se nalaze sve filijale(lokacije) iz izabranog rent a car servisa
  naziv: string;
  adresa: string;
  opis: string;
  
  newVehicle: Vehicle = new Vehicle();
  currentVehicle: Vehicle = new Vehicle();
  updateV: Vehicle = new Vehicle();
  tempVehicle: Vehicle = new Vehicle();
  updateFlag : boolean = false;
  deleteFlag : boolean = false;
  updateBranchFlag : boolean = false;
  deleteBranchFlag : boolean = false;
  newBranch: Branch = new Branch();
  currentBranch: Branch = new Branch();
  updateB: Branch = new Branch();
  tempBranch: Branch = new Branch();

  checkReserved: boolean = false;

//za update
  name = new FormControl("");
  brand = new FormControl("");
  model = new FormControl("");
  year = new FormControl("");
  seats = new FormControl("");
  price = new FormControl("");
  type = new FormControl("");

  branchAddress = new FormControl("");
  city = new FormControl("");
//za error page
niz : Array<any>;
  pom : String;

  //promena 30.12.2018
  nizBranches : Array<any>;

  constructor(private racService : RentacarService, private route : ActivatedRoute, private modalService: ModalService, private router: Router, private token: TokenStorageService) { }

  ngOnInit() {
    this.niz = this.token.getAuthorities();
    this.pom = JSON.stringify(this.niz);
    if(this.pom == "[\"ROLE_SYSTEMADMIN\"]" || this.pom == "[\"ROLE_RENTACARADMIN\"]") {
      this.id = this.route.snapshot.params.id;
      this.racService.getOne(this.id).subscribe(data => {
      this.rac = data;
      this.naziv = this.rac.name;
      this.adresa = this.rac.address;
      this.opis = this.rac.description;
      this.racService.getAllVehicles().subscribe(data2 => {
        this.vehicles = data2;
        this.compareVehicle();
      })
      this.racService.getAllBranches().subscribe(data3 => {
        this.branches = data3;
        this.compareBranches();
      })


    })
    } else {
      alert("Niko nije ulogovan, ne moze se pristupiti ovoj stranici!");
      this.router.navigate(['/error45']);
    }
    

  }

  compareVehicle() {
    for(let v of this.vehicles) {
      if(v.rentacar.id === this.rac.id) {
          this.vehicles2.push(v);
      }
    }
  }

  compareBranches() {
    for(let b of this.branches) {
      if(this.rac.id === b.rentacar.id) {
        this.branches2.push(b);
      }
    }
  }


  addVehicle() {
    this.newVehicle.rentacar = this.rac;
   // this.newVehicle.branch_locations = this.newVehicle.branch_locations;
    //alert("Filijala: " + this.newVehicle.branch_locations.id);
    this.racService.addVehicle(this.newVehicle).subscribe(data => {
        alert("Dodao sam novo vozilo!");
        this.tempVehicle = data;
        this.newVehicle.id = this.tempVehicle.id;
        this.vehicles2.push(this.tempVehicle);
    });
  }

  updateVehicle(v : Vehicle) {
    this.updateFlag = true;
    this.currentVehicle = v;
    this.updateV.id = this.currentVehicle.id;
    this.name.setValue(this.currentVehicle.name);
    this.brand.setValue(this.currentVehicle.brand);
    this.model.setValue(this.currentVehicle.model);
    this.year.setValue(this.currentVehicle.year);
    this.seats.setValue(this.currentVehicle.seats);
    this.price.setValue(this.currentVehicle.price);
    this.type.setValue(this.currentVehicle.type);
  }

  update() {
  
    this.updateV.name = this.name.value;
    this.updateV.brand = this.brand.value;
    this.updateV.model = this.model.value;
    this.updateV.year = this.year.value;
    this.updateV.seats = this.seats.value;
    this.updateV.price = this.price.value;
    this.updateV.type = this.type.value;
    this.updateV.rentacar = this.rac;
    this.updateV.branch_locations = this.currentVehicle.branch_locations;

    this.racService.updateVehicle(this.updateV).subscribe(data => {
      
      this.tempVehicle = data;
      alert("thslkjtha " + this.tempVehicle);
      if(this.tempVehicle == null) {
        alert("Vozilo je rezervizano i nije moguce izmena");
      } else {
        this.currentVehicle.id = this.tempVehicle.id;
        this.currentVehicle.name = this.tempVehicle.name;
        this.currentVehicle.brand = this.tempVehicle.brand;
        this.currentVehicle.model = this.tempVehicle.model;
        this.currentVehicle.year = this.tempVehicle.year;
        this.currentVehicle.seats = this.tempVehicle.seats;
        this.currentVehicle.price = this.tempVehicle.price;
        this.currentVehicle.type = this.tempVehicle.type;
        this.currentVehicle.branch_locations = this.tempVehicle.branch_locations;
      }
      
     
    
      
    })
  }

  deleteVehicle(v: Vehicle) {
    this.deleteFlag = true;
    this.currentVehicle = v;
  }
  delete() {
    this.racService.deleteVehicle(this.currentVehicle.id).subscribe(data => {
      this.checkReserved = data;
      
      alert("Uspesno obrisao: ");
      
    })

    if(this.checkReserved == false) {
      alert("Automobil je reservisan!");
    } else {
      const index: number = this.vehicles2.indexOf(this.currentVehicle);
      if (index !== -1) {
        this.vehicles2.splice(index, 1);
    }   
    }
    

  }

  addBranch() {
    this.newBranch.rentacar = this.rac;
    this.racService.addBranch(this.newBranch).subscribe(data => {
        alert("Dodao sam novu filijalu!");
        this.tempBranch = data;
        this.newBranch.id = this.tempBranch.id;
        this.branches2.push(this.newBranch);
    });
  }

  updateBranch(b : Branch) {
    this.updateBranchFlag = true;
    this.currentBranch = b;
    this.updateB.id = this.currentBranch.id;
    this.branchAddress.setValue(this.currentBranch.address);
    this.city.setValue(this.currentBranch.city);
    
  }

  updateBranch2() {

    alert("Ovde???");
    this.updateB.address = this.branchAddress.value;
    this.updateB.city = this.city.value;

    this.racService.updateBranch(this.updateB).subscribe(data => {
      this.tempBranch = data;
      this.currentBranch.address = this.tempBranch.address;
      this.currentBranch.city = this.tempBranch.city;

    })

  }

  deleteBranch(b: Branch) {
    this.deleteBranchFlag = true;
    this.currentBranch = b;
  }
  deleteBranch2() {
    this.racService.deleteBranch(this.currentBranch.id).subscribe(data => {
      alert("Uspesno obrisao: ");
      
    })

    const index: number = this.branches2.indexOf(this.currentBranch);
      if (index !== -1) {
        this.branches2.splice(index, 1);
    }   

  }


  logout() {
    this.token.signOut();
    this.router.navigate(['/login']);
  }


}
