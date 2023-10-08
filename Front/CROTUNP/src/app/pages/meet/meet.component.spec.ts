import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { MeetComponent } from './meet.component';

describe('AppComponent', () => {
  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [
        MeetComponent
      ],
    }).compileComponents();
  });

  it('should create the app', () => {
    const fixture = TestBed.createComponent(MeetComponent);
    const app = fixture.componentInstance;
    expect(app).toBeTruthy();
  });

  it(`should have as title 'angular-prebuilt'`, () => {
    const fixture = TestBed.createComponent(MeetComponent);
    const app = fixture.componentInstance;

  });

  it('should render title', () => {
    const fixture = TestBed.createComponent(MeetComponent);
    fixture.detectChanges();
    const compiled = fixture.nativeElement;
    expect(compiled.querySelector('.content span').textContent).toContain('angular-prebuilt app is running!');
  });
});
