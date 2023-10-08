import { TestBed } from '@angular/core/testing';

import { NorificationService } from './norification.service';

describe('NorificationService', () => {
  let service: NorificationService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(NorificationService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
