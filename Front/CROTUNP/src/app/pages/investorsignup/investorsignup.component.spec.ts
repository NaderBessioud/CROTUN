import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { InvestorsignupComponent } from './investorsignup.component';

describe('InvestorsignupComponent', () => {
  let component: InvestorsignupComponent;
  let fixture: ComponentFixture<InvestorsignupComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ InvestorsignupComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(InvestorsignupComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
