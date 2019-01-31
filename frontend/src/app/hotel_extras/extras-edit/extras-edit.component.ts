import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { HotelService } from 'src/services/hotel.service';
import { HotelExtrasService } from 'src/services/hotel-extras.service';
import { NgForm } from '@angular/forms';

@Component({
  selector: 'app-extras-edit',
  templateUrl: './extras-edit.component.html',
  styleUrls: ['./extras-edit.component.scss']
})
export class ExtrasEditComponent implements OnInit {

  extra: any = {};
  hotel: any = {};
  extraName : string;

  constructor(private route: ActivatedRoute, private router: Router, private hotelservice : HotelService, private extraService: HotelExtrasService) { }


  ngOnInit() {
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
    this.router.navigate(['/hotel/edit/' + this.hotel.id + '/extras']);
  }

  update(from : NgForm) {
    this.extraService.update(this.extra).subscribe(result => {
      this.gotoList();
    }, error => console.error(error));
  }

}
