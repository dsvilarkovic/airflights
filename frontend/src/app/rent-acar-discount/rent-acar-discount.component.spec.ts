import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { RentACarDiscountComponent } from './rent-acar-discount.component';

describe('RentACarDiscountComponent', () => {
  let component: RentACarDiscountComponent;
  let fixture: ComponentFixture<RentACarDiscountComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ RentACarDiscountComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(RentACarDiscountComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
