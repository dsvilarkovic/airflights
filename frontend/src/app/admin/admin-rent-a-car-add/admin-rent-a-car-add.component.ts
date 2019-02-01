import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { RentacarService } from 'src/services/rentacar.service';
import { TokenStorageService } from 'src/services/auth/token-storage.service';
import { rentacar } from 'src/app/rentacar';
import { NgForm } from '@angular/forms';

@Component({
  selector: 'app-admin-rent-a-car-add',
  templateUrl: './admin-rent-a-car-add.component.html',
  styleUrls: ['./admin-rent-a-car-add.component.scss']
})
export class AdminRentACarAddComponent implements OnInit {

  rac: rentacar = new rentacar();

  constructor(private route: ActivatedRoute, private router: Router, private racService: RentacarService,  private token: TokenStorageService) { }

  ngOnInit() {

  }

  gotoList() {
    this.router.navigate(['admin/rac']);
  }

  save(form: NgForm) {
    this.racService.save(this.rac).subscribe(result => {
      this.gotoList();
    }, error => console.error(error));
  }

}
