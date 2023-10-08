import { Injectable } from '@angular/core';
import { HttpClient , HttpEvent , HttpRequest} from '@angular/common/http';
import { Observable } from 'rxjs';
import { Reponse } from '../reponse';

@Injectable({
  providedIn: 'root'
})
export class MethodePaymentService {

  constructor(private httpClient : HttpClient) { }
  payment_avec_sold(id,idc):Observable<Reponse>{
    return this.httpClient.post<Reponse>("http://localhost:8082/CROTUN/Paye-With-Sold-card/"+idc+"/"+id,{})

  }
  upload(file: File, id,idc): Observable<HttpEvent<any>> {
    const formData: FormData = new FormData();
    formData.append('Image', file);
    const req = new HttpRequest('POST', "http://localhost:8082/CROTUN/Verifier-recu/"+idc+"/" +id , formData, {
      reportProgress: true,
      responseType: 'json'
    });
    return this.httpClient.request(req);
  }
}
