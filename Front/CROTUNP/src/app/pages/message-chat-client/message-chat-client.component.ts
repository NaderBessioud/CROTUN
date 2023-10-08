import { Component, OnInit } from '@angular/core';
import { Messages } from 'src/app/Modals/messages';
import { Customer } from 'src/app/Modals/customer';
import { Agent } from 'src/app/Modals/agent';
import { MessageService } from 'src/app/Services/message.service';

@Component({
  selector: 'vex-message-chat-client',
  templateUrl: './message-chat-client.component.html',
  styleUrls: ['./message-chat-client.component.scss']
})
export class MessageChatClientComponent implements OnInit {

  agent:Agent=new Agent();
  listMessages : Messages[]=[];

  message : Messages=new Messages();


  constructor(private _service:MessageService) { }

  ngOnInit(): void {
    this._service.getConveruser(sessionStorage.getItem("id")).subscribe(res=>{console.log(res);

      this.agent=res;
      this._service.getMessagesUrl(sessionStorage.getItem("id"),this.agent.idA).subscribe(res=>{console.log(res);

        this.listMessages=res}

      )
    });


    ;

  }
  onSave(ids,idr:number){
    this._service.getMessagesUrl(sessionStorage.getItem("id"),idr).subscribe(res=>{console.log(res);

      this.listMessages=res}

    )
    ;
  }
  addMessage(ids:number,m: Messages){
    this._service.addMessageclient(sessionStorage.getItem("id"),this.agent.idA,m).subscribe(() => {
      this.onSave(sessionStorage.getItem("id"),this.agent.idA);

    });
    this.message.contenu='';

  }
  deleteMessage(ids:number,ida:number){
    this._service.deleteMessage(ida).subscribe(() => this.onSave(sessionStorage.getItem("id"),this.agent.idA))


  }

}
