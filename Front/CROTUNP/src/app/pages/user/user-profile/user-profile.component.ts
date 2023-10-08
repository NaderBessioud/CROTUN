import { Component, OnInit } from '@angular/core';
import {scaleIn400ms} from '../../../../@vex/animations/scale-in.animation';
import {fadeInRight400ms} from '../../../../@vex/animations/fade-in-right.animation';
import {stagger40ms} from '../../../../@vex/animations/stagger.animation';
import {fadeInUp400ms} from '../../../../@vex/animations/fade-in-up.animation';
import {scaleFadeIn400ms} from '../../../../@vex/animations/scale-fade-in.animation';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {ActivatedRoute, Router} from '@angular/router';
import {User, UserService} from '../../../providers/user.service';
import {DatePipe} from '@angular/common';
import {BankAcc, CCard} from '../../campaign/interfaces/payment.interface';
import {checkMatch} from '../../../helpers/match.validator';
import icCancel from '@iconify/icons-ic/outline-clear';
import {profCatsData} from '../../../../static-data/categories';

import { DataService } from '../../../providers/data.service'
import {Customer} from "../../../Modals/customer";
import {Agent} from "../../../Modals/agent";
import {Investor} from "../../../Modals/investor";
import {Manager} from "../../../Modals/manager";
import {RegistrationService} from "../../../Services/registration.service";
import {HttpResponse} from "@angular/common/http";
import { take } from 'rxjs/operators';

@Component({
  selector: 'vex-user-profile',
  templateUrl: './user-profile.component.html',
  styleUrls: ['./user-profile.component.scss'],
  animations: [
    scaleIn400ms,
    fadeInRight400ms,
    stagger40ms,
    fadeInUp400ms,
    scaleFadeIn400ms
  ]
})
export class UserProfileComponent implements OnInit {
  c:Customer=new Customer();
  a:Agent;
  i:Investor=new Investor();
  m:Manager=new Manager();
  userType:String="";
  adr:String;
  uploadedImage: File;
  dbImage: any;
  postResponse: any;
  successResponse: string;
  image: any;
  Response:HttpResponse<any>;
  newpass1:String;
  newpass2:String;
  passMatch:boolean=false;
  passmatchString:String="2 password does not match";

  oldpassmatch:boolean=false;
  oldpassmatchString:String="old password does not match";





  profileFormGroup: FormGroup;
  ccFormGroup: FormGroup;
  bankFormGroup: FormGroup;
  categories = profCatsData;
  profile: User;
  disableEmail = true;
  disablePhone = true;
  ccards: CCard[] = [];
  curCardId = 0;
  bankAcc: BankAcc;
  paymentOpt: string;
  icCancel = icCancel;

  constructor(private fb: FormBuilder,
              private route: ActivatedRoute,
              public userService: UserService,
              private router: Router,
              public dataService: DataService,
              private service:RegistrationService

  ) {
    this.profile = this.userService.currentUser;
    this.a=new Agent();
  }

