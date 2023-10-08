import { Component, OnInit } from '@angular/core';
import * as Highcharts from 'highcharts'
import {CustomerPenality} from "../../Modals/customer-penality";
import {Customerpenlitydate} from "../../Modals/customerpenlitydate";
import {RegistrationService} from "../../Services/registration.service";

@Component({
  selector: 'vex-barchart',
  templateUrl: './barchart.component.html',
  styleUrls: ['./barchart.component.scss']
})
export class BarchartComponent implements OnInit {
  CustomerPen: CustomerPenality[];
  customerPen1: Customerpenlitydate[];
  customerPen2: Customerpenlitydate[];
  customerPen3: Customerpenlitydate[];
  dataa1:any[]=[]
  dataa2:any[]=[]
  dataa3:any[]=[]
  Highcharts = Highcharts;
  chart;
  updateFlag = false;

  chartConstructor = "chart";

  chartOption = {
    chart: {
      type: 'column'
    },
    title: {
      align: 'left',
      text: 'Top customer penaity'
    },

    accessibility: {
      announceNewData: {
        enabled: true
      }
    },
    xAxis: {
      type: 'category'
    },
    yAxis: {
      title: {
        text: 'penality'
      }

    },
    legend: {
      enabled: false
    },
    plotOptions: {
      series: {
        borderWidth: 0,
        dataLabels: {
          enabled: true,

        }
      }
    },

    tooltip: {
      headerFormat: '<span style="font-size:11px">{series.name}</span><br>',
      pointFormat: '<span style="color:{point.color}">{point.name}</span>: <b>{point.y:.2f}%</b> of total<br/>'
    },

    series: [
      {
        name: "Customers",
        colorByPoint: true,
        data: [

        ]
      }
    ],
    drilldown: {
      breadcrumbs: {
        position: {
          align: 'right'
        }
      },
      series: [






      ]
    }
  };

  constructor(private service :RegistrationService) { }

  ngOnInit(): void {
    this.service.getPenalityCustomer().subscribe(data=>{
      console.log(data);
      this.CustomerPen=data;
      console.log(this.CustomerPen)
      console.log(this.CustomerPen[0])
      this.service.getPenalityCustomerDate(this.CustomerPen[0].idc).subscribe(data1=>{
        console.log(data1)
        this.customerPen1=data1

        this.service.getPenalityCustomerDate(this.CustomerPen[1].idc).subscribe(data2=>{
          this.customerPen2=data2
          this.service.getPenalityCustomerDate(this.CustomerPen[2].idc).subscribe(data3=>{
            this.customerPen3=data3
            const self = this,
                chart = this.chart;
            setTimeout(() => {
            self.chartOption.drilldown
              self.chartOption.series[0].data=[ {
              name: this.CustomerPen[0].firstName + " " + this.CustomerPen[0].lastName,
              y: this.CustomerPen[0].penality,
              drilldown: this.CustomerPen[0].firstName
            },
              {
                name: this.CustomerPen[1].firstName + " " + this.CustomerPen[1].lastName,
                y: this.CustomerPen[1].penality,
                drilldown: this.CustomerPen[1].firstName
              },
              {
                name: this.CustomerPen[2].firstName + " " + this.CustomerPen[2].lastName,
                y: this.CustomerPen[2].penality,
                drilldown: this.CustomerPen[2].firstName
              },
            ]
            for (let i=0;i<this.customerPen1.length;i++){
              this.dataa1.push(this.customerPen1[i].date,this.customerPen1[i].penality);
            }
            for(let i1=0;i1<this.customerPen2.length;i1++){
              this.dataa2.push(this.customerPen2[i1].date,this.customerPen2[i1].penality);
            }
            for(let i2=0;i2<this.customerPen3.length;i2++){
              this.dataa3.push(this.customerPen3[i2].date,this.customerPen2[i2].penality)
            }

              self.chartOption.drilldown.series=[{
              name:this.CustomerPen[0].firstName,
                id:this.CustomerPen[0].firstName,
                data:this.dataa1
              },{
                name:this.CustomerPen[1].firstName,
                  id:this.CustomerPen[1].firstName,
                  data:this.dataa2
            },
                {
                  name:this.CustomerPen[2].firstName,
                  id:this.CustomerPen[2].firstName,
                  data:this.dataa3
                },
              ]



















              self.updateFlag = true;
            }, 0);


          })
        });
      });




    })
  }


}
