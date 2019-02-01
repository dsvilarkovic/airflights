import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { RentacarService } from 'src/services/rentacar.service';
import { ROLE_SYS } from 'src/app/globals';
import { TokenStorageService } from 'src/services/auth/token-storage.service';

@Component({
  selector: 'app-admin-rent-a-car',
  templateUrl: './admin-rent-a-car.component.html',
  styleUrls: ['./admin-rent-a-car.component.scss']
})
export class AdminRentACarComponent implements OnInit {

  racs: Array<any>;
  readonly type: string = "R";

  constructor(private route: ActivatedRoute, private ts: TokenStorageService, private router: Router, private racService: RentacarService) { }


  ngOnInit() {

    if (!this.ts.getAuthorities().includes(ROLE_SYS)) {
      alert("Unauthorized");
      this.router.navigate(['/home']);
    }
    
    this.racService.getAllRacs().subscribe(data => {
      this.racs = data;
    }, error => console.error(error));
  }

  delete(id: number) {
    this.racService.remove(id).subscribe( r => {
      window.location.reload();
    }, error => console.error(error));
  }

}
