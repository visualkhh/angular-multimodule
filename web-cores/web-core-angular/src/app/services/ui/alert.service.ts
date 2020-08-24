import {Injectable} from '@angular/core';
// import {I18nService} from "@app/shared/i18n/i18n.service";
// import '../../libs/Customizable-Loading-Modal-Plugin/css/modal-loading.css';
// import '../../libs/Customizable-Loading-Modal-Plugin/css/modal-loading-animate.css';
/*
src/style.css import
@import url("../../../web-cores/web-core-angular/src/libs/Customizable-Loading-Modal-Plugin/css/modal-loading.css");
@import url("../../../web-cores/web-core-angular/src/libs/Customizable-Loading-Modal-Plugin/css/modal-loading-animate.css");
 */

// import $ from 'jquery';
// import 'jquery';
declare var $: any;
import '@web-core/libs/bootstrap-toaster/dist/js/bootstrap-toaster.js';
// require('../../libs/Customizable-Loading-Modal-Plugin/css/modal-loading.css');
// require('../../libs/Customizable-Loading-Modal-Plugin/css/modal-loading-animate.css');
// const Loading = require('external-lib/Customizable-Loading-Modal-Plugin/js/modal-loading.js');
@Injectable()
export class AlertService {
    // private progress: any;

    constructor() {
    }

    // smallBox(data, cb?) {
    //     $.smallBox(data, cb)
    // }
    //
    // bigBox(data, cb?) {
    //     $.bigBox(data, cb)
    // }
    //
    // smartMessageBox(data, cb?) {
    //     $.SmartMessageBox(data, cb)
    // }
    //
    // primaryAlert(title: string, content: string) {
    //     this.smallBox({
    //         title: title,
    //         content: `<i class='fa fa-clock-o'></i>${content}<i></i>`,
    //         color: "#3182c4",
    //         iconSmall: "fa fa-times fa-2x fadeInRight animated",
    //         timeout: 4000
    //     });
    // }
    // infoAlert(title: string, content: string) {
    //     this.smallBox({
    //         title: title,
    //         content: `<i class='fa fa-clock-o'></i>${content}<i></i>`,
    //         color: "#3b9285",
    //         iconSmall: "fa fa-times fa-2x fadeInRight animated",
    //         timeout: 4000
    //     });
    // }

    successAlert(title: string, content: string) {
       new $.toaster({
            content: content,
            title: title,
            delay: 3000,
            containerOption: {
                style: 'background-color:#55aa55ee',
            },
            // buttonOption: {
            //     style: 'color: yellow',
            // },
            headerOption: {
                style: 'background-color:#55aa55ee; color:#ffffff',
                //     class: 'wow'
            },
            bodyOption: {
                style: 'background-color:#55aa55ee; color:#ffffff',
                class: 'wow'
            }
        });
    }

    dangerAlert(title: string, content: string) {
        // debugger;
        console.log("------?danger",title, content)
        new $.toaster({
            content: content,
            title: title,
            delay: 3000,
            containerOption: {
                style: 'background-color:#dd5555ee',
            },
            // buttonOption: {
            //     style: 'color: yellow',
            // },
            headerOption: {
                style: 'background-color:#dd5555ee; color:#ffffff',
                //     class: 'wow'
            },
            bodyOption: {
                style: 'background-color:#dd5555ee; color:#ffffff',
                class: 'wow'
            }
        })
    }
    progressSpinnerOpen(){
        return  new $.Loading({
            direction: 'hor',
            discription: 'Loading...',
            // animationIn: true,
            // animationOut: true,
            defaultApply: 	true,
            maskBgColor:  "rgba(0,0,0,0.1)"
        });
        //pro.out();
        // return  new $.Loading();
    }
    // progressSpinnerClose(){
    //     this.progress.out();
    // }
}
