import { TestBed } from '@angular/core/testing';

import { HotelExtrasService } from './hotel-extras.service';

describe('HotelExtrasService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: HotelExtrasService = TestBed.get(HotelExtrasService);
    expect(service).toBeTruthy();
  });
});
