import { Component, OnInit, Input, EventEmitter } from '@angular/core';
import {LoanService} from 'src/app/Services/loan.service';
import {MatDialog, MatDialogRef} from '@angular/material/dialog';
import {LoanInfoComponent} from '../loan-info/loan-info.component';
import {scaleIn400ms} from '../../../@vex/animations/scale-in.animation';
import {stagger40ms} from '../../../@vex/animations/stagger.animation';
import {fadeInUp400ms} from '../../../@vex/animations/fade-in-up.animation';
import {scaleFadeIn400ms} from '../../../@vex/animations/scale-fade-in.animation';
import {fadeInRight400ms} from '../../../@vex/animations/fade-in-right.animation';
import * as CanvasJS from 'src/assets/canvasjs.min.js';
import * as $ from 'jquery';
import { Ng2SearchPipeModule } from 'ng2-search-filter';
import {debug} from "ng-packagr/lib/utils/log";

class Loan {
  idL: any;
  loanRef: any;
  score: any;
  quantity: any;
  interest: any;
  totalAmount: any;
  loanPeriodInMonths: any;
  stateL: any;
  customerLoans: any;
  firstName: any;
  lastName: any;
  address: any;
  salary: any;
  job: any;
  socialRate: any;
  numberChildren: any;
  loanPurpose: any;
  guranteeLoan: {
    idGu: any;
    description: any;
    fristName: any;
    lastName: any;

  };
}



@Component({
  selector: 'vex-loan',
  templateUrl: './loan.component.html',
  styleUrls: ['./loan.component.scss'],
  animations: [
    scaleIn400ms,
    stagger40ms,
    fadeInUp400ms,
    scaleFadeIn400ms,
    fadeInRight400ms
  ]
})
export class LoanComponent implements OnInit {

  selectedAmount: any;
  selectedAmount1: any;
  selectedPeriod: any;
  selectedInterest: any;
  config: any;
  collection = { count: 4, data: Loan };
  p = 1;
  fN: any;
  registerForm: any;
  constructor(private loanService: LoanService,
              public dialog: MatDialog) {
    this.config = {
      itemsPerPage: 5,
      currentPage: 1,
      totalItems: 5

    }; }

  listLoans: any;
  Loan!: Loan;
  form = false;
  clickedUser: any;
  userInformation: any;
  display = false;
  x = sessionStorage.getItem('type') ;
  branches: any = [];
  ngOnInit(): void {
    this.loanService.getAllLoans().subscribe((response: any) => {
      this.branches = response;
    });
    this.getAllLoans();
    this.Loan = {
      idL: null,
      loanRef: null,
      score: null,
      quantity: null,
      interest: null,
      totalAmount: null,
      loanPeriodInMonths: null,
      stateL: null,
      customerLoans: null,
      firstName: null,
      lastName: null,
      address: null,
      salary: null,
      job: null,
      socialRate: null,
      numberChildren: null,
      loanPurpose: null,
      guranteeLoan: {
        idGu: null,
        description: null,
        fristName: null,
        lastName: null,

      }

    };



  }
  pageChanged(event){
    this.config.currentPage = event;
  }
  getAllLoans() {
    this.loanService.getAllLoans().subscribe(res => this.listLoans = res);
  }
  getLoan(idLoan: any) {
    this.loanService.getLoan(idLoan).subscribe(res => this.listLoans = res);
  }
  addLoan(p: any, templateRef2) {
    this.loanService.addLoan(p, sessionStorage.getItem("id")).subscribe(() => {
      this.getAllLoans();
    });

    const dialogRef = this.dialog.open(templateRef2, {
      width: '800px'
    });
    dialogRef.afterClosed().subscribe(result => {
      console.log(`Dialog result: ${result}`);

    });


  }
  /*
editLoan(Loan: Loan) {
  this.loanService.editLoan(Loan).subscribe();
}*/
  deleteLoan(idL: any) {
    this.loanService.deleteLoan(idL).subscribe(() => this.getAllLoans());

  }
  acceptLoan(idL: any, loan: Loan) {
    this.loanService.acceptLoan(idL, loan ).subscribe(() => this.getAllLoans());

  }
  denyLoan(idL: any, loan: Loan) {
    this.loanService.denyLoan(idL, loan ).subscribe(() => this.getAllLoans());

  }
  onPress() {
    this.display = true;

  }
  openDialog(templateRef) {
    const dialogRef = this.dialog.open(templateRef, {
      width: '800px'
    });
    dialogRef.afterClosed().subscribe(result => {
      console.log(`Dialog result: ${result}`);

    });
  }
  loadData(){
    const dataPoints = [];
    const dataPoints2 = [];
    $.get("http://localhost:8082/CROTUN/loan/retrieve-all-loans", function(List) {
      $(List).each(function (i) {
        var $dataPoint = $(this);

        var x = List[i].loanRef;
        var y = List[i].score;

        var x2 = List[i].loanPeriodInMonths;
        var y2 = List[i].totalAmount;

        dataPoints.push({x: parseFloat(x), y: parseFloat(y)});
        dataPoints2.push({x: parseFloat(x2), y: parseFloat(y2)});
        console.log(dataPoints[0].y);
      });
      console.log(List[0].score);
      var chart = new CanvasJS.Chart("chartContainer", {
        animationEnabled: true,
        title: {
          text: "Score Distribution - Bar Chart",
        },
        data: [{
          type: "column",
          dataPoints: dataPoints,
        }]
      });
      var chart2 = new CanvasJS.Chart("chartContainer3", {
        animationEnabled: true,
        title: {
          text: "Score Distribution - Pie Chart",
        },
        data: [{
          type: "pie",
          indexLabel: "{y}" ,
          dataPoints: dataPoints,
        }]
      });
      var chart4 = new CanvasJS.Chart("chartContainer4", {
        animationEnabled: true,
        zoomEnabled: true,
        title: {
          text: "Loan amount in relation with loan period ",
        },
        data: [{
          type: "line",
          showInLegend: true,
          dataPoints: dataPoints2,
        }]
      });

      chart.render();
      chart2.render();
      chart4.render();

    });


  }

  Search(fN){
    if(fN === ""){
      this.ngOnInit();
    }else {
      this.branches = this.branches.filter ( res => {
        return res.customerLoans.firstName.match(fN);
      });
    }
  }
  key: string = 'id';
  reverse : boolean = false;
  sort(key){
    this.key = key;
    this.reverse = !this.reverse;
  }

  sim(){
    this.loanService.getSim(this.selectedAmount1, this.selectedPeriod, this.selectedInterest ).subscribe((data)=>{
      this.selectedAmount = data;
    });
  }
  formatLabel(value: number) {
    if (value >= 1000) {
      return Math.round(value / 1000) + 'k';
    }

    return value;
  }

}
