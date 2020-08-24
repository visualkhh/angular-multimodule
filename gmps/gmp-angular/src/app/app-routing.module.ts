import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';

import {CrisisListComponent} from '@app/features/crisis-list/crisis-list.component';
// import { MainLayoutComponent } from "@web-core-app/shared/layout/app-layouts/main-layout.component";
// import {EmptyLayoutComponent} from "@web-core-app/shared/layout/app-layouts/empty-layout.component";
import {WebCoreListComponent} from '@web-core-app/features/web-core-list/web-core-list.component';
import {MainLayoutComponent} from '@app/shared/layout/app-layouts/main-layout.component';
import {EmptyLayoutComponent} from '@web-core-app/layouts/empty/layout/empty-layout.component';
import {UserService} from '@web-core-app/services/user.service';
import {PageNotFoundComponent} from '@app/features/errors/page-not-found/page-not-found.component';
import {BbsComponent} from '@app/features/bbs/bbs.component';

const routes: Routes = [
    {
        path: '',
        component: EmptyLayoutComponent,
        loadChildren: () => import('./features/auth/auth.module').then(m => m.AuthModule),
    },
    // {
    //     path: 'home',
    //     component: EmptyLayoutComponent,
    //     loadChildren: () => import('./features/home/home.module').then(m => m.HomeModule),
    //     // canActivate: [UserService]
    // },


    {
        path: '',
        component: MainLayoutComponent,
        // component: EmptyLayoutComponent,
        data: {pageTitle: 'Home'},
        children: [
    //         {
    //             path: '',
    //             redirectTo: 'home',
    //             pathMatch: 'full'
    //         },
            {
                path: 'home',
                loadChildren: () => import('./features/home/home.module').then(m => m.HomeModule),
                canActivate: [UserService]
                // canActivate: [UserService]
                // loadChildren: () => import('@web-core-app/features/web-core-home/web-core-home.module').then(m => m.WebCoreHomeModule),
                // loadChildren: () => import('./features/crisis-list/crisis-list.component').then(m => m.HomeListComponent),
                // component: HomeListComponent

                // data: { pageTitle: "Home" }
            },
            {
                path: 'bbs',
                loadChildren: () => import('./features/bbs/bbs.module').then(m => m.BbsModule),
                canActivate: [UserService]
            },
    //         {
    //             path: 'graph',
    //             loadChildren: () => import('./features/graph/graph.module').then(m => m.GraphModule),
    //             // canActivate: [UserService]
    //         }
        ]
    },

    // {
    //   path: "auth",
    //   component: AuthLayoutComponent,
    //   loadChildren: "./features/auth/auth.module#AuthModule"
    // },
    {path: '**', component: PageNotFoundComponent}

];

@NgModule({
    imports: [RouterModule.forRoot(routes, {useHash: true, enableTracing: false})],
    exports: [RouterModule]
})
export class AppRoutingModule {
}
