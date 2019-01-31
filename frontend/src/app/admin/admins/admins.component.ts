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
        switch(element.idCompany) {
          case 1: element.kind="Flight"; break;
          case 2: element.kind="Rent a car"; break;
          case 3: element.kind="Hotel"; break;
          case 4: element.kind="System"; break;
        }
      });
    }, error => console.error(error));
  }

}
