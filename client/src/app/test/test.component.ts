import { Component, OnInit } from '@angular/core';
import { TestService } from 'src/app/services/test/test.service';

@Component({
  selector: 'app-test',
  templateUrl: './test.component.html',
  styleUrls: ['./test.component.css']
})
export class TestComponent implements OnInit {

	test: String;

  constructor(private testService : TestService) { }

  ngOnInit() {
  	this.testService.test().subscribe( retVal =>{
  		this.test = retVal;
  	});
  }

}
