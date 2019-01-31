import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ExtrasEditComponent } from './extras-edit.component';

describe('ExtrasEditComponent', () => {
  let component: ExtrasEditComponent;
  let fixture: ComponentFixture<ExtrasEditComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ExtrasEditComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ExtrasEditComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
