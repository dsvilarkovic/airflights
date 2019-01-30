import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { RentacarPreviewComponent } from './rentacar-preview.component';

describe('RentacarPreviewComponent', () => {
  let component: RentacarPreviewComponent;
  let fixture: ComponentFixture<RentacarPreviewComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ RentacarPreviewComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(RentacarPreviewComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
