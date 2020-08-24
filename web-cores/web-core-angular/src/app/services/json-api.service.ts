import {HttpClient, HttpErrorResponse, HttpHeaders, HttpParams} from '@angular/common/http';
import {ValidUtil} from '@web-core-app/shareds/valid/ValidUtil';
import {AlertService} from '@web-core-app/services/ui/alert.service';
import {Observable} from 'rxjs';
import {catchError, delay, map, filter} from 'rxjs/operators';
import {Router} from '@angular/router';
import {MsgCode} from '@web-core/app/shareds/code/MsgCode';
import {CookieService} from 'ngx-cookie-service';
import {I18nService} from '@web-core/app/services/i18n/i18n.service';
import { Injectable, Inject, Injector } from '@angular/core';
import {Operator} from 'rxjs/internal/Operator';

export type ActionAlertOption = {
    title?: string;
    progressPopup?: boolean;
    successPopup?: boolean;
    errorPopup?: boolean;
};
export type HttpOption = {
    headers?: HttpHeaders | {
        [header: string]: string | string[];
    };
    observe?: 'body';
    params?: HttpParams | {
        [param: string]: string | string[];
    };
    reportProgress?: boolean;
    responseType?: 'json';
    withCredentials?: boolean;
};

@Injectable()
export class JsonApiService {

    // private i18n: I18nService;

    constructor(private http: HttpClient, private injector: Injector, private router: Router, private cookieService: CookieService, private alertService: AlertService) {
        // this.i18n = injector.get(I18nService)
        // setTimeout(() => {
        //     this.i18n = this.injector.get(I18nService);
        //     console.log('-->', this.i18n)
        // }, 5000)
    }

    public fetch(url): Observable<any> {
        return this.http.get(this.getBaseUrl() + url)
            .pipe(
                delay(100),
                map((data: any) => (data.data || data)),
                catchError(this.handleError)
            );
    }

    private getBaseUrl() {
        return location.protocol + '//' + location.hostname + (location.port ? ':' + location.port : '') + '/';
    }


    private handleError(error: any) {
        // In a real world app, we might use a remote logging infrastructure
        // We'd also dig deeper into the error to get a better message
        const errMsg = (error.message) ? error.message :
            error.status ? `${error.status} - ${error.statusText}` : 'Server error';
        console.error(errMsg); // log to console instead
        return Observable.throw(errMsg);
    }



    // public getObservable<T>(action: ApiAction<T>): Observable<T> {
    //     const options: HttpOption = {};
    //     if (action.param) {
    //         options.params = Object.getOwnPropertyNames(action.param)
    //             .reduce((p, key) => {
    //                 if (!ValidUtil.isNullOrUndefined(action.param[key])) {
    //                     return p.set(key, action.param[key]);
    //                 } else {
    //                     return p;
    //                 }
    //             }, new HttpParams());
    //     }
    //     if (action.headers) {
    //         options.headers = action.headers;
    //     }
    //     const observable = this.http.get<T>(action.url, options);
    //     return observable;
    // }
    // https://www.techiediaries.com/angular-http-client/
    // https://www.techiediaries.com/angular-http-client/
    // public get<T>(url: string, success:(data: T|any) => void, param = {}) {
    public get<T>(action: ApiAction<T>, voptions: ActionAlertOption = {}) {
        // console.log("--------->", this.i18n);
        // this.http.get(url + "?" + ConvertUtil.objToGetURL(param))
        // const params = new HttpParams().set('_page', "1").set('_limit', "1");
        // const params = new HttpParams({fromString: '_page=1&_limit=1'});
        // const httpParams = new HttpParams().set('a', '4444').set('zzzz','ggg');
        // for (const key in param) {
        //     httpParams.set(key, param[key]+'');
        // }
        const options: HttpOption = {};
        if (action.param) {
            options.params = Object.getOwnPropertyNames(action.param)
                .reduce((p, key) => {
                    if (!ValidUtil.isNullOrUndefined(action.param[key])) {
                        return p.set(key, action.param[key]);
                    } else {
                        return p;
                    }
                }, new HttpParams());
        }
        if (action.headers) {
            options.headers = action.headers;
        }

        let pro;
        if (voptions.progressPopup === undefined || voptions.progressPopup) {
            pro = this.alertService.progressSpinnerOpen();
        }
        const observable = this.http.get<T>(action.url, options);
        observable.subscribe(
            (data: any) => {
                if (voptions.progressPopup === undefined || voptions.progressPopup) {
                    pro.out();
                }

                if (voptions.successPopup === undefined || voptions.successPopup) {
                    this.httpClientHandleSuccess(voptions.title || 'INFO', data || {code: 'R00000'});
                }
                if (action.success) {
                    action.success(data);
                }
                if (data.href) {
                    location.href = data.href;
                }
                if (data.location) {
                    this.router.navigate([data.location]);
                }
            },
            (err: HttpErrorResponse) => {
                if (voptions.progressPopup === undefined || voptions.progressPopup) {
                    pro.out();
                }
                if (voptions.errorPopup === undefined || voptions.errorPopup) {
                    this.httpClientHandleError(voptions.title || 'ERROR', err.error || {code: 'E99999'});
                }
                if (action.error) {
                    action.error(err);
                }
                if (action.afterComplete) {
                    action.afterComplete();
                } else {
                    if (err.error.href) {
                        location.href = err.error.href;
                    }
                    if (err.error.location) {
                        this.router.navigate([err.error.location]);
                    }
                }
            },
            () => {
                // pro.out();
                if (action.complete) {
                    action.complete();
                }
            }
        );

    }

