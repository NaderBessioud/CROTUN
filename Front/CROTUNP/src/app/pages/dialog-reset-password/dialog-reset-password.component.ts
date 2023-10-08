import { Component, OnInit } from '@angular/core';
import { RegistrationService } from 'src/app/Services/registration.service';
import { FormControl, Validators } from '@angular/forms';
import {Router} from "@angular/router";

@Component({
  selector: 'vex-dialog-reset-password',
  templateUrl: './dialog-reset-password.component.html',
  styleUrls: ['./dialog-reset-password.component.scss']
})
export class DialogResetPasswordComponent implements OnInit {
  email:string="";
  existe:boolean=false;
  emailFormControl = new FormControl('', [Validators.required, Validators.email]);


  constructor(private service:RegistrationService,private router: Router) { }

  ngOnInit(): void {
  }

   CheckEmail():boolean{
    this.service.verifyEmail(this.email).subscribe(data=>{
      if(data == 1){
        return false;
      }
      else{
        return true;
      }
    })
     return false;
  }
  SentPassToken(){
    if( !this.CheckEmail()){
      this.service.passwordReset(this.email).subscribe(data=>{
        this.router.navigate(["resetPass"]);
      })
    }
    else{
      alert("this email does not exist");



    }

  }

}
