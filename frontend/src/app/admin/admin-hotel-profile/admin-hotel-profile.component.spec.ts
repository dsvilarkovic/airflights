import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AdminHotelProfileComponent } from './admin-hotel-profile.component';

describe('AdminHotelProfileComponent', () => {
  let component: AdminHotelProfileComponent;
  let fixture: ComponentFixture<AdminHotelProfileComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AdminHotelProfileComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AdminHotelProfileComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
