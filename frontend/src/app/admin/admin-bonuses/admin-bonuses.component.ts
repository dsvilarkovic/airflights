import { Component, OnInit } from '@angular/core';
import { ROLE_SYS } from 'src/app/globals';
import { TokenStorageService } from 'src/services/auth/token-storage.service';
import { Router } from '@angular/router';
import { AdminsService } from 'src/services/admins.service';
import { Misc } from 'src/app/misc';

@Component({
  selector: 'app-admin-bonuses',
  templateUrl: './admin-bonuses.component.html',
  styleUrls: ['./admin-bonuses.component.scss']
})
export class AdminBonusesComponent implements OnInit {

  m: Misc = new Misc();
  i: number;
  th: number;
  dis: number;

  constructor(private ts: TokenStorageService, private router: Router,
    private aS: AdminsService) { }

  ngOnInit() {
    if (!this.ts.getAuthorities().includes(ROLE_SYS)) {
      alert("Unauthorized");
      this.router.navigate(['/home']);
    }

    this.aS.getMisc().subscribe( r => {
      this.m.id = r.id;
      this.m.b=r.b;
      this.m.bb=r.bb;
      this.m.bbb=r.bbb;
    })

    this.aS.getMiscAll().subscribe( r => {
      r.forEach(element => {
       switch(element.id) {
         case 4:
          this.i = element.id
          this.th = element.b
          this.dis = element.bb
         break;
       } 
      });

    }) 

  }

  logout() {
    this.ts.signOut();
    this.router.navigate(['/login']);
  }

  save() {
    if (this.m.b && this.m.bb && this.m.bbb && this.m.b > 0 && this.m.bb > 0 && this.m.bbb > 0) {
      this.aS.upMisc(this.m).subscribe( r => {
        window.location.reload();
      })
    }else {
      alert("Please insert postitive integers")
    }
  }

  save2() {
    if (this.th && this.dis && this.th > 0 && this.dis > 0) {
      let m = new Misc();
      m.b = this.th
      m.bb = this.dis
      m.id = this.i
      m.bbb = 0
      this.aS.upMisc(m).subscribe( r => {
        window.location.reload();
      })
    }else {
      alert("Please insert postitive integers")
    }
  }

}
