import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ConfigSeatsComponent } from './config-seats.component';

describe('ConfigSeatsComponent', () => {
  let component: ConfigSeatsComponent;
  let fixture: ComponentFixture<ConfigSeatsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ConfigSeatsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ConfigSeatsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
