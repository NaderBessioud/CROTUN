import { Component, OnInit } from '@angular/core';
import {Notification} from "../../Modals/notification";
import {NorificationService} from "../../Services/norification.service";

@Component({
  selector: 'vex-notification',
  templateUrl: './notification.component.html',
  styleUrls: ['./notification.component.scss']
})

export class NotificationComponent implements OnInit {
  listnotifs : Notification[]=[];

  constructor(private _service:NorificationService) { }

  ngOnInit(): void {
    this._service.getnotifclient(sessionStorage.getItem("id")).subscribe(res=>{console.log(res);

      this.listnotifs=res}

    )
    ;
  }

}