  ngOnInit(): void {

    this.userType=sessionStorage.getItem("type");

    if(this.userType=="customer"){

      this.c.userName=sessionStorage.getItem("username");
      this.c.email=sessionStorage.getItem("email");
      this.c.birthDate=sessionStorage.getItem("birthdate");
      this.c.address=sessionStorage.getItem("address");
      this.c.salary=sessionStorage.getItem("salary");
      this.c.firstName=sessionStorage.getItem("firstname");
      this.c.lastName=sessionStorage.getItem("lastname");
      this.c.cin=sessionStorage.getItem("cin");
      this.c.Sex=sessionStorage.getItem("gender");
      this.c.job=sessionStorage.getItem("job");
      this.profileFormGroup = this.fb.group({
        ctrlbirth: [this.c.birthDate],
        ctrlemail: [this.c.email],
        firstName:[this.c.firstName],
        lastName: [this.c.lastName],
        addressline1: [this.c.address],
        salary: [this.c.salary],
        username:[this.c.userName],
        cin:[this.c.cin],
        gender: [this.c.Sex],
        pass: [null],
        job: [this.c.job],
        newpass1: [null],
        newpass2: [null]
      });
    }
    else if(this.userType=="agent"){
      console.log("-------------------->"+sessionStorage.getItem("cin"));
      this.a.cin=sessionStorage.getItem("cin");
      this.a.userName=sessionStorage.getItem("username");

      this.a.email=sessionStorage.getItem("email");
      this.a.birthDate=sessionStorage.getItem("birthdate");
      this.a.address=sessionStorage.getItem("address");
      this.a.salary=sessionStorage.getItem("salary");
      this.a.firstName=sessionStorage.getItem("firstname");
      this.a.lastName=sessionStorage.getItem("lastname");
      this.profileFormGroup = this.fb.group({
        ctrlbirth: [this.a.birthDate],
        ctrlemail: [this.a.email],
        firstName:[this.a.firstName],
        lastName: [this.a.lastName],
        addressline1: [this.a.address],
        salary: [this.a.salary],
        username:[this.a.userName],
        cin: [this.a.cin],
        pass: [null],
        newpass1: [null],
        newpass2: [null]
      });



    }
    else if(this.userType=="investor"){
      this.i.CIN=sessionStorage.getItem("cin");
      this.i.userName=sessionStorage.getItem("username");
      this.i.email=sessionStorage.getItem("email");
      this.i.birthDate=sessionStorage.getItem("birthdate");
      this.i.address=sessionStorage.getItem("address");
      this.i.firstName=sessionStorage.getItem("firstname");
      this.i.lastName=sessionStorage.getItem("lastname");
      this.profileFormGroup = this.fb.group({
        ctrlbirth: [this.i.birthDate],
        ctrlemail: [this.i.email],
        firstName:[this.i.firstName],
        lastName: [this.i.lastName],
        addressline1: [this.i.address],
        cin: [this.i.CIN],
        username:[this.i.userName],
        pass: [null],
        newpass1: [null],
        newwpass2: [null]
      });
    }
    else if(this.userType=="manager"){
      this.m.CIN=sessionStorage.getItem("cin");
      this.m.userName=sessionStorage.getItem("username");
      this.m.email=sessionStorage.getItem("email");
      this.m.birthDate=sessionStorage.getItem("birthdate");
      this.m.address=sessionStorage.getItem("address");
      this.m.firstName=sessionStorage.getItem("firstname");
      this.m.lastName=sessionStorage.getItem("lastname");
      this.profileFormGroup = this.fb.group({
        ctrlbirth: [this.m.birthDate],
        ctrlemail: [this.m.email],
        firstName:[this.m.firstName],
        lastName: [this.m.lastName],
        addressline1: [this.i.address],
        cin: [this.m.CIN],
        username:[this.m.userName],
        pass: [null],
        newpass1: [null],
        newpass2: [null]
      });
    }
    this.downloadImage(sessionStorage.getItem("image"));

    this.ccFormGroup = this.fb.group({
      ccardNum: [null],
      ccardName: [null],
      ccardExpmonth: [null],
      ccardExpyear: [null],
      ccardCVC: [null],
    });
    this.bankFormGroup = this.fb.group({
      bankType: ['Checking', Validators.required],
      bankRouting: [null],
      bankAccnum: [null, Validators.required],
      bankAccholder: [null],
      bankCfAccnum: [null, Validators.required],
    }, { validator: checkMatch('bankAccnum', 'bankCfAccnum') });
    this.bankAcc = {
      type: '',
      routing: '',
      number: '',
      holder: '',
    };
  }
  toggleDisable(field) {
    if (field === 'email') {
      this.disableEmail = !this.disableEmail;
    }
    if (field === 'phone') {
      this.disablePhone = !this.disablePhone;
    }
  }
  saveProfile(){
    this.profile.fullName = this.profileFormGroup.value.fullName;
    this.profile.email = this.profileFormGroup.value.ctrlemail;
    this.profile.phone = this.profileFormGroup.value.ctrlphone;
    this.profile.gender = this.profileFormGroup.value.gender;

    this.profile.address1 = this.profileFormGroup.value.addressline1;
    this.profile.address2 = this.profileFormGroup.value.addressline2;
    this.profile.state = this.profileFormGroup.value.addrstate;
    this.profile.city = this.profileFormGroup.value.addrcity;
    this.profile.zipCode = this.profileFormGroup.value.zipcode;
    this.profile.cCards = this.ccards;
    this.profile.bankAccount = this.bankAcc;

    this.profile.brandsite = this.profileFormGroup.value.brandsite;
    this.profile.company = this.profileFormGroup.value.company;
    this.profile.category = this.profileFormGroup.value.cats;

    this.profile.bio = this.profileFormGroup.value.bio;

    var formData: any = new FormData();

    if(this.profile.type == 'advertiser') {
      formData.append('company_name', this.profile.company);
      formData.append('about_company', this.profile.bio);
      formData.append('website', this.profile.brandsite);
      console.log('advertiser profile edit', formData, this.profile.profileId);
      this.dataService.updateAdvertiser(this.profile.profileId, formData)
      .pipe()
      .subscribe(cdata => {
        console.log('update influencer', cdata);
        this.router.navigate(['panel/user/setting']);
      });
    }
    else {
      var gender = this.profile.gender == 'female' ? 'F' : 'M';
      var name = this.profile.fullName.split(' ');
      
      formData.append('biography', this.profile.bio);
      formData.append('birthday', this.formatDate(this.profile.birthDay));
      formData.append('gender', gender);
      // formData.append('categories', this.profile.category);
      formData.append('first_name', name[0]);
      formData.append('last_name', name[1]);
      
      this.dataService.updateInfluencer(this.profile.profileId, formData)
      .pipe()
      .subscribe(cdata => {
        console.log('update influencer', cdata);
        this.router.navigate(['panel/user/setting']);
      });
    }
    

    // this.router.navigate(['panel/user/setting']);
  }
  formatDate(date) {
    var d = new Date(date),
    month = '' + (d.getMonth() + 1),
    day = '' + d.getDate(),
    year = d.getFullYear();

    // if (month.length < 2) 
    //     month = '0' + month;
    // if (day.length < 2) 
    //     day = '0' + day;

    return [year, month, day].join('-');
  }
  get BirthDay() {
    return new Date(Date.parse(this.profile.birthDay));
  }
  set BirthDay(val) {
    const dp: DatePipe = new DatePipe('en-US');
    this.profile.birthDay = dp.transform(new Date(val), 'MM.dd.yyyy');
  }
  openForm(selector, exist = 0){
    if (selector === 'creditcard') {
      this.curCardId = exist;
      this.ccFormGroup.reset();
      if (exist !== 0) {
        const id = this.ccards.findIndex((elem) => elem.id === exist);
        this.ccFormGroup.get('ccardNum').setValue(this.ccards[id].number);
        this.ccFormGroup.get('ccardName').setValue(this.ccards[id].name);
        this.ccFormGroup.get('ccardExpmonth').setValue(this.ccards[id].expMonth);
        this.ccFormGroup.get('ccardExpyear').setValue(this.ccards[id].expYear);
        this.ccFormGroup.get('ccardCVC').setValue(this.ccards[id].cvc);
      }
    }
    const wrapper = document.querySelectorAll('.payments .' + selector)[0];
    wrapper.classList.toggle('opened');
  }
  updateMethod(){
    const id = this.ccards.findIndex((elem) => elem.id === this.curCardId);
    this.ccards[id].number = this.ccFormGroup.value.ccardNum;
    this.ccards[id].name = this.ccFormGroup.value.ccardName;
    this.ccards[id].expMonth = this.ccFormGroup.value.ccardExpmonth;
    this.ccards[id].expYear = this.ccFormGroup.value.ccardExpyear;
    this.ccards[id].cvc = this.ccFormGroup.value.ccardCVC;

    const wrapper = document.querySelectorAll('.payments .creditcard')[0];
    wrapper.classList.toggle('opened');
  }
  deleteInfo(selector, exist = 0) {
    if (selector === 'creditcard') {
      const id = this.ccards.findIndex((elem) => elem.id === exist);
      this.ccards.splice(id, 1);
    }else{
      this.bankAcc = {
        type: '',
        routing: '',
        number: '',
        holder: '',
      };
    }
  }
  closeForm(selector) {
    const wrapper = document.querySelectorAll('.payments .' + selector)[0];
    wrapper.classList.remove('opened');
  }
  addMethod(type) {
    if (type === 'creditcard') {
      let cid = 1;
      if ( this.ccards ) {
        cid = this.ccards.length + 1;
      }
      this.ccards.push({
        id: cid,
        number: this.ccFormGroup.value.ccardNum,
        name: this.ccFormGroup.value.ccardName,
        expMonth: this.ccFormGroup.value.ccardExpmonth,
        expYear: this.ccFormGroup.value.ccardExpyear,
        cvc: this.ccFormGroup.value.ccardCVC,
      });

      this.paymentOpt = type;
      const wrapper = document.querySelectorAll('.payments .' + type)[0];
      wrapper.classList.remove('opened');
    }
  }
  saveBankInfo(){
    this.paymentOpt = 'bank';
    if (this.bankFormGroup.valid) {
      this.bankAcc.type = this.bankFormGroup.value.bankType;
      this.bankAcc.routing = this.bankFormGroup.value.bankRouting;
      this.bankAcc.number = this.bankFormGroup.value.bankAccnum;
      this.bankAcc.holder = this.bankFormGroup.value.bankAccholder;
      const wrapper = document.querySelectorAll('.payments .bank')[0];
      wrapper.classList.remove('opened');
    }
  }
  cancelForm() {
    const wrapper = document.querySelectorAll('.payments .opened')[0];
    wrapper.classList.remove('opened');
  }

