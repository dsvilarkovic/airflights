import { Component, OnInit } from '@angular/core';
import { ROLE_SYS } from 'src/app/globals';
import { AdminsService } from 'src/services/admins.service';
import { Router, ActivatedRoute } from '@angular/router';
import { TokenStorageService } from 'src/services/auth/token-storage.service';
import { Admin } from 'src/app/admin';
import { NgForm } from '@angular/forms';
import { Role } from 'src/app/role';

@Component({
  selector: 'app-admin-system-add',
  templateUrl: './admin-system-add.component.html',
  styleUrls: ['./admin-system-add.component.scss']
})
export class AdminSystemAddComponent implements OnInit {

  constructor(private route: ActivatedRoute, private aService: AdminsService, private ts: TokenStorageService, private router: Router, private aaService: AdminsService) { }

  admin: Admin = new Admin();
  
  ngOnInit() {

    if (!this.ts.getAuthorities().includes(ROLE_SYS)) {
      alert("Unauthorized");
      this.router.navigate(['/home']);
      
    }
  }

  save(form: NgForm) {
    let role : Role = new Role();
    this.admin.verify = true;
    var regex = /^[0-9A-Za-z]+@[A-Za-z]+\.[A-Za-z]{2,3}$/g
    if (!regex.test(this.admin.email)) {
      alert("Not valid email format!")
      return;
    }

    role.name = ROLE_SYS;
    role.id = 2;
    this.admin.role = role;
    this.aService.save(this.admin).subscribe( r => {
      this.router.navigate(['/admin/admins']);
    }, error => console.error(error));
  }

}
