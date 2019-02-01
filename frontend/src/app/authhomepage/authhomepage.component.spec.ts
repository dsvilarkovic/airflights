import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AuthhomepageComponent } from './authhomepage.component';

describe('AuthhomepageComponent', () => {
  let component: AuthhomepageComponent;
  let fixture: ComponentFixture<AuthhomepageComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AuthhomepageComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AuthhomepageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
