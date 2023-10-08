import {Component, Inject, OnInit, Renderer2} from '@angular/core';
import {scaleIn400ms} from '../../../../@vex/animations/scale-in.animation';
import {fadeInRight400ms} from '../../../../@vex/animations/fade-in-right.animation';
import {stagger40ms} from '../../../../@vex/animations/stagger.animation';
import {fadeInUp400ms} from '../../../../@vex/animations/fade-in-up.animation';
import {scaleFadeIn400ms} from '../../../../@vex/animations/scale-fade-in.animation';
import {ActivatedRoute, Router} from '@angular/router';
import {UserService} from '../../../providers/user.service';
import {DOCUMENT} from '@angular/common';
import { ImageCroppedEvent } from 'ngx-image-cropper';
import {HttpResponse} from "@angular/common/http";
import {RegistrationService} from "../../../Services/registration.service";

@Component({
  selector: 'vex-user-setting',
  templateUrl: './user-setting.component.html',
  styleUrls: ['./user-setting.component.scss'],
  animations: [
    scaleIn400ms,
    fadeInRight400ms,
    stagger40ms,
    fadeInUp400ms,
    scaleFadeIn400ms
  ]
})
export class UserSettingComponent implements OnInit {
  imageChangedEvent: any = '';
  croppedImage: any = '';
  fullName:String;
  uploadedImage: File;
  dbImage: any;
  postResponse: any;
  successResponse: string;
  image: any;
  Response:HttpResponse<any>;

  constructor(private route: ActivatedRoute,
              private renderer: Renderer2,
              @Inject(DOCUMENT) private document: Document,
              private router: Router,
              public userService: UserService,
              private service:RegistrationService) {
    this.renderer.addClass(this.document.body, 'user-page');
  }

  ngOnInit(): void {
    if(sessionStorage.getItem("firstname") != "null"){
    this.fullName=sessionStorage.getItem("firstname")+" "+sessionStorage.getItem("lastname");
    }
    else{
      this.fullName="";
    }
    this.downloadImage(sessionStorage.getItem("image"));
  }
  onFileChange(event) {
    const reader = new FileReader();
    const cf = document.querySelectorAll('.img-cropping-wrapper')[0];

    if (event.target.files && event.target.files.length) {
      const [file] = event.target.files;
      reader.readAsDataURL(file);

      reader.onload = () => {
        // this.userService.currentUser.avatar = reader.result as string;
      };

      this.imageChangedEvent = event;
      cf.classList.add('opened');
    }
  }
  imageCropped(event: ImageCroppedEvent) {
    this.croppedImage = event.base64;
  }
  saveCroppedImage() {
    this.userService.currentUser.avatar = this.croppedImage;
    const cf = document.querySelectorAll('.img-cropping-wrapper')[0];
    cf.classList.remove('opened');
  }
  imageLoaded() {
    // show cropper
  }
  cropperReady() {
    // cropper ready
  }
  loadImageFailed() {
    // show message
  }

  onimageChanged(event){
    let reader = new FileReader();
    // when the load event is fired and the file not empty
    if(event.target.files && event.target.files.length > 0) {
      // Fill file variable with the file content
      this.uploadedImage = event.target.files[0];
    }

    console.log(this.uploadedImage);
    const imageFormData = new FormData();
    imageFormData.append('imageFile', this.uploadedImage, this.uploadedImage.name);
    console.log( this.uploadedImage.name);
    this.service.ImageUpload(imageFormData).subscribe(data=>{
      console.log(data);
      sessionStorage.setItem("image",data.image);
      this.downloadImage(data.image);
    })
      
  }

  public onAgentImageUpload() {

    console.log("houni");

    const imageFormData = new FormData();
    imageFormData.append('imageFile', this.uploadedImage, this.uploadedImage.name);

    this.service.ImageUpload(imageFormData).subscribe(data=>{
      sessionStorage.setItem("image",data.image);
      this.downloadImage(data.image);
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
      console.log("hay dataaaaaaaaaaaaa ");
    }




  }

  saveImage(){
    console.log(sessionStorage.getItem("image"));
  }


}
