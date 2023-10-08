import { Component, OnInit } from '@angular/core';
import {RegistrationService} from "../../Services/registration.service";
import {Router} from "@angular/router";
import {FormControl, Validators} from "@angular/forms";

@Component({
  selector: 'vex-new-password',
  templateUrl: './new-password.component.html',
  styleUrls: ['./new-password.component.scss']
})
export class NewPasswordComponent implements OnInit {
  token:String;
  pass:String;
  confirmPass:String;
  passmatch:boolean=false;
  PassMatch:String="the passwords does not match"
  emailFormControl = new FormControl('', [Validators.required, Validators.email]);
  constructor( private service: RegistrationService,private router: Router) { }

  ngOnInit(): void {
  }
  checkPass(){

      if(this.confirmPass ==this.pass){
        this.passmatch=true;
      }
      else{
        this.passmatch=true;
      }


  }
  Confirmass(){
    this.service.ConfirmPass(this.pass,this.token).subscribe();
    this.router.navigate([""]);
  }



}
