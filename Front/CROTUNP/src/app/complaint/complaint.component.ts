import { Component, OnInit } from '@angular/core';
import { ModalDismissReasons, NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { Complaint} from '../Modals/complaint';
import { ComplaintService } from '../Services/complaint.service';

@Component({
    selector: 'app-products',
    templateUrl: './complaint.component.html',
    styleUrls: ['./complaint.component.css']
})
export class ComplaintComponent implements OnInit {
    listComplaints: any;
    listComplaint: any;
    countComplaint: any;
    decisionComplaints:any;
    themeComplaints:any;
    complaint!: Complaint;
    closeResult!: string;
    form: boolean = false;


    constructor(private complaintService: ComplaintService, private modalService: NgbModal) { }

    ngOnInit(): void {
        //this.getComplaint(1);
        this.getAllComplaints();
        // this.decisionComplaint(1);
        //this.themeComplaint(1);
        this.statComplaint();

        this.complaint = {
            idL: null,
            Text: null,
            Response: null,
            status: null,
            num: null,
            Date: null,
            ComplaintSubject: null,
            customerComplaint: null,
            responderManager: null,
            agentComplaint: null
        };
    }

    /*getComplaint(complaintid:any){
      this.complaintService.getComplaint(complaintid).subscribe(res => this.listComplaint = res);
    }*/
    getAllComplaints(){
        this.complaintService.getAllComplaints().subscribe(res => {console.log(res)
            debugger
                ;this.listComplaints = res});

    }
    addComplaint(c:Complaint,idmanager:any,idcostumer:any,idagent:any,num:any,complaintSubject:any,text:any){
        this.complaintService.addComplaint(c, idmanager,sessionStorage.getItem("id"),idagent,num, complaintSubject,text).subscribe(() => {
            this.getAllComplaints();
        });
    }
    deleteComplaint(complaintid:any) {
        this.complaintService.deleteComplaint(complaintid).subscribe( () => {
            this.getAllComplaints();
        });
    }
    updateComplaint(complaint:Complaint, idmanager:any, idcustomer:any, idagent:any){
        this.complaintService.updateComplaint(complaint, idmanager, sessionStorage.getItem("id"), idagent).subscribe(() => {
            this.getAllComplaints();
        });
    }
    traiterComplaint(idreq:any,Rep:any){
        this.complaintService.traiterComplaint(idreq,Rep).subscribe(() => {
            this.getAllComplaints();
        });
    }
    /*decisionComplaint(idmanager:any){
      this.complaintService.decisionComplaint(idmanager).subscribe(res => this.decisionComplaints = res)
    }*/
    themeComplaint(id:any){
        this.complaintService.themeComplaint(id).subscribe(res => this.themeComplaints = res)
    }
    statComplaint(){
        this.complaintService.statComplaint().subscribe(res => this.countComplaint = res)
    }
    open(content: any) {
        this.modalService.open(content, {ariaLabelledBy: 'modal-basic-title'}).result.then((result) => {
            this.closeResult = `Closed with: ${result}`;
        }, (reason) => {
            this.closeResult = `Dismissed ${this.getDismissReason(reason)}`;
        });
    }
    private getDismissReason(reason: any): string {
        if (reason === ModalDismissReasons.ESC) {
            return 'by pressing ESC';
        } else if (reason === ModalDismissReasons.BACKDROP_CLICK) {
            return 'by clicking on a backdrop';
        } else {
            return  `with: ${reason}`;
        }
    }
    closeForm(){

    }
    cancel(){
        this.form = false;
    }

    /*
    listProducts : any;
    form : boolean = false;
     product!: Product;
     closeResult! : string;

    constructor(private productService : ComplaintService, private modalService: NgbModal) { }

    ngOnInit(): void {
      this.getAllProducts();;

      this.product = {
        id_product: null,
        title: null,
        price: null,
        quantity: null
      }
    }

    getAllProducts(){
      this.productService.getAllProducts().subscribe(res => this.listProducts = res)
    }

    addProduct(p: any){
      this.productService.addProduct(p).subscribe(() => {
        this.getAllProducts();
        this.form = false;
      });
    }

    editProduct(product : Product){
      this.productService.editProduct(product).subscribe();
    }
    deleteProduct(idProduct : any){
      this.productService.deleteProduct(idProduct).subscribe(() => this.getAllProducts())
    }
    open(content: any) {
    this.modalService.open(content, {ariaLabelledBy: 'modal-basic-title'}).result.then((result) => {
      this.closeResult = `Closed with: ${result}`;
    }, (reason) => {
      this.closeResult = `Dismissed ${this.getDismissReason(reason)}`;
    });
    }
    private getDismissReason(reason: any): string {
      if (reason === ModalDismissReasons.ESC) {
        return 'by pressing ESC';
      } else if (reason === ModalDismissReasons.BACKDROP_CLICK) {
        return 'by clicking on a backdrop';
      } else {
        return  `with: ${reason}`;
      }
    }
    closeForm(){

    }
    cancel(){
      this.form = false;
    }*/
}
