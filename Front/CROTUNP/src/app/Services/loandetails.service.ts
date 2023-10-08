import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class LoandetailsService {

  constructor(private httpClient: HttpClient) { }

  readonly API_URL = 'http://localhost:8082/CROTUN/DetailLoan';

  getL(){
    return this.httpClient.get('http://localhost:8082/CROTUN/loan/retrieve-all-loans');
  }
  getAllDetailLoans() {
    return this.httpClient.get(`${this.API_URL}/retrieve-all-detailLoan`);
  }
  addDetailLoan(idDetailLoan: any, DetailLoan: any) {
    return this.httpClient.post(` ${this.API_URL}/add-detailLoan-lists/${idDetailLoan}`, DetailLoan);
  }
  editDetailLoan(DetailLoan: any) {
    return this.httpClient.put(`${this.API_URL}/edit-DetailLoan`, DetailLoan);
  }
  deleteDetailLoan(idDetailLoan: any) {
    return this.httpClient.delete(` ${this.API_URL}/delete-DetailLoan/${idDetailLoan}`);
  }
  acceptDetailLoan(idDetailLoan: any, DetailLoan: any) {
    return this.httpClient.put(` ${this.API_URL}/acceptDetailLoanRequest/${idDetailLoan}`, DetailLoan);
  }
  exportToPDF() {
    return this.httpClient.get(`${this.API_URL}/export/excel`);
  }
}
