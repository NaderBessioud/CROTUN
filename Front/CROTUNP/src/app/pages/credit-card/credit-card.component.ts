import { Component, OnInit } from '@angular/core';
import {CreditCardService} from "src/app/Services/credit-card.service";

@Component({
  selector: 'vex-credit-card',
  templateUrl: './credit-card.component.html',
  styleUrls: ['./credit-card.component.scss']
})
export class CreditCardComponent implements OnInit {

  constructor(private cardService : CreditCardService) { }
  list_cards = [];
  p: number = 1;
  add_credit_card(){
    let amount =(<HTMLInputElement>document.getElementById("cc-range")).value;
    let number =(<HTMLInputElement>document.getElementById("cc-amount")).value;
    if (amount == '') {
      alert("please fill the amount")
      return;
    }
    this.cardService.add_card(amount, number,sessionStorage.getItem("id")).subscribe((res)=> {
      this.cardService.get_all_cards().subscribe((res) => {
        console.log(res)
        this.list_cards = []
        for (let elem in res) {
          console.log(elem)
          this.list_cards.push(res[elem]);
        }
        
      }, (err) => {
        console.log(err)
      })
    })

  }
  delete_card(id){
    this.cardService.delete_card(id).subscribe((res) => {
      this.cardService.get_all_cards().subscribe((res) => {
        console.log(res)
        this.list_cards = []
        for (let elem in res) {
          console.log(elem)
          this.list_cards.push(res[elem]);
        }
        
      }, (err) => {
        console.log(err)
      })
    })
  }
  ngOnInit(): void {
    console.log("getting creduit card list")
    this.cardService.get_all_cards().subscribe((res) => {
      console.log(res)
      this.list_cards = []
      for (let elem in res) {
        console.log(elem)
        this.list_cards.push(res[elem]);
      }
      
    }, (err) => {
      console.log(err)
    })
  }

}
