import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { PaymentEnLigneComponent } from './payment-en-ligne.component';

describe('PaymentEnLigneComponent', () => {
  let component: PaymentEnLigneComponent;
  let fixture: ComponentFixture<PaymentEnLigneComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ PaymentEnLigneComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(PaymentEnLigneComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
