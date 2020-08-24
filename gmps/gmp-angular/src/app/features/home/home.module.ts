import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { homeRouting } from './home.routing';
import {HomeComponent} from './home.component';
import {HomeListComponent} from '@app/features/home/modal/home-list.component';
import {DatatableComponent} from '@web-core-app/features/datatable/datatable.component';
import {SharedModule} from '@app/shared/shared.module';
@NgModule({
  imports: [
    CommonModule,
    homeRouting,
    SharedModule
  ],
  declarations: [HomeComponent, HomeListComponent, DatatableComponent]
})
export class HomeModule { }
