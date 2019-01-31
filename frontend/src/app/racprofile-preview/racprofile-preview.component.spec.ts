import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { RacprofilePreviewComponent } from './racprofile-preview.component';

describe('RacprofilePreviewComponent', () => {
  let component: RacprofilePreviewComponent;
  let fixture: ComponentFixture<RacprofilePreviewComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ RacprofilePreviewComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(RacprofilePreviewComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
