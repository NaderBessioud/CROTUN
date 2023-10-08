import { TestBed } from '@angular/core/testing';

import { MethodePaymentService } from './methode-payment.service';

describe('MethodePaymentService', () => {
  let service: MethodePaymentService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(MethodePaymentService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
