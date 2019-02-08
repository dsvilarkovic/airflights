import { Component, OnInit, Directive, EventEmitter, Input, Output, QueryList, ViewChildren } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { RoomService } from 'src/services/room.service';
import { HotelService } from 'src/services/hotel.service';
import { HotelExtrasService } from 'src/services/hotel-extras.service';
import { ROLE_H } from 'src/app/globals';
import { TokenStorageService } from 'src/services/auth/token-storage.service';
import { AdminsService } from 'src/services/admins.service';

export type SortDirection = 'asc' | 'desc' | '';
const rotate: {[key: string]: SortDirection} = { 'asc': 'desc', 'desc': '', '': 'asc' };
export const compare = (v1, v2) => v1 < v2 ? -1 : v1 > v2 ? 1 : 0;

export interface SortEvent {
  column: string;
  direction: SortDirection;
}

@Directive({
  selector: 'th[sortable]',
  host: {
    '[class.asc]': 'direction === "asc"',
    '[class.desc]': 'direction === "desc"',
    '(click)': 'rotate()'
  }
})
export class NgbdSortableHeader {

  @Input() sortable: string;
  @Input() direction: SortDirection = '';
  @Output() sort = new EventEmitter<SortEvent>();

  rotate() {
    this.direction = rotate[this.direction];
    this.sort.emit({column: this.sortable, direction: this.direction});
  }
}

@Component({
  selector: 'app-extras-list',
  templateUrl: './extras-list.component.html',
  styleUrls: ['./extras-list.component.scss']
})
export class ExtrasListComponent implements OnInit {

  EXTRAS: Array<any>;
  extras: Array<any>;
  hotel: any;

  @ViewChildren(NgbdSortableHeader) headers: QueryList<NgbdSortableHeader>;

  constructor(private ts: TokenStorageService, private adminService: AdminsService, private route: ActivatedRoute, private router: Router, 
    private extraService: HotelExtrasService, private hotelService: HotelService) { }


  ngOnInit() {

    if (!this.ts.getAuthorities().includes(ROLE_H)) {
      alert("Unauthorized");
      this.router.navigate(['/home']);
    }

    this.adminService.getAdmin(this.ts.getUser()).subscribe( r => {
      this.hotel = r.hotel;
      this.extraService.getHotelExtras(this.hotel.id).subscribe(data => {
        this.extras = data;
        this.EXTRAS = this.extras;
      })
    }, error => console.error(error));
  }

  delete(id: number) {
    this.extraService.delete(id).subscribe(result => {
      window.location.reload();
    }, error => console.error(error));
  }

  onSort({column, direction}: SortEvent) {

    this.headers.forEach(header => {
      if (header.sortable !== column) {
        header.direction = '';
      }
    });

    if (direction === '') {
      this.extras = this.EXTRAS;
    } else {
      this.extras = [...this.EXTRAS].sort((a, b) => {
        const res = compare(a[column], b[column]);
        return direction === 'asc' ? res : -res;
      });
    }
  }

  logout() {
    this.ts.signOut();
    this.router.navigate(['/login']);
  }

}
