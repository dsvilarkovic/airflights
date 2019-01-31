import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { HotelRevenuesComponent } from './hotel-revenues.component';

describe('HotelRevenuesComponent', () => {
  let component: HotelRevenuesComponent;
  let fixture: ComponentFixture<HotelRevenuesComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ HotelRevenuesComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(HotelRevenuesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
