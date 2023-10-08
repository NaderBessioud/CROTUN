import { Component, OnInit } from '@angular/core';
import { Agent } from 'src/app/Modals/agent';
import { Customer } from 'src/app/Modals/customer';
import { Messages } from 'src/app/Modals/messages';
import { MessageService } from 'src/app/Services/message.service';
import { VideoSDKMeeting } from '@videosdk.live/rtc-js-prebuilt';
import { Router, ActivatedRoute, ParamMap } from '@angular/router';


@Component({
  selector: 'vex-chatmessage',
  templateUrl: './chatmessage.component.html',
  styleUrls: ['./chatmessage.component.scss']
})
export class ChatmessageComponent implements OnInit {
  id:number=0;
  listConver : Agent[]=[];
  listConveru : Customer[]=[];
  idCon:any;
  listMessages : Messages[]=[];
  listMessages2 : Messages[]=[];


  message : Messages=new Messages();
  firstname :string="";
  test:number=0;

  constructor(private _service:MessageService,private router: Router) { }

  ngOnInit(): void {
    this.idCon=sessionStorage.getItem("id");
    this._service.getConver().subscribe(res=>{console.log(res);

      this.listConver=res});
    this._service.getConveru(sessionStorage.getItem("id")).subscribe(res=>{console.log(res);

      this.listConveru=res});
  }
  getconver(){
    this._service.getConver().subscribe(res => this.listConver = res)



  }
  onSave(ids,idr:number,chaine:string){
    this.firstname=chaine;
    this.id=idr;
    this.test=1;
    this._service.getMessages(sessionStorage.getItem("id"),idr).subscribe(res=>{console.log(res);

      this.listMessages=res}

    )
    ;
  }
  onSaveC(ids,idr:number,chaine:string){
    this.firstname=chaine;
    this.id=idr;
    this.test=2;

    this._service.getMessagesUrl(sessionStorage.getItem("id"),idr).subscribe(res=>{console.log(res);

      this.listMessages2=res}

    )
    ;
  }
  addMessage(ids:number,m: Messages){
    this._service.addMessage(sessionStorage.getItem("id"),this.id,m).subscribe(() => {
      this.onSave(sessionStorage.getItem("id"),this.id,this.firstname);

    });
    this.message.contenu='';
  }

  deleteMessage(ids:number,ida:number){
    this._service.deleteMessage(ida).subscribe(() => {
      if (this.test == 1)
        this.onSave(sessionStorage.getItem("id"), this.id, this.firstname);
      if (this.test == 2)
        this.onSaveC(sessionStorage.getItem("id"), this.id, this.firstname);

    });


  }
  addMessageu(ids:number,m: Messages){
    this._service.addMessageclient(ids,this.id,m).subscribe(() => {
      this.onSaveC(sessionStorage.getItem("id"),this.id,this.firstname);

    });
    this.message.contenu='';

  }
  async  onmakemeet(){
    this.router.navigate(['/panel/meet'])
  }
  addMessageac(ids:number,m: Messages){
    this._service.addMessageagentclient(ids,this.id,m).subscribe(() => {
      this.onSaveC(sessionStorage.getItem("id"),this.id,this.firstname);


    });
    this.message.contenu='';

  }
refresh(ids:number,idr:number,chaine:string)
{if(this.test==1)
{
  this.onSave(sessionStorage.getItem("id"),idr,chaine);

}
  if(this.test==2)
  {
    this.onSaveC(sessionStorage.getItem("id"),idr,chaine);

  }
}

  }


