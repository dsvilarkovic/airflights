import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { AdminsService } from 'src/services/admins.service';
import { ROLE_SYS } from 'src/app/globals';
import { TokenStorageService } from 'src/services/auth/token-storage.service';

@Component({
  selector: 'app-admins',
  templateUrl: './admins.component.html',
  styleUrls: ['./admins.component.scss']
})
export class AdminsComponent implements OnInit {

  admins: Array<any>;

  constructor(private route: ActivatedRoute, private ts: TokenStorageService, private router: Router, private adminservice : AdminsService ) { }

  ngOnInit() {
    if (!this.ts.getAuthorities().includes(ROLE_SYS)) {
      alert("Unauthorized");
      this.router.navigate(['/home']);
    }
    this.adminservice.getAllA().subscribe(data =>{
      this.admins = data;
    }, error => console.error(error));
  }

  delete(id: number) {
    this.adminservice.remove(id).subscribe( r => {
      window.location.reload();
    }, error => console.error(error));
  }

  logout() {
    this.ts.signOut();
    this.router.navigate(['/login']);
  }

}
