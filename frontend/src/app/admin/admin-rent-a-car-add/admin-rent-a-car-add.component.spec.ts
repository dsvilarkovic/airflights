import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AdminRentACarAddComponent } from './admin-rent-a-car-add.component';

describe('AdminRentACarAddComponent', () => {
  let component: AdminRentACarAddComponent;
  let fixture: ComponentFixture<AdminRentACarAddComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AdminRentACarAddComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AdminRentACarAddComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
