import { LoginService } from './../../../services/login.service';
import { User } from './../../user';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { NgForm } from '@angular/forms';
import { NgModule } from '@angular/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {

  user: User = new User();
  constructor(private loginService: LoginService, private router: Router) { }

  ngOnInit() {
  }

  login() {
    this.loginService.login(this.user).subscribe(data => {
      this.user = data;
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
  }
}
