import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import { Observable } from 'rxjs';
import {Customer} from "../Modals/customer";
import {Agent} from "../Modals/agent";
import {Investor} from "../Modals/investor";
import {Manager} from "../Modals/manager";
import {ImageResponse} from "../Modals/image-response";
import { CustomerPenality } from '../Modals/customer-penality';
import { Customerpenlitydate } from '../Modals/customerpenlitydate';

@Injectable({
  providedIn: 'root'
})
export class RegistrationService {

  url : string = 'http://localhost:8082/CROTUN/';
  url2 : string = 'http://127.0.0.1:5000/';
  constructor(private http: HttpClient) { }

  login( username, pass): Observable<any> {

    return this.http.get(this.url+"auth/login",{params:{username:username,pass:pass}});
  }

  verifyEmail(email):Observable<Number>{
    return this.http.get<Number>(this.url+"auth/verifyEmail",{params:{email:email}})
  }

  verifyUsername(username):Observable<Number>{
    return this.http.get<Number>(this.url+"auth/verifyUsername",{params:{username:username}})
  }
  passwordReset(email){
    return this.http.get(this.url+"auth/resetPasswordRequest",{params:{email:email}})
  }
  register(C:Customer,image){
    return this.http.post(this.url+"auth/addCustomer",C,{params:{image:image}});
  }

  registerAgent(A:Agent,image){
    return this.http.post(this.url+"auth/addAgent",A,{params:{image:image}});
  }
  registerInvestor(I:Investor,image){
    return this.http.post(this.url+"auth/addInvestor",I,{params:{image:image}});
  }

  ConfirmPass(pass,token){
    return this.http.get(this.url+"auth/resetPassword",{params:{token:token,pass:pass}})
  }

  getNewDeviceActivity(user,ip): Observable<any>{

    return this.http.get(this.url+"auth/ConsultDeviceActivity",{params:{user:user,ip:ip}})
  }

  ConfirmAgentDevice(details,loc,username){
    return this.http.get(this.url+"auth/confirmAgentDevice",{params:{details:details,loc:loc,username:username}})
  }

  ConfirmCustomerDevice(details,loc,username){
    return this.http.get(this.url+"auth/confirmCustomerDevice",)
  }

  ConfirmInvestorDevice(details,loc,username){
    return this.http.get(this.url+"auth/confirmInvestorDevice",{params:{details:details,loc:loc,username:username}})
  }

  ConfirmManagerDevice(details,loc,username){
    return this.http.get(this.url+"auth/confirmManagerDevice",{params:{details:details,loc:loc,username:username}})
  }

  UpdateAgentProfil(a:Agent) {
    return this.http.put(this.url + "agent/updateAgent", a);
  }

  UpdateCustomerProfile(c:Customer):Observable<Customer>{
    c.idC=sessionStorage.getItem("id");
    return this.http.put<Customer>(this.url + "customer/updateCustomer", c);
  }

  UpdateInvestorProfile(i:Investor){
    return this.http.put(this.url + "investor/updateInvestor", i);
  }

  UpdateManagerProfile(m:Manager){
    return this.http.put(this.url + "manager/updateManager", m);
  }


  ImageUpload(image:FormData):Observable<ImageResponse>{
    return this.http.post<ImageResponse>(this.url + "auth/upload",image);
  }

  DownloadImage(name):Observable<ImageResponse>{
    return this.http.get<ImageResponse>(this.url + "auth/download",{params:{image:name}})
  }

  upadateAgentPass(pass,username){
    return this.http.put(this.url+"agent/updatePass",{},{params:{pass:pass,username:username}})
  }
  upadateCustomerPass(pass,username){
    return this.http.put(this.url+"customer/updatePass",{},{params:{pass:pass,username:username}})
  }

  upadateInvestorPass(pass,username){
    return this.http.put(this.url+"investor/updatePass",{},{params:{pass:pass,username:username}})
  }

  upadateManagerPass(pass,username){
    return this.http.put(this.url+"manager/updatePass",{},{params:{pass:pass,username:username}})
  }


  checkAgentPass(pass,username){
    return this.http.get(this.url+"agent/checkPass",{params:{pass:pass,username:username}})
  }

  checkCustomerPass(pass,username){
    return this.http.get(this.url+"customer/checkPass",{params:{pass:pass,username:username}})
  }


  checkInvestorPass(pass,username){
    return this.http.get(this.url+"investor/checkPass",{params:{pass:pass,username:username}})
  }

  checkManagerPass(pass,username){
    return this.http.get(this.url+"manager/checkPass",{params:{pass:pass,username:username}})
  }

  getAgentByUsername(user){
    return this.http.get(this.url+"agent/getAgentByusername",{params:{username:user}});
  }


  getCustomerByUsername(user){
    return this.http.get(this.url+"customer/getCustomerByUsername",{params:{user:user}});
  }


  getInvestorByUsername(user){
    return this.http.get(this.url+"investor/getInvestorByusername",{params:{username:user}});
  }


  getManagerByUsername(user){
    return this.http.get(this.url+"manager/getManagerByusername",{params:{user:user}});
  }


  getAgentCard():Observable<any[]>{
    return this.http.get<any[]>(this.url+"manager/getAgentCards");
  }

  getPenalityCustomer():Observable<CustomerPenality[]>{
    return this.http.get<CustomerPenality[]>(this.url+"manager/getPenality");
  }

  getPenalityCustomerDate(id):Observable<Customerpenlitydate[]>{
    return this.http.get<Customerpenlitydate[]>(this.url+"manager/getPenalityByCustomerByDate",{params:{id:id}});
  }

  getAddress(lat,lng){
    return this.http.get(this.url+"auth/getAddress",{params:{lat:lat,lng:lng},responseType:"text"});

  }
  facialReco(){
    return this.http.get(this.url2,{responseType:"text"});
  }


    loginAgent( username): Observable<Agent> {

        return this.http.get<Agent>(this.url+"auth/loginAgent",{params:{username:username}});
    }










}
