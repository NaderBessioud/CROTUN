import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class LoanService {

  readonly API_URL = 'http://localhost:8082/CROTUN/loan';
  constructor(private httpClient: HttpClient) { }
  getAllLoans() {
    return this.httpClient.get(`${this.API_URL}/retrieve-all-loans`);
  }
  addLoan(Loan: any, idLoan: any) {
    return this.httpClient.post(` ${this.API_URL}/add-loan/${idLoan}`, Loan);
  }
  editLoan(Loan: any) {
    return this.httpClient.put(`${this.API_URL}/edit-Loan`, Loan);
  }
  deleteLoan(idLoan: any) {
    return this.httpClient.delete(` ${this.API_URL}/delete-Loan/${idLoan}`);
  }
  acceptLoan(idLoan: any, Loan: any) {
    return this.httpClient.put(` ${this.API_URL}/acceptLoanRequest/${idLoan}`, Loan);
  }
  denyLoan(idLoan: any, Loan: any) {
    return this.httpClient.put(` ${this.API_URL}/denyLoanRequest/${idLoan}`, Loan);
  }
  getLoan(idLoan: any) {
    return this.httpClient.get(`${this.API_URL}/retrieve-loan/${idLoan}`);
  }
  getSim(idLoan: any, m: any, i: any) {
    console.log(idLoan);
    return this.httpClient.get(`${this.API_URL}/creditSimulation?capital=${idLoan}&mois=${m}&interest=${i}`,{responseType:"text"});
  }

}


