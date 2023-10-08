import { Component, OnInit } from '@angular/core';
import {InvestmentService} from "../Services/investment";
import {ModalDismissReasons, NgbModal} from "@ng-bootstrap/ng-bootstrap";
import { Investment } from '../Modals/investment';
import {faPlus} from "@fortawesome/free-solid-svg-icons";

@Component({
  selector: 'vex-investment',
  templateUrl: './investment.component.html',
  styleUrls: ['./investment.component.scss']
})
export class InvestmentComponent implements OnInit {

    start !:string;
    end !:string;
    amount !:number;
    faPlus = faPlus;

    count1:any;
    count2:any;
    count3:any;
simulation:any;
listInvestment : any;
listAllInvestment:any;
form: boolean = false;
investment!: Investment;
closeResult!:string ;
  id !: number;
  type:string;
  constructor(private InvestmentService: InvestmentService, private modalService: NgbModal) {


  }

  ngOnInit(): void {

      this.type=sessionStorage.getItem("type");
      this.getAllInvestment();
    this.getMyInvestment(sessionStorage.getItem("id"));
      this.simulationInvestment();
      this.getCountAllInvestment();
      this.getCountProfitInvestment();
      this.getCountTotalInvestment();

this.simulation;
    this.investment = {
      idIN : null,
      amount: null,
      dateStart: null,
      dateEnd: null,
      rate: null,
      duration: null,
      profit: null,
      totalAmount: null,
      status: null,
      Investors : null
    }
  }

  getMyInvestment(id: any){
    this.InvestmentService.getMyInvestment(sessionStorage.getItem("id")).subscribe(res => this.listInvestment = res)
  }
 addInvestment(p:any){
    this.InvestmentService.addInvestment(p,sessionStorage.getItem("id")).subscribe(() => {
      this.getMyInvestment(sessionStorage.getItem("id"));
      this.form = false;
    });
 }
  simulationInvestment(){
      this.InvestmentService.simulationInvestment(this.start ,this.end,this.amount).subscribe(res => this.simulation = res);
  console.log(this.simulation);
  }

    getAllInvestment(){
        this.InvestmentService.getAllInvestment().subscribe(res => this.listAllInvestment = res)
    }

    getCountAllInvestment(){
        this.InvestmentService.getCountAllInvestment().subscribe(res => this.count1 = res)
    }
    getCountProfitInvestment(){
        this.InvestmentService.getCountProfitInvestment().subscribe(res => this.count2 = res)
    }
    getCountTotalInvestment(){
        this.InvestmentService.getCountTotalInvestment().subscribe(res => this.count3 = res)
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
