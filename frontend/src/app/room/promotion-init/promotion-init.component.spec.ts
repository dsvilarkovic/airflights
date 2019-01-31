import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { PromotionInitComponent } from './promotion-init.component';

describe('PromotionInitComponent', () => {
  let component: PromotionInitComponent;
  let fixture: ComponentFixture<PromotionInitComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ PromotionInitComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(PromotionInitComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
