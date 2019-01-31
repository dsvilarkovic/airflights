import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ExtrasAddComponent } from './extras-add.component';

describe('ExtrasAddComponent', () => {
  let component: ExtrasAddComponent;
  let fixture: ComponentFixture<ExtrasAddComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ExtrasAddComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ExtrasAddComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
