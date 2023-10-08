import { Component, Input, Output, EventEmitter, OnInit } from '@angular/core';
import {LoanService} from '../../Services/loan.service';
import { LoanComponent } from '../loan/loan.component';

class Loan {
  idL: any;
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
}

@Component({
  selector: 'vex-loan-info',
  templateUrl: './loan-info.component.html',
  styleUrls: ['./loan-info.component.scss']
})



export class LoanInfoComponent implements OnInit {
  @Input() userInfo: any;
  @Input() item: any;
  @Output() notify: EventEmitter<any> = new EventEmitter<any>();

  clickedUser: any;

  constructor(private loanService: LoanService) { }
  listLoans: any;
  Loan!: Loan;
  form = false;

  ngOnInit(): void {
    this.getLoan(1);
    this.clickedUser = this.userInfo;
    this.Loan = {
      idL: null,
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
    };
  }
  getAllLoans() {
    this.loanService.getAllLoans().subscribe(res => this.listLoans = res);
  }
  getLoan(idLoan: any) {
    this.loanService.getLoan(idLoan).subscribe(res => this.listLoans = res);
  }


}
