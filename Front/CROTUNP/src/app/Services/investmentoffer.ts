import { Injectable } from '@angular/core';

import { HttpClient} from '@angular/common/http';
import {Observable} from "rxjs-compat/Observable";
import {InvestmentOffer} from "../Modals/investmentOffer";

@Injectable({
    providedIn: 'root'
})
export class InvestmentofferService {
    readonly API_URL = 'http://localhost:8082';

    constructor(private httpClient: HttpClient) { }

    getAllInvestmentoffers() {
        return this.httpClient.get(`${this.API_URL}/CROTUN/investmentoffer/retrieveAllInvestmentOffer`)
    }
    getRecommandation(id:any) {
        return this.httpClient.get(`${this.API_URL}/CROTUN/investmentoffer/recommandation`,{ params: { id : id}})
    }
    getAlloffers() {
        return this.httpClient.get(`${this.API_URL}/CROTUN/investmentoffer/retrieveAllOffers`)
    }

    getCountInvestment() {
        return this.httpClient.get(`${this.API_URL}/CROTUN/investmentoffer/NumberInvestmentOffer`)
    }
    getCountSoldInvestment() {
        return this.httpClient.get(`${this.API_URL}/CROTUN/investmentoffer/NumberSoldInvestmentOffer`)
    }
    getCountUnSoldInvestment() {
        return this.httpClient.get(`${this.API_URL}/CROTUN/investmentoffer/NumberUnSoldInvestmentOffer`)
    }

    getAllArchivedInvestmentoffers() {
        return this.httpClient.get(`${this.API_URL}/CROTUN/investmentoffer/retrieveAllArchivedInvestmentOffer`)
    }

    addInvestmentoffer(investmentoffer : any, id : any) {
        return this.httpClient.post(`${this.API_URL}/CROTUN/investmentoffer/addInvestmentOffer`, investmentoffer,{params:{id:id}} )
    }

    archiveInvestmentoffer(id : any) {
        return this.httpClient.put(`${this.API_URL}/CROTUN/investmentoffer/archiverInvestmentOffer`, null , { params: { id : id}} )
    }
    buyInvestmentOffer(idIO :any, idI : any){
        return this.httpClient.put(`${this.API_URL}/CROTUN/investmentoffer/buyInvestmentOffer`, null , { params: { idIO : idIO , idI : idI}} )
    }
    getInvestorInvestmentoffers(id : any): Observable<InvestmentOffer>{
        return this.httpClient.get<InvestmentOffer>(`${this.API_URL}/CROTUN/investmentoffer/retrieveInvestorInvestmentOffer`,{params: {id :id}})
    }

    getUpdateInvestmentoffers(inv : any) {
        return this.httpClient.put(`${this.API_URL}/CROTUN/investmentoffer/updateInvestmentOffer`,inv)
    }


}
