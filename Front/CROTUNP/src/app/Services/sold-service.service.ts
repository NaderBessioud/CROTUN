import { Injectable } from '@angular/core';
import { HttpClient , HttpHeaders} from '@angular/common/http';
import { Observable } from 'rxjs';
@Injectable({
  providedIn: 'root'
})
export class SoldServiceService {

  constructor(private httpClient : HttpClient) { }

  addsold(code,id):Observable<number>{
    return this.httpClient.put<number>("http://localhost:8082/CROTUN/addSold/"+id+"/"+code,{});
  }
}
