import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AgentBackComponent } from './agent-back.component';

describe('AgentBackComponent', () => {
  let component: AgentBackComponent;
  let fixture: ComponentFixture<AgentBackComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AgentBackComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(AgentBackComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
