import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { HotelExtrasService } from 'src/services/hotel-extras.service';
import { HotelService } from 'src/services/hotel.service';
import { RoomService } from 'src/services/room.service';
import { PromoRoom } from 'src/app/PromoRoom';

@Component({
  selector: 'app-promotion-init',
  templateUrl: './promotion-init.component.html',
  styleUrls: ['./promotion-init.component.scss']
})
export class PromotionInitComponent implements OnInit {

  hotel: any;
  room: any;
  extras: Array<any>;

  selectedItems = [];
  dropdownSettings = {};
  
  constructor(private route: ActivatedRoute, private router: Router, 
    private extraService: HotelExtrasService, private hotelService: HotelService, private roomService: RoomService) { }


  ngOnInit() {
    const hotelId : number = this.route.snapshot.params['idh'];
    this.hotelService.get(hotelId).subscribe(data =>{
      this.hotel = data;
    });
    this.extraService.getHotelExtras(hotelId).subscribe(data => {
      this.extras = data;
    });
    const roomId : number = this.route.snapshot.params['id'];
    this.roomService.get(roomId).subscribe(data => {
      this.room = data;
    });

    this.dropdownSettings = {
      singleSelection: false,
      idField: 'id',
      textField: 'name',
      selectAllText: 'Select All',
      unSelectAllText: 'UnSelect All',
      itemsShowLimit: 5,
      allowSearchFilter: true
    };
  }

  onItemSelect(item: any) {
    this.selectedItems.push(item);
  }

  onSelectAll(items: any) {
    this.selectedItems = items;
  }

  onItemDeselect(item: any) {
    let ind = this.selectedItems.indexOf(item);
    this.selectedItems.splice(ind, 1);
    alert(this.selectedItems);
  }

  onDeselectAll(items: any) {
    this.selectedItems = [];
  }

  gotoList() {
    this.router.navigate(['/hotel/edit/' + this.hotel.id + '/rooms']);
  }

  save(form: NgForm) {
    
    this.selectedItems.forEach(element => {
      let promoExtra: PromoRoom = new PromoRoom();
      promoExtra.room = this.room;
      promoExtra.extra = element; 
      this.roomService.addPromoExtra(promoExtra).subscribe(result => {
        //this.gotoList();
      }, error => console.error(error));
    });

    this.room.promo = true;
    this.roomService.update(this.room).subscribe(result => {
      this.gotoList();
    }, error => console.error(error));

  }

}
