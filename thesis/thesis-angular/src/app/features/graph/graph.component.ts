import {Component, OnDestroy, OnInit, ViewChild} from '@angular/core';
import {AlertService} from "@web-core-app/services/ui/alert.service";
import {WebCoreDatepickerDirective} from '@web-core-app/directive/form/input/webCoreDatepicker.directive';
import {HomeListComponent} from "@app/features/home/modal/home-list.component";
import * as examples from "./flot-examples"
import {FakeDataSource} from "./flot-examples";
import {JsonApiService} from "@web-core-app/services/json-api.service";

@Component({
    selector: 'app-chart',
    templateUrl: './graph.component.html',
    styleUrls: ['./graph.component.css']
})
export class GraphComponent implements OnInit, OnDestroy {
    public flotData:any;
    public flotExamples:any;

    public updatingData: Array<any>;

    public chartjsData: any;

    constructor(private jsonApiService: JsonApiService) {
    }

    ngOnInit() {
        this.jsonApiService.fetch( '/web-core-assets/api/graphs/chartjs.json').subscribe((data)=>{
            this.chartjsData = data;
        });



        this.jsonApiService.fetch( '/web-core-assets/api/graphs/flot.json').subscribe(data => this.flotData = data);
        this.flotExamples = examples;

        this.interval = setInterval(()=>{
            this.updateStats()
        }, 1000);
        this.updateStats()
    }

    updateStats() {
        this.updatingData = [FakeDataSource.getRandomData()]
    }

    private interval;

    ngOnDestroy() {
        clearInterval(this.interval);
    }

}
