import { TestBed } from '@angular/core/testing';

import { SoldServiceService } from './sold-service.service';

describe('SoldServiceService', () => {
  let service: SoldServiceService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(SoldServiceService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
