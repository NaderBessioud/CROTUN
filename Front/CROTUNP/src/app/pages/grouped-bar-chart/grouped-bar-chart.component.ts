import { Component, OnInit } from '@angular/core';

import {RegistrationService} from "../../Services/registration.service";

@Component({
  selector: 'vex-grouped-bar-chart',
  templateUrl: './grouped-bar-chart.component.html',
  styleUrls: ['./grouped-bar-chart.component.scss']
})
export class GroupedBarChartComponent implements OnInit {
  title="Card nomber"
  multi: any[];
  view: any[] = [700, 400];
  showXAxis: boolean = true;
  showYAxis: boolean = true;
  gradient: boolean = true;
  showLegend: boolean = true;
  showXAxisLabel: boolean = true;
  xAxisLabel: string = 'Agent';
  showYAxisLabel: boolean = true;
  yAxisLabel: string = 'Nomber';
  legendTitle: string = 'Amount';
  colorScheme = {
    domain: ['#5AA454', '#C7B42C', '#AAAAAA']
  };
  constructor(private service :RegistrationService) {

  }

  ngOnInit(): void {
  this.service.getAgentCard().subscribe(data=>{
    console.log("hay data");
    console.log(data);
    this.multi=data;
  })

    Object.assign(this, this.multi)
  }

  onSelect(data): void {
    console.log('Item clicked', JSON.parse(JSON.stringify(data)));
  }

  onActivate(data): void {
    console.log('Activate', JSON.parse(JSON.stringify(data)));
  }

  onDeactivate(data): void {
    console.log('Deactivate', JSON.parse(JSON.stringify(data)));
  }

}
