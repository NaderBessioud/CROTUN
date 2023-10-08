import { Component, OnInit } from '@angular/core';
import {ModalDismissReasons, NgbModal} from "@ng-bootstrap/ng-bootstrap";
import { AvailablityService } from "../Services/availablity";
import {Investment} from "../Modals/investment";
import {Availablity} from "../Modals/availablity";
import dayGridPlugin from '@fullcalendar/daygrid';
import { CalendarOptions } from '@fullcalendar/angular';

@Component({
  selector: 'vex-availablity',
  templateUrl: './availablity.component.html',
  styleUrls: ['./availablity.component.scss']
})
export class AvailablityComponent implements OnInit {

  idA:any;
  idC:any;
time1:any;
time2:any;
date:any;
id:any;
  listAllAvai:any;
  listagentavai:any;
  listcustomeravai:any;
  type:string;
availablity !: Availablity;
  listDateAvai:any;
  form: boolean = false;
  closeResult!:string ;
  constructor(private AvailablityService: AvailablityService, private modalService: NgbModal ) { }

  ngOnInit(): void {
    this.type=sessionStorage.getItem("type");

    this.getretrieveAllAvai();
    this.getRetrieveAgentAvaibli(1);
    this.getCustomerAvaibli(1);

    this.availablity={
      idAvailblity:null,
      dateCreated:null,
      dateAva:null,
      startDate:null,
      endHour:null,
      customerAvailbility:null,
      agentAvailbility:null
    }
  }


  calendarOptions: CalendarOptions = {
    initialView: 'dayGridMonth',

    events: [
      { title: 'event 1', date: '2022-05-04' },
      {  date: '2022-05-07' }


    ]
  };


getDateAvai(){
  this.AvailablityService.getDateAvai().subscribe(res => this.listDateAvai = res);
}




  getAllAvai(p:any){
    this.AvailablityService.getAllAvai(p,this.time1,this.time2,this.date,sessionStorage.getItem("id")).subscribe( () => {
      this.getretrieveAllAvai();
      this.getRetrieveAgentAvaibli(sessionStorage.getItem("id"));
      this.getCustomerAvaibli(sessionStorage.getItem("id"));


      this.form = false;
    })
  }


  getretrieveAllAvai(){
  this.AvailablityService.getretrieveAllAvai().subscribe(res => this.listAllAvai = res )
  }


  getRetrieveAgentAvaibli(id:any){
  this.AvailablityService.getretrieveAgentAvaibli(sessionStorage.getItem("id")).subscribe(res => this.listagentavai = res)
  }


  getaddAvaiCustomer(idA:any,idC:any){
  this.AvailablityService.getAddAvaiCustomer(idA,sessionStorage.getItem("id")).subscribe(()=>{
    this.getretrieveAllAvai();
    this.getRetrieveAgentAvaibli(sessionStorage.getItem("id"));
    this.getCustomerAvaibli(sessionStorage.getItem("id"));


  })
  }

  getCustomerAvaibli(idC:any){
    this.AvailablityService.getretrieveAgentAvaibli(sessionStorage.getItem("id")).subscribe(res => this.listcustomeravai = res)
  }







  open(content: any) {
    this.modalService.open(content, {ariaLabelledBy: 'modal-basic-title'}).result.then((result) => {
      this.closeResult = `Closed with: ${result}`;
    }, (reason) => {
      this.closeResult = `Dismissed ${this.getDismissReason(reason)}`;
    });
  }
  private getDismissReason(reason: any): string {
    if (reason === ModalDismissReasons.ESC) {
      return 'by pressing ESC';
    } else if (reason === ModalDismissReasons.BACKDROP_CLICK) {
      return 'by clicking on a backdrop';
    } else {
      return  `with: ${reason}`;
    }
  }
  closeForm(){

  }
  cancel(){
    this.form = false;
  }
}
