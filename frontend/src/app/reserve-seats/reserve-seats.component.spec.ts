import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ReserveSeatsComponent } from './reserve-seats.component';

describe('ReserveSeatsComponent', () => {
  let component: ReserveSeatsComponent;
  let fixture: ComponentFixture<ReserveSeatsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ReserveSeatsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ReserveSeatsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
