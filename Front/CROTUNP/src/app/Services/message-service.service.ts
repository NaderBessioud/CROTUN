import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {Customer} from "../Modals/customer";

@Injectable({
  providedIn: 'root'
})
export class MessageServiceService {
  converUrl="http://localhost:8082/CROTUN/messages/afficherconver";
  converUrlu="http://localhost:8082/CROTUN/messages/afficherlistcustomers";
  messageUrl="http://localhost:8082/CROTUN/messages/affichermessagesagenagent";
  ajoutmessageUrl="http://localhost:8082/CROTUN/messages/addMessageagentagent"
  deletemessageUrl="http://localhost:8082/CROTUN/messages/deleteMessage"
  ///////Serviceforuser
  converuserUrl="http://localhost:8082/CROTUN/messages/afficherconveruser";
  messageUrlclient="http://localhost:8082/CROTUN/messages/affichermessagesclientagent";
  ajoutmessageclientUrl="http://localhost:8082/CROTUN/messages/addMessageclientagent";

  constructor(private _http:HttpClient) {


    }
  getConveru(Ida) : Observable<Customer[]> {
    return this._http.get<Customer[]>(this.converUrlu,{params:{idagent:Ida}});





  }
}
