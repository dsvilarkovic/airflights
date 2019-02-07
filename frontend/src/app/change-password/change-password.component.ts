import { LoginService } from './../../services/login.service';
import { LoginComponent } from './../login/login/login.component';
import { TokenStorageService } from './../../services/auth/token-storage.service';
import { Component, OnInit } from '@angular/core';
import { FormControl } from '@angular/forms';
import { User } from '../user';
import { Router } from '@angular/router';
import { AuthService } from 'src/services/auth/auth.service';
import { ROLE_H, ROLE_SYS } from 'src/app/globals';

@Component({
  selector: 'app-change-password',
  templateUrl: './change-password.component.html',
  styleUrls: ['./change-password.component.scss']
})
export class ChangePasswordComponent implements OnInit {


  new = new FormControl("");
  repeat = new FormControl("");
  id;
  user: User = new User();
  idCompany;
  roles: string[] = [];
  repeatStr: string;

  constructor(private authService: AuthService, private router: Router, private tokenStorage: TokenStorageService, private loginService:LoginService) { }

  ngOnInit() {
    this.roles = this.tokenStorage.getAuthorities();
  }

  confirm() {

    this.repeatStr = this.repeat.value;
    this.id = this.tokenStorage.getUser();
    this.loginService.setNewPassword(this.repeatStr,this.id).subscribe(data => {
      this.user = data;
      this.loginService.getLoggedByIdCompany(this.id).subscribe(data => {
        this.idCompany = data;
        if(this.roles.includes('ROLE_USER')) {
          this.router.navigate(['/home']);
        } else if(this.roles.includes(ROLE_SYS)) {
          this.router.navigate(['/admin/profile']);
        } else if(this.roles.includes('ROLE_RENTACARADMIN')) {
          this.router.navigate(['/rentacar/'+ this.idCompany]);
        } else if(this.roles.includes(ROLE_H)) {
          this.router.navigate(['/admin/hotel/profile']);
        }
      })
    })

  }

}
