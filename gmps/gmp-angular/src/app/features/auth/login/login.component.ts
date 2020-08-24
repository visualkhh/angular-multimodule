import {Component, ElementRef, OnInit, ViewChild} from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {HttpClient, HttpErrorResponse, HttpHeaders} from '@angular/common/http';
import {UserService} from '@web-core-app/services/user.service';
import {FormGroup, FormControl, Validators} from '@angular/forms';
import {CookieService} from 'ngx-cookie-service';
@Component({
    selector: 'app-login',
    // providers: [Location, {provide: LocationStrategy, useClass: PathLocationStrategy}],
    templateUrl: './login.component.html',
    styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
    form: any = Object();
    note: string;
    formdata: FormGroup;
    @ViewChild('from') someInput: ElementRef;
    private type: string;

    public cnt = 0;
    private forceProfile: string;

    constructor(private router: Router, private userService: UserService, private cookieService: CookieService,
                private http: HttpClient, private route: ActivatedRoute) {
        console.log('LoginComponent constructor');
    }

    ngOnInit() {
        console.log('LoginComponent ngOnInit');
        this.type = this.route.snapshot.queryParamMap.get('type');
        if ('sign-fail' === this.type) {
            this.note = 'msg.error.login.fail';
        }else if ('sign-out' === this.type) {
            this.note = 'msg.error.login.logout';
        }
        this.formdata = new FormGroup({
            username: new FormControl('', [Validators.required]),
            password: new FormControl('', [Validators.required]),
            _csrf: new FormControl(this.cookieService.get('XSRF-TOKEN'), [Validators.required])
        });

        this.userService.user$.subscribe((it) => {
            console.log('login init  ' + it.admNm);
            if ('USE001' === it.useCd) {
                this.router.navigate(['/home']);
            }
        });
    }

    submit(data) {
        const f: HTMLFormElement = this.someInput.nativeElement;
        f.submit();
    }


    // logoClick() {
    //     this.cnt++;
    //     if (this.cnt > 20) {
    //         this.forceProfile = '/assets/img/profile/' + Math.floor( (Math.random() * (24 - 1 + 1)) + 1 ) + '.png';
    //     }
    // }


}
