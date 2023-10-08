import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { MessageChatClientComponent } from './message-chat-client.component';

describe('MessageChatClientComponent', () => {
  let component: MessageChatClientComponent;
  let fixture: ComponentFixture<MessageChatClientComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ MessageChatClientComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(MessageChatClientComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
