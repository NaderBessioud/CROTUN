import { Injectable } from '@angular/core';
import { HttpClient , HttpHeaders} from '@angular/common/http';
import {Observable} from "rxjs";
@Injectable({
  providedIn: 'root'
})
export class CreditCardService {

  constructor(private httpClient : HttpClient) { }
  get_all_cards():Observable<any[]> {
   return this.httpClient.get<any[]>("http://localhost:8082/CROTUN/retrieve-all-CreditCard/");
  }
  delete_card(id) {
    return this.httpClient.delete("http://localhost:8082/CROTUN/remove-CreditCard/"+ id + "/");
   }
   add_card(amount, number,id){
    return this.httpClient.post("http://localhost:8082/CROTUN/add-CreditCard/"+ number + "/" + amount+ "/"+id+"/", {});
   }
}
