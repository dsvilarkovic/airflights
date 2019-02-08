import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { AirlineService } from 'src/services/airline.service';
import { ROLE_SYS } from 'src/app/globals';
import { TokenStorageService } from 'src/services/auth/token-storage.service';
import { AdminsService } from 'src/services/admins.service';

@Component({
  selector: 'app-admin-flights',
  templateUrl: './admin-flights.component.html',
  styleUrls: ['./admin-flights.component.scss']
})
export class AdminFlightsComponent implements OnInit {

  airlines: Array<any> = new Array();

  constructor(private route: ActivatedRoute, private ts: TokenStorageService, private router: Router, private aaService: AdminsService) { }

  ngOnInit() {
    if (!this.ts.getAuthorities().includes(ROLE_SYS)) {
      alert("Unauthorized");
      this.router.navigate(['/home']);
    }
    this.aaService.getAllAirlines().subscribe(data => {
      data.forEach(element => {
        if (element.active) {
          this.airlines.push(element)
        }
      });

    }, error => console.error(error));
  }

  delete(id: number) {
    this.aaService.removeAir(id).subscribe( r => {
      window.location.reload();
    }, error => console.error(error));
  }

  logout() {
    this.ts.signOut();
    this.router.navigate(['/login']);
  }

}