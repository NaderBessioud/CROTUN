import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { PaymentIntentDto } from '../Modals/PaymentIntentDto';

@Injectable({
  providedIn: 'root'
})
export class PaymentEnLigneService {

  constructor(private httpClient : HttpClient) { }


payment_enligne( ids: string , p: PaymentIntentDto ):Observable<any>{
  return this.httpClient.post<any>("http://localhost:8082/CROTUN/paye_enligne/1/" +ids, p ,{})
}
}