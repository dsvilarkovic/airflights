import { LoginService } from './../../services/login.service';
import { Component, OnInit } from '@angular/core';
import { Route } from '@angular/compiler/src/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-error',
  templateUrl: './error.component.html',
  styleUrls: ['./error.component.scss']
})
export class ErrorComponent implements OnInit {

  constructor(private router : Router) { }

  ngOnInit() {
  }

  login() {
    this.router.navigate(['login']);
  }

}
