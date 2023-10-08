import { Component, Input, OnInit } from '@angular/core';
import { trackByRoute } from '../../utils/track-by';
import { NavigationService } from '../../services/navigation.service';
import icRadioButtonChecked from '@iconify/icons-ic/twotone-radio-button-checked';
import icRadioButtonUnchecked from '@iconify/icons-ic/twotone-radio-button-unchecked';
import { LayoutService } from '../../services/layout.service';
import { ConfigService } from '../../services/config.service';
import { map } from 'rxjs/operators';
import icMenu from '@iconify/icons-ic/twotone-menu';
import {UserService} from '../../../app/providers/user.service';
import {RegistrationService} from "../../../app/Services/registration.service";

@Component({
  selector: 'vex-sidenav',
  templateUrl: './sidenav.component.html',
  styleUrls: ['./sidenav.component.scss']
})
export class SidenavComponent implements OnInit {
  fullName:String;
  @Input() collapsed: boolean;
  collapsedOpen$ = this.layoutService.sidenavCollapsedOpen$;
  title$ = this.configService.config$.pipe(map(config => config.sidenav.title));
  imageUrl$ = this.configService.config$.pipe(map(config => config.sidenav.imageUrl));
  mimageUrl$ = this.configService.config$.pipe(map(config => config.sidenav.mobileImageUrl));
  showCollapsePin$ = this.configService.config$.pipe(map(config => config.sidenav.showCollapsePin));
  showTitle$ = this.configService.config$.pipe(map(config => config.sidenav.showTitle));

  items = this.navigationService.items;
  trackByRoute = trackByRoute;
  icRadioButtonChecked = icRadioButtonChecked;
  icRadioButtonUnchecked = icRadioButtonUnchecked;
  icMenu = icMenu;

  uploadedImage: File;
  dbImage: any;
  postResponse: any;
  successResponse: string;
  image: any;

  constructor(private navigationService: NavigationService,
              private layoutService: LayoutService,
              public userService: UserService,
              private configService: ConfigService,
              private service: RegistrationService) { }

  ngOnInit() {
    if(sessionStorage.getItem("firstname") != "null"){
      this.fullName=sessionStorage.getItem("firstname")+" "+sessionStorage.getItem("lastname")
    }
    else{
      this.fullName="";
    }
    this.downloadImage(sessionStorage.getItem("image"));
  }
  openSidenav() {
    this.layoutService.openSidenav();
  }
  onMouseEnter() {
    this.layoutService.collapseOpenSidenav();
  }

  onMouseLeave() {
    this.layoutService.collapseCloseSidenav();
  }
  checkMenuVisible(permissions: string[]){
    const visible = permissions.includes(this.userService.currentUser.type);
    return visible;
  }
  toggleSidenav(){
    const wrapper = document.querySelectorAll('.sidenav .mat-drawer-inner-container')[0];
    wrapper.classList.toggle('collapsed');
    const toolbarlogo = document.querySelectorAll('.toolbar-logo')[0];
    toolbarlogo.classList.toggle('show');
    const sidenavContent = document.querySelectorAll('.mat-drawer.sidenav:not(.mat-sidenav-fixed) ~ .sidenav-content')[0];
    sidenavContent.classList.toggle('sidenav-collapsed');
  }
  toggleCollapse() {
    this.collapsed ? this.layoutService.expandSidenav() : this.layoutService.collapseSidenav();
  }

  downloadImage(name){

    this.service.DownloadImage(name).subscribe(res=>{


      if(res == null){
      }
      else{
        this.dbImage=res.image;
      }
    }),err=>{

    }




  }
}
