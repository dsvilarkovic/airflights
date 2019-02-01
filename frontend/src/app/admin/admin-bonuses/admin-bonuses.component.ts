import { Component, OnInit } from '@angular/core';
import { ROLE_SYS } from 'src/app/globals';
import { TokenStorageService } from 'src/services/auth/token-storage.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-admin-bonuses',
  templateUrl: './admin-bonuses.component.html',
  styleUrls: ['./admin-bonuses.component.scss']
})
export class AdminBonusesComponent implements OnInit {

  constructor(private ts: TokenStorageService, private router: Router) { }

  ngOnInit() {
    if (!this.ts.getAuthorities().includes(ROLE_SYS)) {
      alert("Unauthorized");
      this.router.navigate(['/home']);
    }
  }

}