  updateProfile(){
    if(sessionStorage.getItem("type")=="customer"){
      this.c.image=sessionStorage.getItem("image");
      this.service.UpdateCustomerProfile(this.c).subscribe(data=>{
        if(data != null){
          this.c=data;
          this.c.image=sessionStorage.getItem("image");
          alert("your profile is upadated")
          sessionStorage.setItem("sold",this.c.sold)
          sessionStorage.setItem("id", this.c.idC);
          sessionStorage.setItem("token", this.c.token);
          sessionStorage.setItem("username", this.c.userName);
          sessionStorage.setItem("email", this.c.email);
          sessionStorage.setItem("type", "customer");
          sessionStorage.setItem("firstname", this.c.firstName);
          sessionStorage.setItem("lastname", this.c.lastName);
          sessionStorage.setItem("image", this.c.image);
          sessionStorage.setItem("cin",this.c.cin);
          sessionStorage.setItem("gender",this.c.Sex);
          sessionStorage.setItem("job",this.c.job);
          sessionStorage.setItem("address",this.c.address);
          sessionStorage.setItem("salary",this.c.salary);

        }
      })
    }

    else if(sessionStorage.getItem("type")=="agent"){
      this.a.image=sessionStorage.getItem("image");
      this.service.UpdateAgentProfil(this.a).subscribe(data=>{
        if(data != null){
          alert("your profile is upadated")
        }
      })
    }

    else if(sessionStorage.getItem("type")=="investor"){
      this.i.image=sessionStorage.getItem("image");
      this.service.UpdateInvestorProfile(this.i).subscribe(data=>{
        if(data != null){
          alert("your profile is upadated")
        }
      })
    }

    if(sessionStorage.getItem("type")=="manager"){
      this.m.image=sessionStorage.getItem("image");
      this.service.UpdateManagerProfile(this.m).subscribe(data=>{
        if(data != null){
          alert("your profile is upadated")
        }
      })
    }

  }

