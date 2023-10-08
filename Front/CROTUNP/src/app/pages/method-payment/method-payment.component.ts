import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { MethodePaymentService } from 'src/app/Services/methode-payment.service';
import { HttpEventType, HttpResponse } from '@angular/common/http';

@Component({
  selector: 'vex-method-payment',
  templateUrl: './method-payment.component.html',
  styleUrls: ['./method-payment.component.scss']
})
export class MethodPaymentComponent implements OnInit {
  constructor(private methodePaymentService:MethodePaymentService,private activatedroute : ActivatedRoute) { }
  id : string ;
  selectedFiles?: FileList;
  currentFile?: File;
  progress = 0;
  message = '';
  payment_avec_sold(){
    this.methodePaymentService.payment_avec_sold(this.id,sessionStorage.getItem("id")).subscribe((res)=>{
      
      alert(res.reponse);

    })
  }
  selectFile(event: any): void {
    this.selectedFiles = event.target.files;
  }
  upload(): void {
    this.progress = 0;
    if (this.selectedFiles) {
      const file: File | null = this.selectedFiles.item(0);
      if (file) {
        this.currentFile = file;
        this.methodePaymentService.upload(this.currentFile, this.id,sessionStorage.getItem("id")).subscribe(
          (event: any) => {
            console.log("*******")
            console.log(event)
            if (event.type === HttpEventType.UploadProgress) {
              this.progress = Math.round(100 * event.loaded / event.total);
            } else if (event instanceof HttpResponse) {
              this.message = event.body.message;

              console.log(event)
              alert(event.body.reponse)
            }
          },
          (err: any) => {
            console.log(err);
            this.progress = 0;
            if (err.error && err.error.message) {
              this.message = err.error.message;
            } else {
              this.message = 'Could not upload the file!';
            }
            this.currentFile = undefined;
          });
      }
      this.selectedFiles = undefined;
    }
  }
  ngOnInit(): void {
    this.id = this.activatedroute.snapshot.paramMap.get('id');
}
}
