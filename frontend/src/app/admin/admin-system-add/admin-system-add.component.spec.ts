import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AdminSystemAddComponent } from './admin-system-add.component';

describe('AdminSystemAddComponent', () => {
  let component: AdminSystemAddComponent;
  let fixture: ComponentFixture<AdminSystemAddComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AdminSystemAddComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AdminSystemAddComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