  public onCustomerImageUpload(event) {
    this.uploadedImage = event.target.files[0];
    const imageFormData = new FormData();
    imageFormData.append('image', this.uploadedImage, this.uploadedImage.name);

    this.service.ImageUpload(imageFormData).subscribe(data=>{
      this.c.image=data
      this.downloadImage(this.c.image);
    })

  }

  public onAgentImageUpload(event) {
    this.uploadedImage = event.target.files[0];
    const imageFormData = new FormData();
    imageFormData.append('image', this.uploadedImage, this.uploadedImage.name);

    this.service.ImageUpload(imageFormData).subscribe(data=>{
      this.a.image=data
      this.downloadImage(this.a.image);
    })

  }

  public onInvestorImageUpload(event) {
    this.uploadedImage = event.target.files[0];
    const imageFormData = new FormData();
    imageFormData.append('image', this.uploadedImage, this.uploadedImage.name);

    this.service.ImageUpload(imageFormData).subscribe(data=>{
      this.i.image=data
      this.downloadImage(this.i.image);
    })

  }

  public onManagerImageUpload(event) {
    this.uploadedImage = event.target.files[0];
    const imageFormData = new FormData();
    imageFormData.append('image', this.uploadedImage, this.uploadedImage.name);

    this.service.ImageUpload(imageFormData).subscribe(data=>{
      this.m.image=data
      this.downloadImage(this.m.image);
    })

  }



