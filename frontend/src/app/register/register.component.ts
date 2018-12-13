import { User } from './../user';
import { Component, OnInit } from '@angular/core';
import { RegisterService } from 'src/services/register.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.scss']
})
export class RegisterComponent implements OnInit {

  user: User = new User();
  constructor(private registerService: RegisterService, private router : Router) { }

  ngOnInit() {
  }

  register() {
      this.registerService.register(this.user).subscribe(data => {
        this.user = data;      
        alert("Uspesno registrovanje! Vasu registraciju morate da potvrdite na vasem e-mailu!");
        this.router.navigate(["/login/"]);
        this.registerService.sendMail(this.user.id).subscribe(data => {
          alert("DATA?");
        })
      })
  }

}
