import { User } from './../user';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { RegisterService } from 'src/services/register.service';

@Component({
  selector: 'app-verify',
  templateUrl: './verify.component.html',
  styleUrls: ['./verify.component.scss']
})
export class VerifyComponent implements OnInit {

  user : User = new User();
  id;

  constructor(private route : ActivatedRoute,
              private service: RegisterService) { }

  ngOnInit() {
    this.id = this.route.snapshot.params.id;
    this.service.verify(this.id).subscribe(data => {
      this.user = data;
    })
  }

}
