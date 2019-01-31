import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AdminRentACarComponent } from './admin-rent-a-car.component';

describe('AdminRentACarComponent', () => {
  let component: AdminRentACarComponent;
  let fixture: ComponentFixture<AdminRentACarComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AdminRentACarComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AdminRentACarComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