    public delete<T>(action: ApiAction<T>, voptions: ActionAlertOption = {}): Observable<T> {
        const options: HttpOption = {};
        action.param = action.param || {};
        action.param._csrf = this.getCSRF();
        if (action.param) {
            options.params = Object.getOwnPropertyNames(action.param)
                .reduce((p, key) => {
                    if (!ValidUtil.isNullOrUndefined(action.param[key])) {
                        return p.set(key, action.param[key]);
                    } else {
                        return p;
                    }
                }, new HttpParams());
        }
        if (action.headers) {
            options.headers = action.headers;
        }
        let pro;
        if (voptions.progressPopup === undefined || voptions.progressPopup) {
            pro = this.alertService.progressSpinnerOpen();
        }
        const observable = this.http.delete<T>(action.url, options);
        observable.subscribe(
            (data: any) => {
                if (voptions.progressPopup === undefined || voptions.progressPopup) {
                    pro.out();
                }
                if (voptions.successPopup === undefined || voptions.successPopup) {
                    this.httpClientHandleSuccess(voptions.title || 'INFO', data || {code: 'R00000'});
                }
                if (action.success) {
                    action.success(data);
                }
                if (data.href) {
                    location.href = data.href;
                }
                if (data.location) {
                    this.router.navigate([data.location]);
                }
            },
            (err: HttpErrorResponse) => {
                if (voptions.progressPopup === undefined || voptions.progressPopup) {
                    pro.out();
                }
                if (voptions.errorPopup === undefined || voptions.errorPopup) {
                    this.httpClientHandleError(voptions.title || 'ERROR', err.error || {code: 'E99999'});
                }
                if (action.error) {
                    action.error(err);
                }
                if (action.afterComplete) {
                    action.afterComplete();
                } else {
                    if (err.error.href) {
                        location.href = err.error.href;
                    }
                    if (err.error.location) {
                        this.router.navigate([err.error.location]);
                    }
                }
            },
            () => {
                // pro.out();
                if (action.complete) {
                    action.complete();
                }
            }
        );
        return observable;
    }

