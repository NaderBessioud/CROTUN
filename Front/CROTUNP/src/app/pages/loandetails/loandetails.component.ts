import { Component, OnInit } from '@angular/core';
import {LoandetailsService} from '../../Services/loandetails.service';
import * as $ from "jquery";
import * as CanvasJS from '../../../assets/canvasjs.min';
class Slice {
  idS: any;
  idL: any;
  MontantRestant: any;
  Interest: any;
  Amortissements: any;
  mensualite: any;
  rip: any;
  customerLoans: any;
  firstName: any;
  lastName: any;
  price: any;
}
class Slice2 {
  idL: any;
  loanRef: any;
  stateL: any;
}
@Component({
  selector: 'vex-loandetails',
  templateUrl: './loandetails.component.html',
  styleUrls: ['./loandetails.component.scss']
})
export class LoandetailsComponent implements OnInit {
  config: any;
  p = 1;
  constructor(private loandetailsService: LoandetailsService) {
    this.config = {
      itemsPerPage: 3,
      currentPage: 1,
      totalItems: 40

    }; }
  listSlices: any;
  Slice!: Slice;
  Slice2!: Slice2;
  form = false;
  sliceLoan: any;
  selectedOption: any;
  printedOption: any;
  x = sessionStorage.getItem('type') ;

  ngOnInit(): void {
    this.getAllDetailLoans();
    this.Slice = {
      idS: null,
      idL: null,
      MontantRestant: null,
      Interest: null,
      Amortissements: null,
      mensualite: null,
      rip: null,
      customerLoans: null,
      firstName: null,
      lastName: null,
      price: null,
    };
    this.getL();
    this.Slice2 = {
      idL: null,
      loanRef: null,
      stateL: null
    };
  }
  pageChanged(event){
    this.config.currentPage = event;
  }
  getL(){
    this.loandetailsService.getL().subscribe(res => this.sliceLoan = res);
  }
  getAllDetailLoans() {
    this.loandetailsService.getAllDetailLoans().subscribe(res => this.listSlices = res);
  }
  exportToPDF() {
    this.loandetailsService.exportToPDF().subscribe(res => this.listSlices = res);
  }
  addslice(idslice: any, slice: any) {
    this.loandetailsService.addDetailLoan(idslice, slice).subscribe(() => {
      this.getAllDetailLoans();
      this.form = false;
    });
    console.log(idslice);
  }
  loadDatad(){
    const dataPoints = [];

    $.get("http://localhost:8082/CROTUN/DetailLoan/retrieve-all-detailLoan", function(List) {
      $(List).each(function (i) {
        var $dataPoint = $(this);

        var x = List[i].montantRestant;
        var y = List[i].interest;
        console.log(x);
        dataPoints.push({x: parseFloat(x), y: parseFloat(y)});

      });

      var chart = new CanvasJS.Chart("chartContainer2", {
        animationEnabled: true,
        title: {
          text: "Interest",
        },
        data: [{
          type: "pie",
          showInLegend: true,
          dataPoints: dataPoints,
        }]
      });

      chart.render();
    });
  }
}
