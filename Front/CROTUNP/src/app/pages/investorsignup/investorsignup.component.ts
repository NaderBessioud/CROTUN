import {ChangeDetectorRef, Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {Customer} from "../../Modals/customer";
import {Router} from "@angular/router";
import {MatSnackBar} from "@angular/material/snack-bar";
import {UserService} from "../../providers/user.service";
import {DataService} from "../../providers/data.service";
import {ToastrService} from "ngx-toastr";
import {SlimLoadingBarService} from "ng2-slim-loading-bar";
import {AuthenticationService} from "../../providers/authentication.service";
import {RegistrationService} from "../../Services/registration.service";
import {first} from "rxjs/operators";
import {fadeInUp400ms} from "../../../@vex/animations/fade-in-up.animation";
import icVisibility from '@iconify/icons-ic/twotone-visibility';
import icVisibilityOff from '@iconify/icons-ic/twotone-visibility-off';
import {Investor} from "../../Modals/investor";

@Component({
  selector: 'vex-investorsignup',
  templateUrl: './investorsignup.component.html',
  styleUrls: ['./investorsignup.component.scss'],
  animations: [
    fadeInUp400ms
  ]
})
export class InvestorsignupComponent implements OnInit {

  form: FormGroup;

  inputType = 'password';
  visible = false;

  icVisibility = icVisibility;
  icVisibilityOff = icVisibilityOff;

  progressbar_visible = false;
  progressbar_value = 0;
  investor:Investor=new Investor();



  dispoEmail:boolean=false
  dispoEmail1:boolean=false
  dispoUsername:boolean=false
  dispoUsername1:boolean=false

  DisponibleEmail: String="Disonible";
  DisponibleUsername: String="Disonible";
  visiblePass:boolean=false;
  matchPass:boolean=false;
  PassMatch:String="2 password does not match"
  pass:String ="";
  constructor(private router: Router,
              private fb: FormBuilder,
              private cd: ChangeDetectorRef,
              private snackbar: MatSnackBar,
              public userService: UserService,
              public dataService: DataService,
              private toastr: ToastrService,
              public _loadingBar: SlimLoadingBarService,
              public authService: AuthenticationService,
              private service: RegistrationService
  ) {
    this._loadingBar.events.subscribe(items => {
      if(items.type == 0)
        this.progressbar_value = items.value;
    });

  }

  ngOnInit() {



    this.form = this.fb.group({
      email: ['', Validators.required],
      password: ['', Validators.required],
      name: ['', Validators.required]
    });
  }

  send() {
    if(this.validateEmail(this.form.get('email').value)){
      if(this.form.get('email').value !== '' && this.form.get('password').value !== '' && this.form.get('name').value !== '')
      {
        // console.log('come in');
        this.progressbar_visible = true;
        this._loadingBar.start();

        const data = {
          company_name: this.form.get('name').value,
          user : {
            email: this.form.get('email').value,
            password: this.form.get('password').value
          }
        }
        //this.dataService.createAdvertiserAccount(data)
        this.dataService.createNewAdvertiser(data)
            .pipe(first())
            .subscribe(content => {
                  this.authService.login(this.form.get('email').value, this.form.get('password').value)
                      .pipe()
                      .subscribe(cdata => {
                        this.userService.afterLogin('advertiser');
                        this.userService.currentUser.company = this.form.get('name').value;
                        this.userService.currentUser.email = this.form.get('email').value;

                        this.userService.currentUser.profileId = cdata.profile_uuid;

                        localStorage.setItem('type', 'advertiser');
                        this._loadingBar.complete();
                        this.router.navigate(['/panel/user/setting']);
                      });

                },
                error => {
                  this._loadingBar.complete();
                  this.toastr.error('The email that you entered has been claimed by other users. Please use a different email or contact us at info@infinovae.com','', {
                    timeOut: 6000,
                    positionClass: 'toast-bottom-right',
                    progressBar: true
                  });
                });
        //this.router.navigate(['/panel/user/setting']);
        // this.snackbar.open('Lucky you! Looks like you didn\'t need a password or email address! For a real application we provide validators to prevent this. ;)', 'Got It!', {
        //   duration: 10000
        // });
      }
      else
        this.snackbar.open('Please fill out all fields', 'Got It!', {
          duration: 10000});
    }
    else {
      this.snackbar.open('Please check email', 'Got It!', {
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

    if(type !== '' && type !== 'influencersignup')
      this.router.navigate([type,'investor']);
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

  signup(){
    this.service.registerInvestor(this.investor,"CROTUN_99fa8387-ace8-4e53-920e-32cf57ccbb3aavatar.png").subscribe(data =>{
      if(data != null){
        this.router.navigate(["/signin/investor"]);
      }
    })
  }

  CheckEmail(){
    this.service.verifyEmail(this.investor.email).subscribe(data =>{
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



    this.service.verifyUsername(this.investor.userName).subscribe(data =>{
      console.log(data);
      console.log(this.investor.userName);
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
    if(this.investor.password ==this.pass){
      this.visiblePass=false
      this.matchPass=false;
    }
    else{
      this.visiblePass=true;
      this.matchPass=true;
    }
  }


}