    public post<T>(action: ApiAction<T>, voptions: ActionAlertOption = {}): Observable<T> {
        const options: HttpOption = {};
        action.param = action.param || {};
        if (action.param instanceof FormData) {
            action.url += '?_csrf=' + this.getCSRF();
        } else {
            action.param._csrf = this.getCSRF();
        }
        if (action.headers) {
            options.headers = action.headers;
        }
        let pro;
        if (voptions.progressPopup === undefined || voptions.progressPopup) {
            pro = this.alertService.progressSpinnerOpen();
        }
        const observable = this.http.post<T>(action.url, action.param, options);
        observable.subscribe(
            (data: any) => {
                if (voptions.progressPopup === undefined || voptions.progressPopup) {
                    pro.out();
                }
                if (voptions.successPopup === undefined || voptions.successPopup) {
                    this.httpClientHandleSuccess(voptions.title || 'INFO', data || {code: 'R00000'});
                }
                if (action.success) {
                    action.success(data);
                }
                if (data.href) {
                    location.href = data.href;
                }
                if (data.location) {
                    this.router.navigate([data.location]);
                }
            },
            (err: HttpErrorResponse) => {
                if (voptions.progressPopup === undefined || voptions.progressPopup) {
                    pro.out();
                }
                if (voptions.errorPopup === undefined || voptions.errorPopup) {
                    this.httpClientHandleError(voptions.title || 'ERROR', err.error || {code: 'E99999'});
                }
                if (action.error) {
                    action.error(err);
                }
                if (action.afterComplete) {
                    action.afterComplete();
                } else {
                    if (err.error.href) {
                        location.href = err.error.href;
                    }
                    if (err.error.location) {
                        this.router.navigate([err.error.location]);
                    }
                }
            },
            () => {
                // pro.out();
                if (action.complete) {
                    action.complete();
                }
            }
        );
        return observable;
    }

    public put<T>(action: ApiAction<T>, voptions: ActionAlertOption = {}): Observable<T> {
        const options: HttpOption = {};
        action.param = action.param || {};
        if (action.param instanceof FormData) {
            action.url += '?_csrf=' + this.getCSRF();
        } else {
            action.param._csrf = this.getCSRF();
        }
        if (action.headers) {
            options.headers = action.headers;
        }
        let pro;
        if (voptions.progressPopup === undefined || voptions.progressPopup) {
            pro = this.alertService.progressSpinnerOpen();
        }
        const observable = this.http.put<T>(action.url, action.param, options);
        observable.subscribe(
            (data: any) => {
                if (voptions.progressPopup === undefined || voptions.progressPopup) {
                    pro.out();
                }
                if (voptions.successPopup === undefined || voptions.successPopup) {
                    this.httpClientHandleSuccess(voptions.title || 'INFO', data || {code: 'R00000'});
                }
                if (action.success) {
                    action.success(data);
                }
                if (data.href) {
                    location.href = data.href;
                }
                if (data.location) {
                    this.router.navigate([data.location]);
                }
            },
            (err: HttpErrorResponse) => {
                if (voptions.progressPopup === undefined || voptions.progressPopup) {
                    pro.out();
                }
                if (voptions.errorPopup === undefined || voptions.errorPopup) {
                    this.httpClientHandleError(voptions.title || 'ERROR', err.error || {code: 'E99999'});
                }
                if (action.error) {
                    action.error(err);
                }
                if (action.afterComplete) {
                    action.afterComplete();
                } else {
                    if (err.error.href) {
                        location.href = err.error.href;
                    }
                    if (err.error.location) {
                        this.router.navigate([err.error.location]);
                    }
                }
            },
            () => {
                // pro.out();
                if (action.complete) {
                    action.complete();
                }
            }
        );
        return observable;
    }

    public httpClientHandleSuccess(title: string, data: any = {}) {
        const code = data.code || 'R00000';
        const message = (data.message || MsgCode.R00000);
        this.alertService.successAlert(title + '(' + code + ')', message);

    }

    public httpClientHandleError(title: string, error: any = {}) {
        const code = error.code || 'E99999';
        const message = (error.message || MsgCode.E99999);
        this.alertService.dangerAlert(title + '(' + code + ')', message);
    }

    public getCSRF(): string {
        // return document.querySelector('meta[name="_csrf"]').getAttribute('content');
        return this.cookieService.get('XSRF-TOKEN');
    }

}

export class ApiAction<T> {
    public url: string;
    public param: any;
    // public afterOperator: Operator<T, T>;
    // public beforeOperator: Operator<T, T>;
    public headers: HttpHeaders;
    public success: (data: T | any) => void;
    public error: (err: any) => void;
    public complete: () => void;
    public beforeSend: (data: T | any) => void;
    public afterComplete: () => void;

    constructor(url: string, inOptions: {
        param?: any;
        success?: (data: T | any) => void;
        error?: (err: any) => void;
        complete?: () => void;
    } = {}) {
        this.url = url;
        this.param = inOptions.param;
        this.success = inOptions.success;
        this.error = inOptions.error;
        this.complete = inOptions.complete;
    }
}


