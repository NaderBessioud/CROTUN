import { Injectable } from '@angular/core';
import { HttpClient , HttpHeaders} from '@angular/common/http';
@Injectable({
  providedIn: 'root'
})
export class PaymentService {

  constructor(private httpClient : HttpClient) { }
  get_all_slices_paye() {
    return this.httpClient.get("http://localhost:8082/CROTUN/slice/retrieve-all-Slices-paye/");
   }
   get_all_slices_non_paye() {
    return this.httpClient.get("http://localhost:8082/CROTUN/slice/retrieve-all-Slices-non-paye/");
   }
}
