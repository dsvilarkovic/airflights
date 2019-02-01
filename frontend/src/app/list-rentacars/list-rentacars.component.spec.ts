import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ListRentacarsComponent } from './list-rentacars.component';

describe('ListRentacarsComponent', () => {
  let component: ListRentacarsComponent;
  let fixture: ComponentFixture<ListRentacarsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ListRentacarsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ListRentacarsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
