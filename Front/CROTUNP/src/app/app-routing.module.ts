import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { CustomLayoutComponent } from './custom-layout/custom-layout.component';
import {NewPasswordComponent} from "./pages/new-password/new-password.component";
import {ActivityLogComponent} from "./pages/activity-log/activity-log.component";

import {MessageChatClientComponent} from "./pages/message-chat-client/message-chat-client.component";
import {ChatmessageComponent} from "./pages/chatmessage/chatmessage.component";
import {MeetComponent} from "./pages/meet/meet.component";
import {NotificationComponent} from "./pages/notification/notification.component";

import {LoanComponent} from "./pages/loan/loan.component";

import {Investor} from "./Modals/investor";
import {InvestorsignupComponent} from "./pages/investorsignup/investorsignup.component";
import {MapComponent} from "./pages/map/map.component";


import {InvestmentofferComponent} from "./investmentoffer/investmentoffer.component";
import { InvestmentComponent } from './investment/investment.component';
import {AvailablityComponent} from "./availablity/availablity.component";
import {ComplaintComponent} from "./complaint/complaint.component";
import {CreditCardComponent} from "./pages/credit-card/credit-card.component";
import { SoldComponent } from './pages/sold/sold.component';
import { PaymentComponent } from './pages/payment/payment.component';
import { MethodPaymentComponent } from './pages/method-payment/method-payment.component';
import { PaymentEnLigneComponent } from './pages/payment-en-ligne/payment-en-ligne.component';


const routes: Routes = [
  {path:"map",component:MapComponent, data: {
      pageTitle: 'Location',
    }},

  {
    path: '',
    loadChildren: () => import('./pages/login/login.module').then(m => m.LoginModule),
  },
  {
    path: 'signin/:id',

    loadChildren: () => import('./pages/signin/signin.module').then(m => m.SigninModule),
    data: {
      pageTitle: 'Signin',
    }
  },

  {
    path: 'resetPass',

    component:NewPasswordComponent,
    data: {
      pageTitle: 'ResetPassword',
    }
  },

  {
    path: 'DeviceAct',

    component:ActivityLogComponent,
    data: {
      pageTitle: 'DeviceActivities',
    }
  },

  {
    path: 'investorsignup',

    component:InvestorsignupComponent,
    data: {
      pageTitle: 'InvestorSignUp',
    }
  },


  {
    path: 'brandsignup',
    loadChildren: () => import('./pages/brandsignup/brandsignup.module').then(m => m.BrandSignUpModule),
    data: {
      pageTitle: 'BrandSignUp',
    }
  },
  {
    path: 'influencersignup',
    loadChildren: () => import('./pages/influencersignup/influencersignup.module').then(m => m.InfluencerSignUpModule),
    data: {
      pageTitle: 'InfluencerSignUp',
    }
  },

  {
    path: 'panel',
    component: CustomLayoutComponent,
    children: [
      {
        path: 'availablity', component:AvailablityComponent,
        data: {
          pageTitle: 'availablity',
        }

      },
      {
          path: 'dashboard',
        loadChildren: () => import('./pages/dashboard/dashboard.module').then(m => m.DashboardModule),
        data: {
          pageTitle: 'Dashboard',
        }
      },
      {
        path: 'complaint',

        component:ComplaintComponent,
        data: {
          pageTitle: 'complaint',
        }
      },


      {
        path: 'chatclient',

        component:MessageChatClientComponent,
        data: {
          pageTitle: 'chat',
        }
      },
      {
        path: 'meet',

        component:MeetComponent,
        data: {
          pageTitle: 'meet',
        }
      },
      {
        path: 'notif',

        component:NotificationComponent,
        data: {
          pageTitle: 'notif',
        }
      },
      {
        path: 'chatagent',

        component:ChatmessageComponent,
        data: {
          pageTitle: 'chat',
        }
      },
      {
        path: 'campaign',
        loadChildren: () => import('./pages/campaign/campaign.module').then(m => m.CampaignModule),
        data: {
          pageTitle: 'Campaign',
        }
      },
      {
        path: 'loan',


        component:LoanComponent,
        data: {
          pageTitle: 'Loan',
        }
      },

      {
        path: 'investmentoffer',

        component:InvestmentofferComponent,
        data: {
          pageTitle: 'Investment_Offer',
        }
      },
      {
        path: 'investment',

        component:InvestmentComponent,
        data: {
          pageTitle: 'Investment ',
        }
      },


      {
        path: 'card',
        component : CreditCardComponent,
        data: {
          pageTitle: 'card',
        }
      },
      {
        path: 'solde',
        component : SoldComponent,
        data: {
          pageTitle: 'solde',
        }
      },
      {
        path: 'enligne/:id',
        component : PaymentEnLigneComponent,
        data: {
          pageTitle: 'enligne',
        }
      },
      {
        path: 'payment',
        component : PaymentComponent,
        data: {
          pageTitle: 'payment',
        }
      },
      
      {
        path: 'methodepayment/:id',
        component : MethodPaymentComponent,
        data: {
          pageTitle: 'methodepayment',
        }
      },
      {
        path: 'inf_discover',
        loadChildren: () => import('./pages/campaign/influencer-grid/influencer-grid.module').then(m => m.InfluencerGridModule),
        data: {
          pageTitle: 'Discover',
        }
      },
      {
        path: 'discover',
        loadChildren: () => import('./pages/discover/discover.module').then(m => m.DiscoverModule),
        data: {
          pageTitle: 'Discover',
        }
      },
      {
        path: 'chat',
        loadChildren: () => import('./pages/chat/chat.module').then(m => m.ChatModule),
        data: {
          toolbarShadowEnabled: true,
          pageTitle: 'Chat',
        }
      },
      {
        path: 'proposal',
        loadChildren: () => import('./pages/proposal/proposal.module').then(m => m.ProposalModule),
        data: {
          toolbarShadowEnabled: true,
          pageTitle: 'Chat',
        }
      },
      {
        path: 'offer',
        loadChildren: () => import('./pages/offer/offer.module').then(m => m.OfferModule),
        data: {
          toolbarShadowEnabled: true,
          pageTitle: 'Offer',
        }
      },
      {
        path: 'user',
        loadChildren: () => import('./pages/user/user.module').then(m => m.UserModule),
        data: {
          toolbarShadowEnabled: true,
          pageTitle: 'User',
        }
      },
      {
        path: 'system',
        loadChildren: () => import('./pages/system/system.module').then(m => m.SystemModule),
        data: {
          toolbarShadowEnabled: true,
          pageTitle: 'User',
        }
      }
    ]
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes, {
    // preloadingStrategy: PreloadAllModules,
    scrollPositionRestoration: 'enabled',
    relativeLinkResolution: 'corrected',
    anchorScrolling: 'enabled'
  })],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
