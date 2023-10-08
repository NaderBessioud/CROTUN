import { Component, OnInit } from '@angular/core';
import {SliceService} from '../../Services/slice.service';

class Slice {
  idS: any;
  idL: any;
  reference: any;
  repaymentDate: any;
  verified: any;
  paymentSlice: any;
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
  selector: 'vex-slice',
  templateUrl: './slice.component.html',
  styleUrls: ['./slice.component.scss']
})



export class SliceComponent implements OnInit {
  config: any;
  p = 1;
  constructor(private sliceService: SliceService) {
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

  ngOnInit(): void {
    this.getAllslices();
    this.Slice = {
      idS: null,
      idL: null,
      reference: null,
      repaymentDate: null,
      verified: null,
      paymentSlice: null,
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
      this.sliceService.getL().subscribe(res => this.sliceLoan = res);
  }
  getAllslices() {
    this.sliceService.getAllslices().subscribe(res => this.listSlices = res);
  }
  exportToPDF() {
    this.sliceService.exportToPDF().subscribe(res => this.listSlices = res);
  }
  addslice(idslice: any, slice: any) {
    this.sliceService.addslice(idslice, slice).subscribe(() => {
      this.getAllslices();
      this.form = false;
    });
    console.log(idslice);
  }
}
