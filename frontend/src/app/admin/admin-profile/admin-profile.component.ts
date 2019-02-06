import { Component, OnInit } from '@angular/core';
import { TokenStorageService } from 'src/services/auth/token-storage.service';
import { AdminsService } from 'src/services/admins.service';
import { ROLE_A, ROLE_SYS } from 'src/app/globals';
import { Location } from '@angular/common';
import { Route } from '@angular/compiler/src/core';
import { Router } from '@angular/router';
import { NgForm } from '@angular/forms';

@Component({
  selector: 'app-admin-profile',
  templateUrl: './admin-profile.component.html',
  styleUrls: ['./admin-profile.component.scss']
})
export class AdminProfileComponent implements OnInit {

  admin: any;
  passwordNew1 :string;
  passwordNew2 :string;
  passwordOld :string;

  pm: boolean;
  pm2: boolean;

  constructor(private ts: TokenStorageService, private router: Router, private adminService: AdminsService) { }

  ngOnInit() {
    this.pm = false;
    if (!this.ts.getAuthorities().includes(ROLE_SYS)) {
      alert("Unauthorized");
      this.router.navigate(['/home']);
    }

    this.adminService.getAdmin(this.ts.getUser()).subscribe(data => {
      this.admin = data;
    }, error => console.error(error));
    
  }

  update(form: NgForm) {
    this.adminService.updateAdminProfile(this.admin).subscribe(r => {
      window.location.reload();
    }, e => console.error(e));
  }

  changeP(form: NgForm) {

    /*if (this.passwordOld!=this.admin.password) {
      this.pm = true;
      return;
    }*/

    if (this.passwordNew1!=this.passwordNew2) {
      this.pm2 = true;
      return;
    }

    this.admin.newPassword = this.passwordNew1;
    this.adminService.updatePass(this.admin).subscribe(r => {
      window.location.reload();
    }, e => console.error(e));

  }

  focusn() {
    this.pm = false;
    this.pm2 = false;
  }

  logout() {
    this.ts.signOut();
    this.router.navigate(['/login']);
  }

}
