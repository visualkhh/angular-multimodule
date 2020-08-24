import {Component, ElementRef, OnInit, ViewChild} from '@angular/core';
import {BsModalRef, BsModalService} from 'ngx-bootstrap/modal';
import {UserService} from '@web-core/app/services/user.service';
import {CookieService} from 'ngx-cookie-service';

@Component({
    selector: 'web-core-sign-out',
    styleUrls: ['sign-out.component.css'],
    templateUrl: './sign-out.component.html',
})
export class SignOutComponent implements OnInit {

    @ViewChild('modalTemplate') modalTemplate;
    @ViewChild('modalTemplate2') modalTemplate2;
    @ViewChild('from') form: ElementRef;

    bsModalRef: BsModalRef;
    config = {
        class: 'modal-dialog-centered',
        ignoreBackdropClick: true
    }

    constructor(private userService: UserService, private modalService: BsModalService, private cookieService: CookieService) {

    }

    ngOnInit() {
    }

    close() {
        this.bsModalRef.hide();
    }

    yes() {
        const form: HTMLFormElement = this.form.nativeElement;
        form.submit();
    }

    no() {
        this.close();
    }

    show() {
        this.bsModalRef = this.modalService.show(this.modalTemplate, this.config);
        // this.bsModalRef = this.modalService.show(this.modalTemplate2, {ignoreBackdropClick: true});
        // this.modalTemplate
    }
}
