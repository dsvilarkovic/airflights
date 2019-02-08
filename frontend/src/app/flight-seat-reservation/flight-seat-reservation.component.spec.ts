import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { FlightSeatReservationComponent } from './flight-seat-reservation.component';

describe('FlightSeatReservationComponent', () => {
  let component: FlightSeatReservationComponent;
  let fixture: ComponentFixture<FlightSeatReservationComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ FlightSeatReservationComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(FlightSeatReservationComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
