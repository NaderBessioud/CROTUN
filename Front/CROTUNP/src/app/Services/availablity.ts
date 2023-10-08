import { Injectable } from '@angular/core';

import { HttpClient} from '@angular/common/http';

@Injectable({
    providedIn: 'root'
})
export class AvailablityService {
    readonly API_URL = 'http://localhost:8082';
    constructor(private httpClient: HttpClient) { }
    getAllAvai(p:any,time1:any,time2:any,date:any,id:any) {
        return this.httpClient.post(`${this.API_URL}/CROTUN/availablity/addAvai`,p,{params:{time1:time1,time2:time2,date:date,id:id}})
    }

    getDateAvai() {
        return this.httpClient.get(`${this.API_URL}/CROTUN/availablity/dateAvai`)
    }


    getretrieveAllAvai() {
        return this.httpClient.get(`${this.API_URL}/CROTUN/availablity/retrieveAllAvaibli`)
    }

    getretrieveAgentAvaibli(idC:any){
        return this.httpClient.get(`${this.API_URL}/CROTUN/availablity/retrieveAgentAvaibli`,{params:{idC:idC}})
    }


    getAddAvaiCustomer(idA:any,idC:any){
        return this.httpClient.put(`${this.API_URL}/CROTUN/availablity/addAvaiCustomer`,null,{params:{idA:idA,idC:idC}})
    }


    getCustomerAvai(idC:any){
        return this.httpClient.get(`${this.API_URL}/CROTUN/availablity/addAvaiCustomer`,{params:{idC:idC}})
    }

    getAgentAvai(idC:any){
        return this.httpClient.get(`${this.API_URL}/CROTUN/availablity/retrieveAgentAvaibli`,{params:{idC:idC}})
    }
}
