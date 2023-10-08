import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import { Observable } from 'rxjs';
import { Agent } from '../Modals/agent';
import { Customer } from '../Modals/customer';
import { Messages } from '../Modals/messages';


@Injectable({
    providedIn: 'root'
})
export class MessageService {
    /////////////Serviceforagent
    converUrl="http://localhost:8082/CROTUN/messages/afficherconver";
    converUrlu="http://localhost:8082/CROTUN/messages/afficherlistcustomers";
    messageUrl="http://localhost:8082/CROTUN/messages/affichermessagesagenagent";
    ajoutmessageUrl="http://localhost:8082/CROTUN/messages/addMessageagentagent"
    deletemessageUrl="http://localhost:8082/CROTUN/messages/deleteMessage";
    ajoutmessageUrlagc="http://localhost:8082/CROTUN/messages/addMessageagentclient"


    ///////Serviceforuser
    converuserUrl="http://localhost:8082/CROTUN/messages/afficherconveruser";
    messageUrlclient="http://localhost:8082/CROTUN/messages/affichermessagesclientagent";
    ajoutmessageclientUrl="http://localhost:8082/CROTUN/messages/addMessageclientagent";


    constructor(private _http:HttpClient) { }


    getConver() : Observable<Agent[]> {
        return this._http.get<Agent[]>(this.converUrl);
    }
    getConveru(Ida) : Observable<Customer[]> {
        return this._http.get<Customer[]>(this.converUrlu,{params:{idagent:Ida}});

    }
    getMessages(IdA,Idb) : Observable<Messages[]> {
        return this._http.get<Messages[]>(this.messageUrl,{params:{idadgent1:IdA,idadgent2:Idb}});
    }
    addMessage(IdA,Idb, m:Messages)
    {return this._http.post(this.ajoutmessageUrl,m,{params:{agentsid:IdA,agentrid:Idb}})
    }
    deleteMessage(IdA)
    {return this._http.delete(this.deletemessageUrl,{params:{idM:IdA}})
    }
    getConveruser(IdA) {
        return this._http.get<Agent>(this.converuserUrl,{params:{iduser:IdA}});
    }
    getMessagesUrl(IdA,Idb) : Observable<Messages[]> {
        return this._http.get<Messages[]>(this.messageUrlclient,{params:{idclient:IdA,idagent:Idb}});
    }
    addMessageclient(IdA,Idb, m:Messages)
    {return this._http.post(this.ajoutmessageclientUrl,m,{params:{clientsid:IdA,agentrid:Idb}})
    }
    addMessageagentclient(IdA,Idb, m:Messages)
    {return this._http.post(this.ajoutmessageUrlagc,m,{params:{agentsid:IdA,clientrid:Idb}})
    }

}
