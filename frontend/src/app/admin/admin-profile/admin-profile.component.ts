import { Component, OnInit } from '@angular/core';
import { TokenStorageService } from 'src/services/auth/token-storage.service';

@Component({
  selector: 'app-admin-profile',
  templateUrl: './admin-profile.component.html',
  styleUrls: ['./admin-profile.component.scss']
})
export class AdminProfileComponent implements OnInit {

  constructor(private token: TokenStorageService) { }

  ngOnInit() {
    alert(this.token.getUser());
  }

}
