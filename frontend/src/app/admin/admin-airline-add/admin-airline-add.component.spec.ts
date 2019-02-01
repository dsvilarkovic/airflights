import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AdminAirlineAddComponent } from './admin-airline-add.component';

describe('AdminAirlineAddComponent', () => {
  let component: AdminAirlineAddComponent;
  let fixture: ComponentFixture<AdminAirlineAddComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AdminAirlineAddComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AdminAirlineAddComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
