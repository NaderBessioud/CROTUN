import { Component, OnInit } from '@angular/core';
import { SoldServiceService } from 'src/app/Services/sold-service.service';
@Component({
  selector: 'vex-sold',
  templateUrl: './sold.component.html',
  styleUrls: ['./sold.component.scss']
})
export class SoldComponent implements OnInit {
 sold :number=Number(sessionStorage.getItem("sold"));
  constructor(private soldservice:SoldServiceService) { }
  addsold(){
    let code =(<HTMLInputElement>document.getElementById("cc-code")).value;
  
   this.soldservice.addsold(code,sessionStorage.getItem("id")).subscribe((res)=>{
     if(res!=null){
       this.sold=res;
       alert("sold add")
     }
     else{
       alert("invalid carte")
     }
   })
 
  }
  ngOnInit(): void {
  }

}
