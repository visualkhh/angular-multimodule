import {NgModule} from '@angular/core';

import {WebCoreDatepickerDirective} from '@web-core-app/directives/form/input/webCoreDatepicker.directive';
import {Camera, FileText, Github, Heart, Save, Settings, Plus, PlusCircle, PlusSquare} from 'angular-feather/icons';
// import {BootstrapModule} from "@app/shared/bootstrap.module";
import {I18nPipe} from '@web-core-app/pipes/i18n/i18n.pipe';
import {FeatherModule} from 'angular-feather';
import {ModalModule} from 'ngx-bootstrap/modal';
import {ButtonsModule} from 'ngx-bootstrap/buttons';
import {TooltipModule} from 'ngx-bootstrap/tooltip';
import {BsDropdownModule} from 'ngx-bootstrap/dropdown';
import {ProgressbarModule} from 'ngx-bootstrap/progressbar';
import {AlertModule} from 'ngx-bootstrap/alert';
import {TabsModule} from 'ngx-bootstrap/tabs';
import {AccordionModule} from 'ngx-bootstrap/accordion';
import {CarouselModule} from 'ngx-bootstrap/carousel';
import {CommonModule} from '@angular/common';
// @NgModule()
// class BB extends BootstrapModule {}

// component는 사용하는 모듈쪽에서만 가져다가 써라.
@NgModule({
    imports: [
        CommonModule,
        ModalModule.forRoot(),
        ButtonsModule.forRoot(),
        TooltipModule.forRoot(),
        BsDropdownModule.forRoot(),
        ProgressbarModule.forRoot(),
        AlertModule.forRoot(),
        TabsModule.forRoot(),
        AccordionModule.forRoot(),
        CarouselModule.forRoot(),
        FeatherModule.pick({
            Camera, FileText, Save, Settings, Heart, Github, Plus, PlusCircle, PlusSquare
        })
    ],
    declarations: [
        WebCoreDatepickerDirective,
        I18nPipe,
    ],
    exports: [
        WebCoreDatepickerDirective,
        // BootstrapModule,
        I18nPipe,
        FeatherModule,
    ]
})

export class SharedModule {
}
