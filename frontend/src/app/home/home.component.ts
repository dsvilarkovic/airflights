import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { TokenStorageService } from 'src/services/auth/token-storage.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss']
})
export class HomeComponent implements OnInit {

  id;
  loggedFlag: boolean = false;
  boolLogIn: boolean = false;
  boolLogOff: boolean = false;

  constructor(private router: Router, private token: TokenStorageService) { }

  ngOnInit() {
    
    this.id = this.token.getUser();
    if(this.id == null) {
      this.loggedFlag = false;
      this.boolLogIn = true;
      this.boolLogOff = false;
    } else {
      this.loggedFlag = true;
      this.boolLogIn = false;
      this.boolLogOff = true;
      this.router.navigate(['/authHomePage/' + this.id])
    }
  }

  logout() {
    this.token.signOut();
    this.router.navigate(['/login']);
  }

}
