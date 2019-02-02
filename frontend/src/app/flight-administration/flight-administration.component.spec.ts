import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { FlightAdministrationComponent } from './flight-administration.component';

describe('FlightAdministrationComponent', () => {
  let component: FlightAdministrationComponent;
  let fixture: ComponentFixture<FlightAdministrationComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ FlightAdministrationComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(FlightAdministrationComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
