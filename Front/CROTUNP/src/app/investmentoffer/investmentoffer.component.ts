import {InvestmentOffer} from "../Modals/investmentOffer";
import {InvestmentofferService} from "../Services/investmentoffer";
import {ModalDismissReasons, NgbModal} from "@ng-bootstrap/ng-bootstrap";
import {Component, ElementRef, OnInit, ViewChild} from '@angular/core';
import {filter, map, startWith, mergeMap} from 'rxjs/operators';
import {Chart} from 'node_modules/chart.js';
import {faPlus} from "@fortawesome/free-solid-svg-icons";
import {faPencilAlt} from "@fortawesome/free-solid-svg-icons";
import {faArchive} from "@fortawesome/free-solid-svg-icons";
declare var $;
@Component({
  selector: 'vex-investmentoffer',
  templateUrl: './investmentoffer.component.html',
  styleUrls: ['./investmentoffer.component.scss']
})
export class InvestmentofferComponent implements OnInit {
  faPencilAlt =faPencilAlt ;
  faPlus = faPlus;
  faArchive=faArchive;
  type : string;
  listInvestmentoffers: any;
  listInvestorInvestmentoffers: any;
  listoffer : any;
  listArchivedInvestmentoffers: any;
  form: boolean = false;
  investmentoffer!: InvestmentOffer;
  offer!: InvestmentOffer;
  investorinvestmentoffer!: InvestmentOffer;
  countInvestment : any;
  countSoldInvestment : any ;
  countUnSoldInvestment : any ;
  listRecommandation: any;
  closeResult!: string;
  id !: number;
  p : number = 1;

  constructor(private InvestmentofferService: InvestmentofferService, private modalService: NgbModal) { }





  ngOnInit(): void {


this.type=sessionStorage.getItem("type");
    this.countSoldInvestment;
    this.countUnSoldInvestment;
    this.getAllInvestmentoffers();
    this.getInvestorInvestmentoffer(sessionStorage.getItem("id"));
    this.getAlloffers();
    this.getAllArchivedInvestmentoffers();
    this.getAlloffers();
    this.getCountInvestmentoffers();
    this.getCountSoldInvestmentoffers();
    this.getCountUnSoldInvestmentoffers();
    this.getRecommandation(sessionStorage.getItem("id"));
    this.investmentoffer = {
      idino:null,
      type:null,
      description:null,
      price:null,
      dateSell:null,
      dateCreated:null,
      status:null,
      managerInvOffer:null,
      InvestorInvest:null
    }

    this.offer = {
      idino:null,
      type:null,
      description:null,
      price:null,
      dateSell:null,
      dateCreated:null,
      status:null,
      managerInvOffer:null,
      InvestorInvest:null
    }
    this.investorinvestmentoffer = {
      idino:null,
      type:null,
      description:null,
      price:null,
      dateSell:null,
      dateCreated:null,
      status:null,
      managerInvOffer:null,
      InvestorInvest:null

    }

      this.InvestmentofferService.getCountSoldInvestment().subscribe(res => {
        this.countUnSoldInvestment = res
        const myChart = new Chart("myChart", {
          type: 'doughnut',
          data: {
            labels: ['Sold', 'Available'],
            datasets: [{

              data: [this.countUnSoldInvestment[0], this.countUnSoldInvestment[1]],
              backgroundColor: [
                'rgb(255, 99, 132)',
                'rgb(54, 162, 235)'

              ],
              hoverOffset: 4

            }]
          },
          options: {
            scales: {
              y: {
                beginAtZero: true
              }
            }
          }
        });
      }
  )





  }


getUpdateInvestmentoffer(inv : InvestmentOffer){
this.InvestmentofferService.getUpdateInvestmentoffers(inv).subscribe(() => {
  this.getAllInvestmentoffers();
  this.getAlloffers();
  this.getAllArchivedInvestmentoffers();
  this.getRecommandation(sessionStorage.getItem("id"));
  this.form = false;
});
}

  getCountInvestmentoffers(){
    this.InvestmentofferService.getCountInvestment().subscribe(res => this.countInvestment = res)
  }
  getCountSoldInvestmentoffers(){
    this.InvestmentofferService.getCountSoldInvestment().subscribe(res => this.countSoldInvestment = res)
  }
  getCountUnSoldInvestmentoffers(){
    this.InvestmentofferService.getCountUnSoldInvestment().subscribe(res => this.countUnSoldInvestment = res)
  }

  getAllInvestmentoffers(){
    this.InvestmentofferService.getAllInvestmentoffers().subscribe(res => this.listInvestmentoffers = res)
  }

  getAllArchivedInvestmentoffers(){
    this.InvestmentofferService.getAllArchivedInvestmentoffers().subscribe(res => this.listArchivedInvestmentoffers = res)
  }

  getAlloffers(){
    this.InvestmentofferService.getAlloffers().subscribe(res => this.listoffer = res)
  }

  getInvestorInvestmentoffer(id : any){
    this.InvestmentofferService.getInvestorInvestmentoffers(sessionStorage.getItem("id")).subscribe(res => this.listInvestorInvestmentoffers = res)
  }
  getRecommandation(id : any){
    this.InvestmentofferService.getRecommandation(sessionStorage.getItem("id")).subscribe(res => this.listRecommandation = res)
  }
  addInvestmentoffer(p: any){
    this.InvestmentofferService.addInvestmentoffer(p,sessionStorage.getItem("id")).subscribe(() => {
      this.getAllInvestmentoffers();
      this.getAlloffers();
      this.getAllArchivedInvestmentoffers();
      this.getRecommandation(sessionStorage.getItem("id"));
      this.form = false;
    });
  }

  archiveInvestmentoffer(id : any){
    this.InvestmentofferService.archiveInvestmentoffer(id).subscribe(() => {
      this.getAllInvestmentoffers();
      this.getAlloffers();
      this.getAllArchivedInvestmentoffers();
      this.getRecommandation(sessionStorage.getItem("id"));
      this.form = false;
    });
  }

  buyInvestmentOffer(idIO : any, idI : any){
    this.InvestmentofferService.buyInvestmentOffer(idIO , idI).subscribe(() => {
      this.getAllInvestmentoffers();
      this.getRecommandation(sessionStorage.getItem("id"));
      this.getInvestorInvestmentoffer(sessionStorage.getItem("id"));
      this.form = false;
    });
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
