import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ListAirflightsComponent } from './list-airflights.component';

describe('ListAirflightsComponent', () => {
  let component: ListAirflightsComponent;
  let fixture: ComponentFixture<ListAirflightsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ListAirflightsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ListAirflightsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
