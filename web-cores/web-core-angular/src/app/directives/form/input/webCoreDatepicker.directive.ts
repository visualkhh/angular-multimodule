import {Directive, ElementRef, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {NgModel} from "@angular/forms";
// import {Directive, ElementRef, EventEmitter, Input, OnInit, Output} from '@angular/core';
// import {NgModel} from "@angular/forms";

declare var $: any;
// import('bootstrap-timepicker/js/bootstrap-timepicker.min.js');
// import * from 'bootstrap-timepicker/js/bootstrap-timepicker.min.js';

// import 'script-loader!bootstrap-duallistbox/dist/jquery.bootstrap-duallistbox.min.js'

@Directive({
    selector: '[webCoreDatepicker]',
    providers: [NgModel]
})
export class WebCoreDatepickerDirective implements OnInit {

    @Input() webCoreDatepicker: any;
    @Output() dateChange = new EventEmitter();
    private datepicker: any;
    private element: any;
    private id: string;

    // private el: ElementRef <--이거 안받아진다 왜그러는지 모르겠지만-_- id값으로 넘겨줘서 처리한다. 띠붕.
    constructor(private el: ElementRef, private ngModel: NgModel) {
        this.id = el.nativeElement.getAttribute('id');
        // console.log('UiDatepickerDirective', el)
        // constructor(private el:ElementRef, private ref: ApplicationRef, private ngModel?: NgModel) {
    }

    ngOnInit() {
        // console.log('UiDatepickerDirective ngOnInit --> ', this.ngModel, this.dateChange);
        let onSelectCallbacks = [];
        let saUiDatepicker = this.webCoreDatepicker || {};
        this.element = $(this.el.nativeElement);
        // this.element = $("#"+this.webCoreDatepicker.id);
        // this.element.change = () => {
        //   console.log('aaaaaa')
        // }
        // this.element.keydown =(function(){
        //   alert("The text has been changed.");
        // });
        if (saUiDatepicker.minRestrict) {
            onSelectCallbacks.push((selectedDate) => {
                $(saUiDatepicker.minRestrict).datepicker('option', 'minDate', selectedDate);
            });
        }
        if (saUiDatepicker.maxRestrict) {
            onSelectCallbacks.push((selectedDate) => {
                $(saUiDatepicker.maxRestrict).datepicker('option', 'maxDate', selectedDate);
            });
        }

        //Let others know about changes to the data field
        onSelectCallbacks.push((selectedDate) => {
            this.element.triggerHandler("change");

            let form = this.element.closest('form');

            if (typeof form.bootstrapValidator == 'function') {
                try {
                    form.bootstrapValidator('revalidateField', this.element);
                } catch (e) {
                    console.log(e.message)
                }
            }
        });

        let options = $.extend(saUiDatepicker, {
            prevText: '<i class="fa fa-chevron-left"></i>',
            nextText: '<i class="fa fa-chevron-right"></i>',
            onSelect: (selectedDate, inst) => {
                onSelectCallbacks.forEach((callback) => {
                    var date = this.element.datepicker("getDate");
                    // console.log(date);
                    // this.ngModel.update.emit(this.element.datepicker("getDate"));
                    this.ngModel.update.emit(selectedDate);
                    this.dateChange.emit(date);
                    callback.call(callback, selectedDate);
                })
            }
        });


        this.datepicker = this.element.datepicker(options);
        if (options.defaultDate) {
            this.element.datepicker("setDate", options.defaultDate);
        }

        // this.datepicker.on("changeDate", () => {
        //   console.log("Got change event from field");
        // });
        // this.element.datepicker("option", "defaultDate", +7);
    }

    public setDate(date: Date, changeEvent = true) {
        this.element.datepicker("setDate", date);
        if (changeEvent) {
            this.ngModel.update.emit(date);
            this.dateChange.emit(this.element.val());
        }
    }

}
