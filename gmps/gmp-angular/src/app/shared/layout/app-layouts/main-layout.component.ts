import { Component, OnInit } from '@angular/core';
import {ActivatedRoute} from '@angular/router';
// import { routerTransition } from "@app/shared/utils/animations";

@Component({
  selector: 'app-main-layout',
  templateUrl: './main-layout.component.html',
  styles: [],
  // animations: [routerTransition]
})
export class MainLayoutComponent implements OnInit {
  constructor() {}

  ngOnInit() {}

  getState(outlet) {
    if (!outlet.activatedRoute) { return; }
    const ss = outlet.activatedRoute.snapshot;

    // return unique string that is used as state identifier in router animation
    return (
      outlet.activatedRouteData.state ||
      (ss.url.length
        ? ss.url[0].path
        : ss.parent.url.length
          ? ss.parent.url[0].path
          : null)
    );
  }
}
