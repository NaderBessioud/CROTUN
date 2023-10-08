import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import {DashboardHomeComponent} from './dashboard-home.component';
import {NgxUsefulSwiperModule} from 'ngx-useful-swiper';
import {FlexLayoutModule} from '@angular/flex-layout';
import {RouterModule} from '@angular/router';
import {ContainerModule} from '../../../../@vex/directives/container/container.module';
import {VexRoutes} from '../../../../@vex/interfaces/vex-route.interface';
import {AppModule} from "../../../app.module";
import {GroupedBarChartComponent} from "../../grouped-bar-chart/grouped-bar-chart.component";
import {NgxChartsModule} from "@swimlane/ngx-charts";
import {BarchartComponent} from "../../barchart/barchart.component";
import {HighchartsChartModule} from  'highcharts-angular';

const routes: VexRoutes = [
  {
    path: '',
    component: DashboardHomeComponent,
    data: {
      scrollDisabled: false,
      toolbarShadowEnabled: false
    }
  }
];


@NgModule({
  declarations: [DashboardHomeComponent,GroupedBarChartComponent,BarchartComponent],
    imports: [
        CommonModule,
        NgxUsefulSwiperModule,
        FlexLayoutModule,
        RouterModule.forChild(routes),
        ContainerModule,
        NgxChartsModule,
        HighchartsChartModule

    ],
  exports: [RouterModule]
})
export class DashboardHomeModule { }
