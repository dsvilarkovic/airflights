import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { AdminsService } from 'src/services/admins.service';

@Component({
  selector: 'app-admins',
  templateUrl: './admins.component.html',
  styleUrls: ['./admins.component.scss']
})
export class AdminsComponent implements OnInit {

  admins: Array<any>;

  constructor(private route: ActivatedRoute, private router: Router, private adminservice : AdminsService ) { }

  ngOnInit() {
    this.adminservice.getAll().subscribe(data =>{
      this.admins = data;
      this.admins.forEach(element => {
        
        switch(element.role.id) {
          case 1: element.kind="User"; 
          
          break;
          case 2: element.kind="System admin"; 
          
          break;
          case 3: 
            element.kind="Flight"; 
            if (element.airline.fullName != null && element.airline.fullName != null) {
              element.com = element.airline.fullName; 
            }
            break;
          case 4: 
            element.kind="Hotel"; 
            if (element.hotel.name != null && element.hotel.name != null) {
              element.com = element.hotel.name;
            }
            element.com = element.hotel.name;
            break;
          case 5: 
            element.kind="Rent a car"; 
            element.com = element.idCompany; 
            break;
        }
      });
    }, error => console.error(error));
  }

  delete(id: number) {
    this.adminservice.remove(id).subscribe( r => {
      window.location.reload();
    }, error => console.error(error));
  }

}
