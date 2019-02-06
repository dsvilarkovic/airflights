import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { HotelService } from 'src/services/hotel.service';
import { ROLE_SYS } from 'src/app/globals';
import { TokenStorageService } from 'src/services/auth/token-storage.service';

@Component({
  selector: 'app-admin-hotels',
  templateUrl: './admin-hotels.component.html',
  styleUrls: ['./admin-hotels.component.scss']
})
export class AdminHotelsComponent implements OnInit {

  hotels: Array<any>;
  readonly type: string = "H";

  constructor(private route: ActivatedRoute, private router: Router, private ts: TokenStorageService, private hotelService: HotelService) { }

  ngOnInit() {
    // TODO - autentifikacija
    if (!this.ts.getAuthorities().includes(ROLE_SYS)) {
      alert("Unauthorized");
      this.router.navigate(['/home']);
    }
    
    this.hotelService.getAll().subscribe(data => {
      this.hotels = data;
    }, error => console.error(error));
  }

  delete(id: number) {
    this.hotelService.remove(id).subscribe( r => {
      window.location.reload();
    }, error => console.error(error));
  }

  logout() {
    this.ts.signOut();
    this.router.navigate(['/login']);
  }

}
