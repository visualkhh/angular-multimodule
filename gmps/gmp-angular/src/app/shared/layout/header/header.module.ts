import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';

import {HeaderComponent} from './header.component';
import {RouterModule} from '@angular/router';
import {SharedModule} from '@app/shared/shared.module';
import {LanguageSelectorComponent} from '@web-core/app/features/i18n/language-selector/language-selector.component';
import {SignOutComponent} from '@web-core/app/features/sign-out/sign-out.component';
@NgModule({
    imports: [
        CommonModule,
        SharedModule,
        RouterModule
        // SharedModule
    ],
    declarations: [HeaderComponent, LanguageSelectorComponent, SignOutComponent],
    exports: [
        HeaderComponent
    ]
})
export class HeaderModule {
}
