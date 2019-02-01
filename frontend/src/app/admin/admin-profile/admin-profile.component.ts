import { Component, OnInit } from '@angular/core';
import { TokenStorageService } from 'src/services/auth/token-storage.service';
import { AdminsService } from 'src/services/admins.service';
import { ROLE_A } from 'src/app/globals';
import { Location } from '@angular/common';

@Component({
  selector: 'app-admin-profile',
  templateUrl: './admin-profile.component.html',
  styleUrls: ['./admin-profile.component.scss']
})
export class AdminProfileComponent implements OnInit {

  constructor(private ts: TokenStorageService, private location: Location, private adminService: AdminsService) { }

  ngOnInit() {

    if (this.ts.getAuthorities().includes(ROLE_A)) {
      
    } else {
      alert("Unauthorized");
      this.location.back();
    }
    
  }

}
