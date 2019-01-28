import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { RentacarpreviewComponent } from './rentacarpreview.component';

describe('RentacarpreviewComponent', () => {
  let component: RentacarpreviewComponent;
  let fixture: ComponentFixture<RentacarpreviewComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ RentacarpreviewComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(RentacarpreviewComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
