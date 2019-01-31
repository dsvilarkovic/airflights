import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { HotelService } from 'src/services/hotel.service';
import { Admin } from 'src/app/admin';
import { Hotel } from 'src/app/hotel';
import { NgForOfContext } from '@angular/common';
import { NgForm } from '@angular/forms';
import { Role } from 'src/app/role';
import { ROLE_H, ROLE_R } from 'src/app/globals';
import { AdminsService } from 'src/services/admins.service';
import { RentacarService } from 'src/services/rentacar.service';

@Component({
  selector: 'app-admin-add',
  templateUrl: './admin-add.component.html',
  styleUrls: ['./admin-add.component.scss']
})
export class AdminAddComponent implements OnInit {

  company: any;
  admin: Admin = new Admin();
  type: string;

  constructor(private route: ActivatedRoute, private router: Router, private hotelservice : HotelService, 
    private aService: AdminsService, private racService: RentacarService ) { }


  ngOnInit() {
    this.type = this.route.snapshot.params['type'];
    const id = this.route.snapshot.params['id'];
    switch(this.type) {
      case 'A':
        this.racService.getOne(id).subscribe(data =>{
          this.company = data;
        });
      break;
      case 'H':
        this.hotelservice.get(id).subscribe(data =>{
          this.company = data;
        });
      break;
      case 'R':
      this.racService.getOne(id).subscribe(data =>{
        this.company = data;
      });
      break;
    }
  }

  save(form: NgForm) {
    let role : Role = new Role();
    this.admin.verify = false;
    switch(this.type) {
      case 'A': 
      
      break;
      case 'H': 
        role.name = ROLE_H;
        role.id = 4;
        this.admin.role = role;
        this.admin.hotel = this.company;
        this.aService.save(this.admin).subscribe( r => {
          this.router.navigate(['/admin/hotels']);
        }, error => console.error(error));
      break;

      case 'R': 
        role.name = ROLE_R;
        role.id = 5;
        this.admin.role = role;
        this.admin.idCompany = this.company.id;
        this.aService.save(this.admin).subscribe( r => {
          this.router.navigate(['/admin/rac']);
        }, error => console.error(error));
      break;
    }





  }

}
