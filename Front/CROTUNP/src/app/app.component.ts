import {Component, HostListener, Inject, LOCALE_ID, OnInit, Renderer2} from '@angular/core';
import { ConfigService } from '../@vex/services/config.service';
import { Settings } from 'luxon';
import { DOCUMENT } from '@angular/common';
import { Platform } from '@angular/cdk/platform';
import { NavigationService } from '../@vex/services/navigation.service';

import { LayoutService } from '../@vex/services/layout.service';
import {ActivatedRoute, ActivatedRouteSnapshot, NavigationEnd, Router} from '@angular/router';
import {filter, map, startWith, mergeMap } from 'rxjs/operators';
import { coerceBooleanProperty } from '@angular/cdk/coercion';
import { SplashScreenService } from '../@vex/services/splash-screen.service';
import { Style, StyleService } from '../@vex/services/style.service';
import { ConfigName } from '../@vex/interfaces/config-name.model';
import { Title } from '@angular/platform-browser';

import {checkRouterChildsData} from '../@vex/utils/check-router-childs-data';
import {VexRouteData} from '../@vex/interfaces/vex-route.interface';
import {UserService} from './providers/user.service';


@Component({
  selector: 'vex-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent implements OnInit {
  title = 'vex';

  constructor(private configService: ConfigService,
              private styleService: StyleService,
              private renderer: Renderer2,
              private platform: Platform,
              private router: Router,
              @Inject(DOCUMENT) private document: Document,
              @Inject(LOCALE_ID) private localeId: string,
              private layoutService: LayoutService,
              private route: ActivatedRoute,
              private titleService: Title,
              private userService: UserService,
              private navigationService: NavigationService,
              private splashScreenService: SplashScreenService) {
    Settings.defaultLocale = this.localeId;

    if (this.platform.BLINK) {
      this.renderer.addClass(this.document.body, 'is-blink');
    }
    /**
     * Customize the template to your needs with the ConfigService
     * Example:
     *  this.configService.updateConfig({
     *    sidenav: {
     *      title: 'Custom App',
     *      imageUrl: '//placehold.it/100x100',
     *      showCollapsePin: false
     *    },
     *    showConfigButton: false,
     *    footer: {
     *      visible: false
     *    }
     *  });
     */

    /**
     * Config Related Subscriptions
     * You can remove this if you don't need the functionality of being able to enable specific configs with queryParams
     * Example: example.com/?layout=apollo&style=default
     */
    this.route.queryParamMap.pipe(
        map(queryParamMap => queryParamMap.has('rtl') && coerceBooleanProperty(queryParamMap.get('rtl'))),
    ).subscribe(isRtl => {
      this.document.body.dir = isRtl ? 'rtl' : 'ltr';
      this.configService.updateConfig({
        rtl: isRtl
      });
    });

    this.route.queryParamMap.pipe(
        filter(queryParamMap => queryParamMap.has('layout'))
    ).subscribe(queryParamMap => this.configService.setConfig(queryParamMap.get('layout') as ConfigName));

    this.route.queryParamMap.pipe(
        filter(queryParamMap => queryParamMap.has('style'))
    ).subscribe(queryParamMap => this.styleService.setStyle(queryParamMap.get('style') as Style));


      


    if (sessionStorage.getItem("type") == "customer") {
      this.navigationService.items = [
        {
          type: 'link',
          label: 'Home',
          route: 'dashboard/home',
          iconClass: 'icon-inf-home',
          permission: ['advertiser', 'influencer'],
        },
        {
          type: 'link',
          label: 'Loan',
          route: 'loan',
          iconClass: 'icon-inf-campaign',
          permission: ['influencer'],
        },
        {
          type: 'link',
          label: 'avaibility',
          route: 'availablity',
          iconClass: 'icon-inf-campaign',
          permission: ['advertiser','influencer'],
        },
        {
          type: 'link',
          label: 'Complaint',
          route: 'complaint',
          iconClass: 'icon-inf-group',
          permission: ['advertiser','influencer'],
        },
        {
          type: 'link',
          label: 'chat',
          route: 'chatclient',
          iconClass: 'ic-inf-offer',
          permission: ['advertiser','influencer'],
        },
        {
          type: 'link',
          label: 'payment',
          route: 'payment',
          iconClass: 'icon-inf-chat',
          permission: ['advertiser', 'influencer'],
        },

        // {
        //   type: 'link',
        //   label: 'Calendar',
        //   route: 'calendar',
        //   iconClass: 'icon-inf-calendar',
        //   permission: ['advertiser'],
        // },
        // {
        //   type: 'link',
        //   label: 'History',
        //   route: 'history',
        //   iconClass: 'icon-inf-history',
        //   permission: ['advertiser'],
        // },
      ];
    }else if (sessionStorage.getItem('type') == 'investor') {

       this.navigationService.items = [
         {
           type: 'link',
           label: 'Home',
           route: 'dashboard/home',
           iconClass: 'icon-inf-home',
           permission: ['advertiser', 'influencer'],
         },
         {
           type: 'link',
           label: 'Offer',
           route: 'investmentoffer',
           iconClass: 'icon-inf-campaign',
           permission: ['advertiser','influencer'],
         },
         {
           type: 'link',
           label: 'Investment',
           route: 'investment',
           iconClass: 'icon-inf-campaign',
           permission: ['advertiser','influencer'],
         }
       ];

    } else if (sessionStorage.getItem('type') == 'agent') {

      this.navigationService.items = [
        {
          type: 'link',
          label: 'Home',
          route: 'dashboard/home',
          iconClass: 'icon-inf-home',
          permission: ['advertiser', 'influencer'],
        },
         {
          type: 'link',
          label: 'Loan',
          route: 'loan',
          iconClass: 'icon-inf-campaign',
          permission: ['advertiser','influencer'],
        },
        {
          type: 'link',
          label: 'avaibility',
          route: 'availablity',
          iconClass: 'icon-inf-campaign',
          permission: ['advertiser','influencer'],
        },
        {
          type: 'link',
          label: 'complaint',
          route: 'complaint',
          iconClass: 'icon-inf-group',
          permission: ['advertiser','influencer'],
        },
        {
          type: 'link',
          label: 'chat',
          route: 'chatagent',
          iconClass: 'ic-inf-offer',
          permission: ['advertiser', 'influencer'],
        },
        {
          type: 'link',
          label: 'card',
          route: 'card',
          iconClass: 'icon-inf-chat',
          permission: ['advertiser', 'influencer'],
        },

        // {
        //   type: 'link',
        //   label: 'Calendar',
        //   route: 'calendar',
        //   iconClass: 'icon-inf-calendar',
        //   permission: ['advertiser'],
        // },
        // {
        //   type: 'link',
        //   label: 'History',
        //   route: 'history',
        //   iconClass: 'icon-inf-history',
        //   permission: ['advertiser'],
        // },
      ];
    }
    else if (sessionStorage.getItem('type') == 'manager'){



        this.navigationService.items = [
          {
            type: 'link',
            label: 'dashbord',
            route: 'dashboard/home',
            iconClass: 'icon-inf-home',
            permission: ['advertiser', 'influencer'],
          },
          {
            type: 'link',
            label: 'complaint',
            route: 'campaign/list',
            iconClass: 'icon-inf-campaign',
            permission: ['advertiser','influencer'],
          },
          {
            type: 'link',
            label: 'loan',
            route: 'inf_discover/list',
            iconClass: 'icon-inf-campaign',
            permission: ['advertiser','influencer'],
          },
          {
            type: 'link',
            label: 'offer',
            route: 'inf_discover/list',
            iconClass: 'icon-inf-campaign',
            permission: ['advertiser','influencer'],
          }
        ];

      }
    }


    ngOnInit()
    {
      this.router.events.pipe(
          filter(event => event instanceof NavigationEnd),
          map(() => this.route),
          map(route => {
            while (route.firstChild) {
              route = route.firstChild;
            }
            return route;
          }),
          filter(route => route.outlet === 'primary'),
          mergeMap(route => route.data)
      ).subscribe(data => {
            if (data.pageTitle) {
              const body = document.querySelectorAll('body')[0];
              body.classList.forEach(c => {
                if (c.indexOf('page--') !== -1) {
                  body.classList.remove(c);
                }
              });
              body.classList.add('page--' + data.pageTitle.toLowerCase());
              this.titleService.setTitle(data.pageTitle + ' | CROTUN');
            } else {
              this.titleService.setTitle('CROTUN');
            }
          }
      );
    }
  }

