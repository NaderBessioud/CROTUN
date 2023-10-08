import { TestBed } from '@angular/core/testing';

import { LoandetailsService } from './loandetails.service';

describe('LoandetailsService', () => {
  let service: LoandetailsService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(LoandetailsService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
