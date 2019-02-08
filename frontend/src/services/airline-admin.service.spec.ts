import { TestBed } from '@angular/core/testing';

import { AirlineAdminService } from './airline-admin.service';

describe('AirlineAdminService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: AirlineAdminService = TestBed.get(AirlineAdminService);
    expect(service).toBeTruthy();
  });
});
