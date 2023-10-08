import { Component, OnInit } from '@angular/core';
import { PaymentService } from 'src/app/Services/payment.service';
@Component({
  selector: 'vex-payment',
  templateUrl: './payment.component.html',
  styleUrls: ['./payment.component.scss']
})
export class PaymentComponent implements OnInit {

  constructor(private paymentService:PaymentService) { }
 
  list_slices = [];
  p: number = 1;
  x: string = "none";
  term: string;
  get_all_Paye(){
    this.x="";
    this.paymentService.get_all_slices_paye().subscribe((res)=> {
      this.list_slices = [];
      for (let i in res) {
        
        this.list_slices.push(res[i])
    
      }
    })
    
  }
  get_all_Non_Paye(){
    this.x="";
    this.term;
    this.paymentService.get_all_slices_non_paye().subscribe((res)=> {
      this.list_slices = [];
      for (let i in res) {
        
        this.list_slices.push(res[i])
      console.log(res)
      }
    })
    
  }
  display(){
    this.x="display";
  }
  ngOnInit(): void {
  
      
    
   
    }
  }