  downloadImage(name){

    this.service.DownloadImage(name).subscribe(res=>{


        if(res == null){

             this.dbImage=res.image;
        }
        else{

        this.dbImage=res.image;

        }
    }),err=>{

    }

  }

  updatePassword(){
    if (this.userType=="customer"){
      this.service.upadateCustomerPass(this.newpass1,sessionStorage.getItem("username")).subscribe();
    }

    else if (this.userType=="agent"){
      this.service.upadateAgentPass(this.newpass1,sessionStorage.getItem("username")).subscribe();
    }
    else if (this.userType=="investor"){
      this.service.upadateInvestorPass(this.newpass1,sessionStorage.getItem("username")).subscribe();
    }
    else if (this.userType=="manager"){
      this.service.upadateManagerPass(this.newpass1,sessionStorage.getItem("username")).subscribe();
    }

  }

  checkPass(){

    if (this.userType=="customer"){
      this.service.checkCustomerPass(this.c.password,sessionStorage.getItem("username")).subscribe(data =>{
        if (data ==0){
          this.oldpassmatch=true;
        }
        else{
          this.oldpassmatch=false;
        }
      });

    }

    else if (this.userType=="agent"){
      this.service.checkAgentPass(this.a.password,sessionStorage.getItem("username")).subscribe(data =>{
        if (data ==0){
          this.oldpassmatch=true;
        }
        else{
          this.oldpassmatch=false;
        }
      });
    }
    else if (this.userType=="investor"){
      this.service.checkInvestorPass(this.i.password,sessionStorage.getItem("username")).subscribe(data =>{
        if (data ==0){
          this.oldpassmatch=true;
        }
        else{
          this.oldpassmatch=false;
        }
      });
    }
    else if (this.userType=="manager"){
      this.service.checkManagerPass(this.m.password,sessionStorage.getItem("username")).subscribe(data =>{
        if (data ==0){
          this.oldpassmatch=true;
        }
        else{
          this.oldpassmatch=false;
        }
      });
    }
  }

  checkTwoPass(){
    if(this.newpass1==this.newpass2){
      this.passMatch=true;
    }
    else{
      this.passMatch=false;
    }
  }
  setData(){
    window.addEventListener('storage', () => {
      // When local storage changes, dump the list to
      // the console.

    });
    this.adr=localStorage.getItem("address");

  if(this.userType=="customer"){
    if(this.c.address !=localStorage.getItem("address") ){
    this.c.address=localStorage.getItem("address");}

  }
  else if(this.userType=="agent"){
    if(this.a.address !=localStorage.getItem("address") ){
    console.log(localStorage.getItem("address"));
    this.a.address=localStorage.getItem("address")}
  }
  else if(this.userType=="investor"){
    if(this.i.address !=localStorage.getItem("address") ){
    this.i.address=localStorage.getItem("address");}
  }
  else{
    if(this.m.address !=localStorage.getItem("address") ) {
      this.m.address = localStorage.getItem("address");
    }
  }
    this.fb.group({
      addressline1:[localStorage.getItem("address")]
    });

  }

  openMapwindow(){
    window.open('/map');

    setInterval(() => {
    this.setData();
    }, 5000);
  }







}
