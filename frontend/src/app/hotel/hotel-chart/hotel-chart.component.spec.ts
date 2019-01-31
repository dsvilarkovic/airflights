import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { HotelChartComponent } from './hotel-chart.component';

describe('HotelChartComponent', () => {
  let component: HotelChartComponent;
  let fixture: ComponentFixture<HotelChartComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ HotelChartComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(HotelChartComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
