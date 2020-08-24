// import {Component, EventEmitter, OnInit, Output, ViewChild} from '@angular/core';
// import {Router} from "@angular/router";
// import {BsModalRef, BsModalService} from "ngx-bootstrap/modal";
// import {Component, EventEmitter, OnInit, Output, ViewChild} from '../../../../../../../thesis/thesis-angular/node_modules/@angular/core';
import {Component, EventEmitter, OnInit, Output, ViewChild} from '@angular/core';
// import {Component, EventEmitter, OnInit, Output, ViewChild} from '@target_angular_project/@angular/core';
// import {Router} from "@angular/router";
import {BsModalRef, BsModalService} from 'ngx-bootstrap/modal';
// import {BsModalRef, BsModalService} from "@target/ngx-bootstrap/modal";
// import {BsModalRef, BsModalService} from "../../../../../../../thesis/thesis-angular/node_modules/ngx-bootstrap/modal";
// import {BsModalRef, BsModalService} from "@tar /ngx-bootstrap/modal";

@Component({
  selector: 'web-core-wow-detail',
  templateUrl: './wow-detail.component.html',
  styleUrls: ['./wow-detail.component.css']
})
export class WowDetailComponent implements OnInit{
  @ViewChild('modalTemplate') modalTemplate;
  bsModalRef: BsModalRef;
  @Output() complete = new EventEmitter();
  constructor(private modalService: BsModalService) {
  }

  ngOnInit(): void {
  }

  public show(detail: any) {
    this.bsModalRef = this.modalService.show(this.modalTemplate, {ignoreBackdropClick: true});
  }

  private closeAndComplet() {
    this.complete.emit();
    this.bsModalRef.hide();
  }

}
