import { TestBed } from '@angular/core/testing';

import { MotorBikeServiceService } from './motor-bike-service.service';

describe('MotorBikeServiceService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: MotorBikeServiceService = TestBed.get(MotorBikeServiceService);
    expect(service).toBeTruthy();
  });
});
