import { Component, OnInit, ViewChild } from '@angular/core';
import { MatSort } from '@angular/material/sort';
import { MatPaginator } from '@angular/material/paginator';
import { MatTableDataSource } from '@angular/material/table';
import {RegistrationService} from "../../Services/registration.service";
import { Router, ActivatedRoute } from '@angular/router';
import {Activies} from "../../Modals/activies";

@Component({
  selector: 'vex-activity-log',
  templateUrl: './activity-log.component.html',
  styleUrls: ['./activity-log.component.scss']
})


export class ActivityLogComponent implements OnInit {
  @ViewChild(MatSort) sort:MatSort
  @ViewChild (MatPaginator) paginator:MatPaginator

  displayedColumns: string[] = ["url","page","ip","loggedTime","userAgent"];
  acts: MatTableDataSource<any>;
  activities:Activies[];
  username:String;
  ip:String;

  deviceDetails:String;
  location:String;
  type:String;
  constructor(private service: RegistrationService,private router:Router,private activatedroute : ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedroute.queryParams
        .subscribe(params => {
              console.log(params); // { category: "fiction" }
              this.username = params.username;

          this.ip=params.ip;
          this.deviceDetails=params.deviceDetails;
          this.location=params.location;
          this.type=params.type;

              console.log(this.location); // fiction
            }
        );


    this.ListerActivities();

  }


  ListerActivities(){
  this.service.getNewDeviceActivity(this.username,this.ip).subscribe(data=>{
  this.activities=data;
      console.log(this.activities[0].loggedTime);
  this.acts=new MatTableDataSource(this.activities);

  this.acts.sort=this.sort;
  this.acts.paginator=this.paginator;

  })
  }

  ConfirmDevice(){
    if(this.type == "agent"){
      this.service.ConfirmAgentDevice(this.deviceDetails,this.location,this.username).subscribe(data=>{
        if(data != null){
          alert("you're device has been confirmed");
        }

      });

    }
    else if(this.type == "customer"){
      this.service.ConfirmCustomerDevice(this.deviceDetails,this.location,this.username).subscribe(data=>{
        if(data != null){
          alert("you're device has been confirmed");
        }
      });
    }
    else if(this.type == "investor"){
      this.service.ConfirmInvestorDevice(this.deviceDetails,this.location,this.username).subscribe(data=>{
        if(data != null){
          alert("you're device has been confirmed");
        }
      });
    }
    else if(this.type=="manager"){
      this.service.ConfirmManagerDevice(this.deviceDetails,this.location,this.username).subscribe(data=>{
        if(data != null){
          alert("you're device has been confirmed");
        }
      })
    }
  }

  DenyService(){
    alert("this device has been denied");
  }
}
