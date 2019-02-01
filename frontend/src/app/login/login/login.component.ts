import { TokenStorageService } from './../../../services/auth/token-storage.service';
import { AuthService } from './../../../services/auth/auth.service';
import { AuthLoginInfo } from './../../forms/loginForm';
import { Token } from './../../Token';
import { LoginService } from './../../../services/login.service';
import { User } from './../../user';
import { Component, OnInit, Input } from '@angular/core';
import { Router } from '@angular/router';
import { NgForm } from '@angular/forms';
import { NgModule } from '@angular/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { ROLE_H, ROLE_SYS } from 'src/app/globals';
import { allSettled } from 'q';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {

  user: User = new User();
  @Input() verified : boolean;
  private loginInfo : AuthLoginInfo;
  isLoggedIn = false;
  isLoginFailed = false;
  errorMessage = '';
  roles: string[] = [];
  token: Token = new Token();
  constructor(private authService: AuthService, private router: Router, private tokenStorage: TokenStorageService) { }

  ngOnInit() {
  }

  login() {
    this.loginInfo = new AuthLoginInfo(this.user.email,this.user.password);
    this.authService.attemptAuth(this.loginInfo).subscribe(data => {

     
      if(data.accessToken === undefined) {
        alert("Pokusaj logovanja neverifikovanog korsinika! Potrebno je verifikovati nalog na svom email servisu!")
      } else {
        this.tokenStorage.saveToken(data.accessToken);
        this.tokenStorage.saveUsername(data.username);
        this.tokenStorage.saveAuthorities(data.authorities);
        this.tokenStorage.saveUser(data.user_id);

        this.isLoginFailed = false;
        this.isLoggedIn = true;
        this.roles = this.tokenStorage.getAuthorities();
        localStorage["sent"] = false;

        //alert(this.roles);

        if(this.roles.includes('ROLE_USER')) {
          this.router.navigate(['/home']);
        } else if(this.roles.includes(ROLE_SYS)) {
          this.router.navigate(['/admin/profile']);
        } else if(this.roles.includes('ROLE_RENTACARADMIN')) {
          this.router.navigate(['/rentacar/']);
        } else if(this.roles.includes(ROLE_H)) {
          this.router.navigate(['/admin/hotel/profile']);
        }
      }


    },
    error => {
      console.log(error);
      this.isLoginFailed = true;
      
    });
    this.user = new User();
  }

    /*his.loginService.login(this.user).subscribe(data => {
      this.token = data;
      localStorage.setItem("jwtToken", this.token.accessToken);
      alert("Sve je ok. User je: " + this.user.firstName + " " + this.user.role)
      if(this.user.role == 3) {
        alert("Ide na home page admina za " + this.user.role);
        this.router.navigate(['/rentacar']);
      } else {
        alert("Ide na home page korisnika!");
      }
      
      console.log(this.user);
    });

    this.user = new User();
  }*/
}
