import { Injectable } from '@angular/core';
import {Observable} from "rxjs";
import {Notification} from "../Modals/notification";
import {HttpClient} from "@angular/common/http";

@Injectable({
  providedIn: 'root'
})
export class NorificationService {
  converUrl="http://localhost:8082/CROTUN/notifications/affichenotifclient";
  converUrlu="http://localhost:8082/CROTUN/notifications/affichenotifagent";

  constructor(private _http:HttpClient) { }
  getnotifclient(Ida) : Observable<Notification[]> {
    return this._http.get<Notification[]>(this.converUrl,{params:{idclient:Ida}});

  }
  getnotifahent(Ida) : Observable<Notification[]> {
    return this._http.get<Notification[]>(this.converUrlu,{params:{idagent:Ida}});

  }

}
