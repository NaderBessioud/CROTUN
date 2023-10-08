import { TestBed } from '@angular/core/testing';

import { PaymentEnLigneService } from './payment-en-ligne.service';

describe('PaymentEnLigneService', () => {
  let service: PaymentEnLigneService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(PaymentEnLigneService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
