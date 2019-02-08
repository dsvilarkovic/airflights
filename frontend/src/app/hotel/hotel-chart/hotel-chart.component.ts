import { Component, OnInit } from '@angular/core';
import { Hotel } from 'src/app/hotel';
import { ActivatedRoute, Router } from '@angular/router';
import { HotelService } from 'src/services/hotel.service';
import { TokenStorageService } from 'src/services/auth/token-storage.service';
import { AdminsService } from 'src/services/admins.service';
import { ROLE_H } from 'src/app/globals';

@Component({
  selector: 'app-hotel-chart',
  templateUrl: './hotel-chart.component.html',
  styleUrls: ['./hotel-chart.component.scss']
})
export class HotelChartComponent implements OnInit {

  hotel: any;

  constructor(private route: ActivatedRoute, private router: Router, private adminService: AdminsService, private ts: TokenStorageService, private hotelservice : HotelService) { }


  ngOnInit() {
    
    if (!this.ts.getAuthorities().includes(ROLE_H)) {
      alert("Unauthorized");
      this.router.navigate(['/home']);
    }

    this.adminService.getAdmin(this.ts.getUser()).subscribe( r => {
      this.hotel = r.hotel;
    }, error => console.error(error));
  }

  logout() {
    this.ts.signOut();
    this.router.navigate(['/login']);
  }

  public lineChartData:Array<any> = [
    {data: [0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0], label: 'Number of guests'},
  ];

  public lineChartLabels:Array<any> = ['', '', '', '', '', '', '', '', '', '', '', ''];

  
  public lineChartOptions:any = {
    responsive: true
  };

  public lineChartColors:Array<any> = [
    { 
      backgroundColor: 'rgba(148,159,177,0.2)',
      borderColor: 'rgba(148,159,177,1)',
      pointBackgroundColor: 'rgba(148,159,177,1)',
      pointBorderColor: '#fff',
      pointHoverBackgroundColor: '#fff',
      pointHoverBorderColor: 'rgba(148,159,177,0.8)'
    }
  ];
  public lineChartLegend:boolean = true;
  public lineChartType:string = 'bar';
 
  public chartClicked(e:any):void {
    console.log(e);
  }
 
  public chartHovered(e:any):void {
    console.log(e);
  }

  week() {
    this.hotelservice.lastWeek(this.hotel.id).subscribe( r => {
      let _lineChartData:Array<any> = new Array(1);
      _lineChartData [0]= {data: new Array(12), label: this.lineChartData[0].label}
      for (let i = 0; i < 7; i++) {
        _lineChartData[0].data[i+5] = r[i]
      }
      this.lineChartData = _lineChartData;
      this.lineChartLabels = ['', '', '', '', '','Day 1', 'Day 2', 'Day 3', 'Day 4', 'Day 5', 'Day 6', 'Day 7'];
    })
  }

  month() {
    this.hotelservice.lastM(this.hotel.id).subscribe( r => {
      let _lineChartData:Array<any> = new Array(1);
      _lineChartData [0]= {data: new Array(12), label: this.lineChartData[0].label}
      for (let i = 0; i < 5; i++) {
        _lineChartData[0].data[i+7] = r[i]
      }
      this.lineChartData = _lineChartData;
      this.lineChartLabels = ['', '', '', '', '','', '', 'Week 1', 'Week 2', 'Week 3', 'Week 4', 'Week 5'];
    })
  }

  year() {
    this.hotelservice.lastYear(this.hotel.id).subscribe( r => {
      let _lineChartData:Array<any> = new Array(1);
      _lineChartData [0]= {data: new Array(12), label: this.lineChartData[0].label}
      for (let i = 0; i < 12; i++) {
        _lineChartData[0].data[i] = r[i]
      }
      this.lineChartData = _lineChartData;
      this.lineChartLabels = ['Month 1', 'Month 2', 'Month 3', 'Month 4', 'Month 5','Month 6', 'Month 7', 'Month 8', 'Month 9', 'Month 10', 'Month 11', 'Month 12'];
    })
  }

}
