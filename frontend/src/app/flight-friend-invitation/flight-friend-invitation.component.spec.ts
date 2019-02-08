import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { FlightFriendInvitationComponent } from './flight-friend-invitation.component';

describe('FlightFriendInvitationComponent', () => {
  let component: FlightFriendInvitationComponent;
  let fixture: ComponentFixture<FlightFriendInvitationComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ FlightFriendInvitationComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(FlightFriendInvitationComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
