import { Injectable } from '@angular/core';

import { HttpClient} from '@angular/common/http';

@Injectable({
    providedIn: 'root'
})
export class InvestmentService {
    readonly API_URL = 'http://localhost:8082';
    constructor(private httpClient: HttpClient) { }
    getMyInvestment(idC : any) {
        return this.httpClient.get(`${this.API_URL}/CROTUN/investment/retrieveInvestorInvestment`, {params: {idC :idC}})
    }
    addInvestment(inv: any,id : any) {
        return this.httpClient.post(`${this.API_URL}/CROTUN/investment/addInvestment`, inv,{params:{id:id}})
    }
    simulationInvestment(start:any,end:any,amount:any){
        return this.httpClient.post(`${this.API_URL}/CROTUN/investment/simulation`, null,{params:{start:start,end:end,amount:amount},responseType:"text"})

    }
    getAllInvestment() {
        return this.httpClient.get(`${this.API_URL}/CROTUN/investment/retrieveAllInvestment`)
    }
    getCountAllInvestment() {
        return this.httpClient.get(`${this.API_URL}/CROTUN/investment/CountAllInvestment`)
    }
    getCountProfitInvestment() {
        return this.httpClient.get(`${this.API_URL}/CROTUN/investment/CountProfitInvestment`)
    }

    getCountTotalInvestment() {
        return this.httpClient.get(`${this.API_URL}/CROTUN/investment/CountTotalInvestment`)
    }


}
