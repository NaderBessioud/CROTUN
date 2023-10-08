
import { VexModule } from '../@vex/vex.module';

import { CustomLayoutModule } from './custom-layout/custom-layout.module';
import { PortalModule } from '@angular/cdk/portal';
import { ToastrModule } from 'ngx-toastr';
import { DialogResetPasswordComponent } from './pages/dialog-reset-password/dialog-reset-password.component';



import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';

import {MatDialogModule} from '@angular/material/dialog';
import { FormsModule } from '@angular/forms';
import { ReactiveFormsModule } from '@angular/forms';
import {MatIconModule} from '@angular/material/icon';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';

import {MatGridList, MatGridListModule, MatGridTile} from '@angular/material/grid-list';
import {MatFormField, MatFormFieldModule} from '@angular/material/form-field';
import {MatInputModule} from '@angular/material/input';
import {MatButtonModule} from '@angular/material/button';
import { NewPasswordComponent } from './pages/new-password/new-password.component';
import { ActivityLogComponent } from './pages/activity-log/activity-log.component';
import {MatTableModule} from "@angular/material/table";
import {MatPaginatorModule} from "@angular/material/paginator";
import { TokenInterceptorService } from './Services/token-interceptor.service';
import { LoanComponent } from './pages/loan/loan.component';
import { LoanInfoComponent } from './pages/loan-info/loan-info.component';
import {MatTabsModule} from "@angular/material/tabs";
import { SliceComponent } from './pages/slice/slice.component';
import { ScorechartComponent } from './pages/scorechart/scorechart.component';
import {NgxPaginationModule} from "ngx-pagination";
import {Ng2OrderModule} from "ng2-order-pipe";
import { LoandetailsComponent } from './pages/loandetails/loandetails.component';
import {MatSliderModule} from "@angular/material/slider";
import {RegistrationService} from "./Services/registration.service";
import { InvestorsignupComponent } from './pages/investorsignup/investorsignup.component';
import {FlexLayoutModule} from "@angular/flex-layout";
import {MatSnackBarModule} from "@angular/material/snack-bar";
import {IconModule} from "@visurel/iconify-angular";
import {MatTooltipModule} from "@angular/material/tooltip";
import {MatCheckboxModule} from "@angular/material/checkbox";
import {SlimLoadingBarModule} from "ng2-slim-loading-bar";
import {MatProgressBarModule} from "@angular/material/progress-bar";
import { GroupedBarChartComponent } from './pages/grouped-bar-chart/grouped-bar-chart.component';
import { NgxChartsModule } from '@swimlane/ngx-charts';
import { BarchartComponent } from './pages/barchart/barchart.component';
import { MapComponent } from './pages/map/map.component';
import { MessageChatClientComponent } from './pages/message-chat-client/message-chat-client.component';
import { ChatmessageComponent } from './pages/chatmessage/chatmessage.component';
import { MeetComponent } from './pages/meet/meet.component';
import { NotificationComponent } from './pages/notification/notification.component';
import { InvestmentofferComponent } from './investmentoffer/investmentoffer.component';
import { NgbModal, NgbModalModule, NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { InvestmentComponent } from './investment/investment.component';
import {DataTablesModule} from "angular-datatables";
import { AvailablityComponent } from './availablity/availablity.component';



import { PaymentComponent } from './pages/payment/payment.component';
import { CreditCardComponent } from './pages/credit-card/credit-card.component';
import { SoldComponent } from './pages/sold/sold.component';
import { MethodPaymentComponent } from './pages/method-payment/method-payment.component';



import { Ng2SearchPipeModule } from 'ng2-search-filter';
import { PaymentEnLigneComponent } from './pages/payment-en-ligne/payment-en-ligne.component';


import { FullCalendarModule } from '@fullcalendar/angular'; // must go before plugins
import dayGridPlugin from '@fullcalendar/daygrid';
import { FontAwesomeModule , FaIconLibrary} from '@fortawesome/angular-fontawesome';
import {ComplaintComponent} from "./complaint/complaint.component";
import { AgentBackComponent } from './agent-back/agent-back.component'; // a plugin!

FullCalendarModule.registerPlugins([ // register FullCalendar plugins
  dayGridPlugin,

]);

@NgModule({
  declarations: [AppComponent, DialogResetPasswordComponent,PaymentEnLigneComponent, ComplaintComponent, NewPasswordComponent, ActivityLogComponent, LoanComponent, LoanInfoComponent, SliceComponent, ScorechartComponent, LoandetailsComponent, InvestorsignupComponent, MapComponent,MessageChatClientComponent, ChatmessageComponent, MeetComponent, NotificationComponent,InvestmentofferComponent, InvestmentComponent, AvailablityComponent,PaymentComponent, CreditCardComponent, SoldComponent, MethodPaymentComponent],

    imports: [
        BrowserModule,
        AppRoutingModule,
        BrowserAnimationsModule,
        HttpClientModule,

        // Vex
        VexModule,
        CustomLayoutModule,
        PortalModule,
        BrowserAnimationsModule,
        MatDialogModule,
        FormsModule,
        ReactiveFormsModule,
        MatIconModule,
        HttpClientModule,
        MatGridListModule,
        MatFormFieldModule,
        MatFormFieldModule,
        Ng2SearchPipeModule,
        MatInputModule,
        MatButtonModule,
        ToastrModule.forRoot(),
        MatTableModule,
        MatPaginatorModule,
      FlexLayoutModule,
        MatTabsModule,
        NgxPaginationModule,
        Ng2OrderModule,
        MatSliderModule,
      MatSnackBarModule,
      IconModule,
      MatTooltipModule,
      MatCheckboxModule,
      SlimLoadingBarModule,
      MatProgressBarModule,
       NgxChartsModule,
       NgbModule,
       FullCalendarModule,
       FontAwesomeModule
    ],


  providers: [{
    provide: HTTP_INTERCEPTORS,
    useClass: TokenInterceptorService,
    multi: true,
  },],

  bootstrap: [AppComponent],
  exports: [

  ]
})
export class AppModule { }
