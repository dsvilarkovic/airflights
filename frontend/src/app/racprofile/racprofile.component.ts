import { VehicleReservation } from './../vehicleReservation';
import { TokenStorageService } from './../../services/auth/token-storage.service';
import { ModalService } from './../../services/modal.service';
import { rentacar } from './../rentacar';
import { ActivatedRoute, Router } from '@angular/router';
import { RentacarService } from './../../services/rentacar.service';
import { Component, OnInit } from '@angular/core';
import { Vehicle } from '../vehicle';
import { Branch } from '../branch';
import { FormControl } from '@angular/forms';
import { ReservationServiceService } from 'src/services/reservation-service.service';
import { NgbDate, NgbCalendar } from '@ng-bootstrap/ng-bootstrap';

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

  //lista rez
  reservations: Array<VehicleReservation>;
  reservations2:VehicleReservation[] = [];

  incomeFlag: boolean = false;

  branchPom = new FormControl("");

  constructor(private racService : RentacarService, 
    private route : ActivatedRoute, 
    private modalService: ModalService, 
    private router: Router, 
    private token: TokenStorageService,
    private resService: ReservationServiceService,
    private calendar: NgbCalendar) {
      this.fromDate = calendar.getToday();
      this.toDate = calendar.getNext(calendar.getToday(), 'd', 10);
     }

  ngOnInit() {
    this.incomeFlag = false;
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

      //prikaz rezervacija u konkretnom rac servisu
      this.resService.getAllById(this.id).subscribe(data=> {
        this.reservations = data;
        for(let r of this.reservations) {
          this.reservations2.push(r);
        }
      })


    })
    } else {
      alert("Niko nije ulogovan, ne moze se pristupiti ovoj stranici!");
      this.router.navigate(['/error45']);
    }
    

  }

  income23() {
    alert("Odsdaf");
    this.incomeFlag = true;
  }

  compareVehicle() {
    for(let v of this.vehicles) {
      if(v.rentACarId == this.rac.id) {
          this.vehicles2.push(v);
      }
    }
  }

  compareBranches() {
    for(let b of this.branches) {
      if(this.rac.id == b.rentACarId) {
        this.branches2.push(b);
      }
    }
  }


  addVehicle() {
    this.newVehicle.rentACarId = this.rac.id;
    alert(this.newVehicle.branchOffice_id);
    alert(this.branchPom.value);
    this.newVehicle.branchOffice_id = this.branchPom.value;
    alert(this.newVehicle.branchOffice_id);
   // this.newVehicle.branch_locations = this.newVehicle.branch_locations;
    //alert("Filijala: " + this.newVehicle.branch_locations.id);
    this.racService.addVehicle(this.newVehicle,this.newVehicle.rentACarId,this.newVehicle.branchOffice_id).subscribe(data => {
        alert("Dodao sam novo vozilo!");
        this.tempVehicle = data;
        this.newVehicle.id = this.tempVehicle.id;
        this.newVehicle.branchOffice_id = this.tempVehicle.branchOffice_id;
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
    alert("Curr branch loc: " + this.currentVehicle.branchOffice_id)
  }

  update() {
  
    this.updateV.name = this.name.value;
    this.updateV.brand = this.brand.value;
    this.updateV.model = this.model.value;
    this.updateV.year = this.year.value;
    this.updateV.seats = this.seats.value;
    this.updateV.price = this.price.value;
    this.updateV.type = this.type.value;
    this.updateV.rentACarId = this.rac.id;
    this.updateV.branchOffice_id = this.currentVehicle.branchOffice_id;

    this.racService.updateVehicle(this.updateV).subscribe(data => {
      
      this.tempVehicle = data;
      alert("Id izmenjenog vozila " + this.tempVehicle.id);
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
        this.currentVehicle.branchOffice_id = this.tempVehicle.branchOffice_id;
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

    if(this.checkReserved == true) {
      alert("Automobil je reservisan!");
    } else {
      const index: number = this.vehicles2.indexOf(this.currentVehicle);
      if (index !== -1) {
        this.vehicles2.splice(index, 1);
    }   
    }
    

  }

  addBranch() {
    this.newBranch.rentACarId = this.rac.id;
    alert("FSLDKFJ " + this.rac.id)
    this.racService.addBranch(this.newBranch,this.rac.id).subscribe(data => {
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

  hoveredDate: NgbDate;

  fromDate: NgbDate;
  toDate: NgbDate;

  onDateSelection(date: NgbDate) {
    if (!this.fromDate && !this.toDate) {
      this.fromDate = date;
    } else if (this.fromDate && !this.toDate && date.after(this.fromDate)) {
      this.toDate = date;
    } else {
      this.toDate = null;
      this.fromDate = date;
    }
  }

  isHovered(date: NgbDate) {
    return this.fromDate && !this.toDate && this.hoveredDate && date.after(this.fromDate) && date.before(this.hoveredDate);
  }

  isInside(date: NgbDate) {
    return date.after(this.fromDate) && date.before(this.toDate);
  }

  isRange(date: NgbDate) {
    return date.equals(this.fromDate) || date.equals(this.toDate) || this.isInside(date) || this.isHovered(date);
  }

  sum: number = 0;
  pickupdate:string;
    dropoffdate:string;

  calculate() {
    this.sum = 0;
    //alert("SUma? " + this.sum);
    this.sum = 0;
    this.pickupdate = this.fromDate.year + "-" + this.fromDate.month + "-" + this.fromDate.day;
    this.dropoffdate = this.toDate.year + "-" + this.toDate.month + "-" + this.toDate.day;
   // alert("SUma? " + this.pickupdate + " " + this.dropoffdate);
    var pom : Array<VehicleReservation> = new Array<VehicleReservation>();
    this.resService.getAllByDate(this.pickupdate,this.dropoffdate,this.id).subscribe(data => {
      pom = data;
      for(let r of pom) {
       
        this.sum += r.price;
      
      }
     // alert("SUma? " + this.sum);
    })

  }

 

  logout() {
    this.token.signOut();
    this.router.navigate(['/login']);
  }


}
