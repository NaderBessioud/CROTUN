import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { PaymentIntentDto } from 'src/app/Modals/PaymentIntentDto';
import { PaymentEnLigneService } from 'src/app/Services/payment-en-ligne.service';

@Component({
  selector: 'vex-payment-en-ligne',
  templateUrl: './payment-en-ligne.component.html',
  styleUrls: ['./payment-en-ligne.component.scss']
})
export class PaymentEnLigneComponent implements OnInit {
   p: PaymentIntentDto = new PaymentIntentDto();
  constructor(private payService : PaymentEnLigneService,private activatedroute : ActivatedRoute) { }
  id: any;
  ngOnInit(): void {
    this.id = this.activatedroute.snapshot.paramMap.get('id');

  }

  payeEnLigne(ids: string){
  
    this.payService.payment_enligne(this.id, this.p).subscribe((res) => {
      console.log(res)
      alert(res.reponse);

  });
}
}
