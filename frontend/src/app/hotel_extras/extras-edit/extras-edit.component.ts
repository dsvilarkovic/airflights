import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { HotelService } from 'src/services/hotel.service';
import { HotelExtrasService } from 'src/services/hotel-extras.service';
import { NgForm } from '@angular/forms';
import { ROLE_H } from 'src/app/globals';
import { TokenStorageService } from 'src/services/auth/token-storage.service';

@Component({
  selector: 'app-extras-edit',
  templateUrl: './extras-edit.component.html',
  styleUrls: ['./extras-edit.component.scss']
})
export class ExtrasEditComponent implements OnInit {

  extra: any = {};
  hotel: any = {};
  extraName : string;

  constructor(private route: ActivatedRoute, private router: Router, private ts:TokenStorageService, private hotelservice : HotelService, private extraService: HotelExtrasService) { }


  ngOnInit() {
    
    if (!this.ts.getAuthorities().includes(ROLE_H)) {
      alert("Unauthorized");
      this.router.navigate(['/home']);
    }

    const id = this.route.snapshot.params['id'];
    this.extraService.get(id).subscribe(data =>{
      this.extra = data;
      this.extraName = this.extra.name;
    });

    const id_h = this.route.snapshot.params['idh'];
    this.hotelservice.get(id_h).subscribe(data =>{
      this.hotel = data;
    });
  }

  gotoList() {
    this.router.navigate(['/hotel/edit/extras/list']);
  }

  update(from : NgForm) {
    this.extraService.update(this.extra).subscribe(result => {
      this.gotoList();
    }, error => console.error(error));
  }

}
