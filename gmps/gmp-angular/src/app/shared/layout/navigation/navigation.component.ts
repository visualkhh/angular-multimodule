import {Component, OnInit} from '@angular/core';
import {UserService} from '@web-core-app/services/user.service';

@Component({
    selector: 'component-navigation',
    templateUrl: './navigation.component.html',
    styleUrls: ['./navigation.component.css']
})
export class NavigationComponent implements OnInit {

  constructor(public userService: UserService) { }

  ngOnInit() {
  }

}
