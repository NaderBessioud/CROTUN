import { Injectable } from '@angular/core';

import { HttpClient} from '@angular/common/http';
import { Complaint } from '../Modals/complaint';
@Injectable({
    providedIn: 'root'
})
export class ComplaintService {
    readonly API_URL = 'http://localhost:8082/CROTUN/complaint';
    http: any;

    constructor(private httpClient: HttpClient) { }
    /*getComplaint(complaintid:any){
      return this.httpClient.get(`${this.API_URL}/retrieve-complaint`,{params:{complaintid:complaintid}});
    }*/

    getAllComplaints(){
        return this.httpClient.get(`${this.API_URL}/retrieve-all-complaints`);
    }

    addComplaint(c: Complaint, idmanager:any, idcustomer:any, idagent:any, num:any, complaintSubject:any ,text:any ){
        return this.httpClient.put(`${this.API_URL}/create1`, c, {params: {idmanager: idmanager,idcustomer: idcustomer,idagent: idagent,num:num, complaintSubject:complaintSubject,text:text}})
    }

    deleteComplaint(complaintid:any) {
        return this.httpClient.delete(`${this.API_URL}/remove-complaint`, {params: {complaintid:complaintid}});
    }

    updateComplaint(c: Complaint, idmanager:any, idcustomer:any, idagent:any){
        return this.httpClient.put(`${this.API_URL}/modify-complaint`, c,{params: {idmanager:idmanager,idcustomer:idcustomer,idagent:idagent}});
    }

    traiterComplaint(idreq:any,Rep:any){
        return this.httpClient.put(`${this.API_URL}/traiter`, {params: {idreq:idreq,Rep:Rep}});
    }

    /*decisionComplaint(idmanager:any){
      return this.httpClient.get(`${this.API_URL}/complaintsSystemDecision`, {params: {idmanager:idmanager}});
    }*/

    themeComplaint(id:any){
        return this.httpClient.get(`${this.API_URL}/TypeOfComplaint`, {params: {id:id}});
    }

    statComplaint(){
        return this.httpClient.get(`${this.API_URL}/NumberTreated`);
    }

    /*  readonly API_URL = 'http://localhost:8082/ComplaintMng/complaint';

      constructor(private httpClient: HttpClient) { }

      getAllProducts() {
        return this.httpClient.get(`${this.API_URL}/all-complaint`)
      }
      addProduct(product : any) {
        return this.httpClient.post(`${this.API_URL}/add-product`, product)
      }
      editProduct(product : any){
        return this.httpClient.put(`${this.API_URL}/edit-product`, product)
      }
      deleteProduct(idProduct : any){
        return  this.httpClient.delete(`${this.API_URL}/delete-product/${idProduct}`)
      }
    */
}
