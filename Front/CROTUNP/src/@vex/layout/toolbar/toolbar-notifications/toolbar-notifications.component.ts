import { ChangeDetectionStrategy, ChangeDetectorRef, Component, ElementRef, Input, OnInit, ViewChild } from '@angular/core';
import { PopoverService } from '../../../components/popover/popover.service';
import { ToolbarNotificationsDropdownComponent } from './toolbar-notifications-dropdown/toolbar-notifications-dropdown.component';
import icNotificationsActive from '@iconify/icons-ic/twotone-notifications-active';
import { DataService } from 'src/app/providers/data.service';
import {MessageService} from "../../../../app/Services/message.service";
import {NorificationService} from "../../../../app/Services/norification.service";
import {Notification} from "../../../../app/Modals/notification";

@Component({
  selector: 'vex-toolbar-notifications',
  templateUrl: './toolbar-notifications.component.html',
  styleUrls: ['./toolbar-notifications.component.scss'],
  changeDetection: ChangeDetectionStrategy.OnPush
})
export class ToolbarNotificationsComponent implements OnInit {

  @ViewChild('originRef', { static: true, read: ElementRef }) originRef: ElementRef;
  @Input() notifications = [];
  listnotifs : Notification[]=[];
  showNotification: boolean;


  dropdownOpen: boolean;
  icNotificationsActive = icNotificationsActive;

  constructor(private popover: PopoverService,
              private cd: ChangeDetectorRef,
              private dataService: DataService,private _service:NorificationService) {}

  ngOnInit() {}

  showPopover() {
    this.dropdownOpen = true;
    this.cd.markForCheck();
    this._service.getnotifclient(1).subscribe(res=>{console.log(res);

      this.listnotifs=res}

    )
    ;

    const popoverRef = this.popover.open({
      content: ToolbarNotificationsDropdownComponent,
      origin: this.originRef,
      offsetY: 12,
      position: [
        {
          originX: 'center',
          originY: 'top',
          overlayX: 'center',
          overlayY: 'bottom'
        },
        {
          originX: 'end',
          originY: 'bottom',
          overlayX: 'end',
          overlayY: 'top',
        },
      ]
    });

    popoverRef.afterClosed$.subscribe(() => {
      this.dropdownOpen = false;
      this.cd.markForCheck();
    });
  }
  openNotification(state: boolean) {
    this.showNotification = state;
  }
}
