import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { InvestmentofferComponent } from './investmentoffer.component';

describe('InvestmentofferComponent', () => {
  let component: InvestmentofferComponent;
  let fixture: ComponentFixture<InvestmentofferComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ InvestmentofferComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(InvestmentofferComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
