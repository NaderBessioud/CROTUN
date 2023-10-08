import {Component, ElementRef, AfterViewInit, ViewChild, Input} from '@angular/core';
import * as L from 'leaflet'
import {RegistrationService} from "../../Services/registration.service";
import {UserProfileComponent} from "../user/user-profile/user-profile.component";
@Component({
  selector: 'vex-map',
  templateUrl: './map.component.html',
  styleUrls: ['./map.component.scss']
})
export class MapComponent implements AfterViewInit {

map;
address:String;

  public constructor(private service:RegistrationService) { }



  public ngAfterViewInit() {
    this.createMap();
  }
  createMap(){
    const parcThabor={
      lat:36.713432,
      lng:10.209552,
    };
    const zoom=15;
  this.map=L.map('map',{
    center: parcThabor,
    zoom: zoom

  });
    L.tileLayer('https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png', {
      attribution: '&copy; <a href="https://www.openstreetmap.org/copyright">OpenStreetMap</a> contributors'
    }).addTo(this.map);

    this.map.on("click", e => {
      this.service.getAddress(e.latlng.lat,e.latlng.lng).subscribe(adr=>{
        this.address=adr;
        console.log(adr);
        localStorage.setItem("address",adr);
        window.close();
      })
      console.log(e.latlng);
    });

  }

}
