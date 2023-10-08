import { ChangeDetectionStrategy, ChangeDetectorRef, Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { MatSnackBar } from '@angular/material/snack-bar';
import icVisibility from '@iconify/icons-ic/twotone-visibility';
import icVisibilityOff from '@iconify/icons-ic/twotone-visibility-off';
import {fadeInUp400ms} from '../../../@vex/animations/fade-in-up.animation';
import {UserService} from '../../providers/user.service';
import { DataService } from 'src/app/providers/data.service';
import { first } from 'rxjs/operators';

import { ToastrService } from 'ngx-toastr';
import {SlimLoadingBarService} from 'ng2-slim-loading-bar';
import { AuthenticationService } from 'src/app/providers/authentication.service';
import {Agent} from "../../Modals/agent";
import {RegistrationService} from "../../Services/registration.service";

@Component({
  selector: 'vex-influencersignup',
  templateUrl: './influencersignup.component.html',
  styleUrls: ['./influencersignup.component.scss'],
  // changeDetection: ChangeDetectionStrategy.OnPush,
  animations: [
    fadeInUp400ms
  ]
})
export class InfluencerSignUpComponent implements OnInit {
  form: FormGroup;

  inputType = 'password';
  visible = false;

  icVisibility = icVisibility;
  icVisibilityOff = icVisibilityOff;

  progressbar_visible = false;
  progressbar_value = 0;
  Agent:Agent=new Agent();

  dispoEmail:boolean=false
  dispoEmail1:boolean=false
  dispoUsername:boolean=false
  dispoUsername1:boolean=false

  DisponibleEmail: String="Disonible";
  DisponibleUsername: String="Disonible";
  visiblePass:boolean=false;
  matchPass:boolean=false;
  PassMatch:String="2 password does not match";
  pass:String ="";

  constructor(private router: Router,
              private fb: FormBuilder,
              private cd: ChangeDetectorRef,
              private snackbar: MatSnackBar,
              public userService: UserService,
              public dataService: DataService,
              private toastr: ToastrService,
              public _loadingBar: SlimLoadingBarService,

              private service :RegistrationService
  ) {
    this._loadingBar.events.subscribe(items => {
      if(items.type == 0)
        this.progressbar_value = items.value;
    })
  }

  ngOnInit() {
    this.form = this.fb.group({
      email: ['', Validators.required],
      password: ['', Validators.required],
      first_name: ['', Validators.required],
      last_name: ['', Validators.required]
    });
  }
  signup(){
    this.service.registerAgent(this.Agent,"CROTUN_99fa8387-ace8-4e53-920e-32cf57ccbb3aavatar.png").subscribe(data =>{
      if(data != null){
        this.router.navigate(["/signin/brand"]);
      }
    })
  }

  send() {
    if(this.validateEmail(this.form.get('email').value)){
      if(this.form.get('email').value !== '' && this.form.get('password').value !== '' && this.form.get('first_name').value !== '' && this.form.get('last_name').value !== '')
      {
        this.progressbar_visible = true;
        this._loadingBar.start();

        const data = { 
          first_name: this.form.get('first_name').value,
          last_name: this.form.get('last_name').value,
          user : {
            email: this.form.get('email').value,
            password: this.form.get('password').value
          }          
        }
        this.dataService.createInfluencerAccount(data)
        .pipe(first())
        .subscribe((content:any) => {



          

        });
      }
      else
        this.snackbar.open('Please fill out all fields', 'Got It!', {
        duration: 10000});
    }
    else {
      this.snackbar.open('Please enter a valid email', 'Got It!', {
        duration: 10000});
    }
  
  }

  validateEmail(email) {
    const re = /^(([^<>()[\]\\.,;:\s@"]+(\.[^<>()[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
    if (re.test(String(email).toLowerCase()))
    {
      return (true)
    }
    return false;
  }
  switchViewer(type) {
    this.userService.afterLogin(type);
    if(type !== '' && type !== 'brandsignup')
      this.router.navigate([type,'influencer']);
    else
      this.router.navigate([type]);
  }

  toggleVisibility() {
    if (this.visible) {
      this.inputType = 'password';
      this.visible = false;
      this.cd.markForCheck();
    } else {
      this.inputType = 'text';
      this.visible = true;
      this.cd.markForCheck();
    }
  }
  registerAgent(){
    this.service.registerAgent(this.Agent,"test").subscribe(data=>{
      if(data != null){
        this.router.navigate(["signin/influencer"])
      }
    })
  }

  CheckEmail(){
    this.service.verifyEmail(this.Agent.email).subscribe(data =>{
      if(data == 1){
        this.DisponibleEmail="Email already exist"
        this.dispoEmail=true
        this.dispoEmail1=false;
      }
      else {
        this.DisponibleEmail="Disponible"
        this.dispoEmail=true
        this.dispoEmail1=true;
      }
    })
  }

  CheckUsername(){

    console.log(this.Agent);

    this.service.verifyUsername(this.Agent.userName).subscribe(data =>{

      if(data == 1){
        this.DisponibleUsername="Username already exist"
        this.dispoUsername=true
        this.dispoUsername1=false;
      }
      else {
        this.DisponibleUsername="Disponible"
        this.dispoUsername=true
        this.dispoUsername1=true;
      }
    })

  }

  checkPass(){
    if(this.Agent.password ==this.pass){
      this.visiblePass=false
      this.matchPass=false;
    }
    else{
      this.visiblePass=true;
      this.matchPass=true;
    }
  }


}
