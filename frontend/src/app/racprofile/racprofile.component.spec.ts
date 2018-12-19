import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { RacprofileComponent } from './racprofile.component';

describe('RacprofileComponent', () => {
  let component: RacprofileComponent;
  let fixture: ComponentFixture<RacprofileComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ RacprofileComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(RacprofileComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
