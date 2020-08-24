import {Injectable} from '@angular/core';

import {BehaviorSubject} from 'rxjs';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs/index';
import {filter, map} from 'rxjs/operators';
import {ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot} from '@angular/router';
import {ApiAction, JsonApiService} from '@web-core/app/services/json-api.service';
import {AuthDetail, Msg, UserDetails} from '@web-core-generate/models';
import {I18nService} from '@web-core/app/services/i18n/i18n.service';
// import * as CoreModels from '@web-core-generate/models';


const defaultUser = {
    admNm: 'Guest',
    useCd: 'USE002'
} as UserDetails;

@Injectable()
export class UserService implements CanActivate {

    public user$ = new BehaviorSubject<UserDetails>(Object.assign({}, defaultUser) as UserDetails);

    constructor(private i18n: I18nService, private http: HttpClient, private jsonApiService: JsonApiService, private httpClient: HttpClient, private router: Router) {
        console.log('UserService constructor');
        this.reloadUserDetails();
    }

    public next(userDetails: UserDetails) {
        this.user$.next(userDetails);
    }

    public setUserDetails(userDetails: UserDetails) {
        this.user$.next(userDetails);
    }

    get userDetails() {
        return this.user$.getValue();
    }

    public reloadUserDetails() {
        this.jsonApiService.get<UserDetails>({
            url: '/anon-web-core/userDetails',
            success: (it: UserDetails) => {
                it = Object.assign({}, it);
                if ('USE001' === it.useCd) {
                    this.user$.next(it);
                } else {
                    this.router.navigate(['/login']);
                }
            },
            error: (err: Error) => {
                this.router.navigate(['/']);
            }
        } as ApiAction<UserDetails>, {successPopup: false});
    }


    public getAuths(level: number, prntUrlSeq?: number, method = 'GET') {
        if (this.userDetails && this.userDetails.authDetails) {
            return this.userDetails.authDetails.filter(it => level === it.menuLvl && (prntUrlSeq ? it.prntUrlSeq === prntUrlSeq : true) && method === it.crudTypeCd && 'USE001' === it.useCd && 'USE002' === it.hddnCd);
        }
    }

    public getAuthMergeUrlDedups(level: number, prntUrlSeq?: number, method = 'GET'): AuthDetail[] {
        if (this.userDetails && this.userDetails.authDetails) {
            const authDetailMap = new Map<string, AuthDetail>();
            const userDetailAuths = this.getAuths(level, prntUrlSeq, method);
            userDetailAuths.forEach(it => {
                const key = it.urlSeq + '_' + it.crudTypeCd;
                let before = authDetailMap.get(key);
                if (!before) {
                    authDetailMap.set(key, it);
                    before = authDetailMap.get(key);
                } else {
                    if ('USE001' === it.useCd) {
                        before.useCd = it.useCd;
                    }
                    if ('USE001' === it.hddnCd) {
                        before.hddnCd = it.hddnCd;
                    }
                    if ('USE001' === it.regexpCd) {
                        before.regexpCd = it.regexpCd;
                    }
                }
            });

            const values: IterableIterator<AuthDetail> = authDetailMap.values();
            const of = Array.from(values);
            of.forEach(it => {
                it.authId = 'ROLE';
                it.authNm = 'ROLE';
            });
            return of;
        }
    }

    canActivate(next: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<boolean> {
        return this.user$.pipe(filter(it => 'USE001' === it.useCd)).pipe(map(userDetails => {
            let canActivate = false;
            if (userDetails && userDetails.authDetails) {
                const authDetails = userDetails.authDetails.filter(it => state.url === it.url && 'GET' === it.crudTypeCd);
                if (authDetails && authDetails.length > 0) {
                    canActivate = true;
                } else {
                    canActivate = false;
                }
            } else {
                canActivate = false;
            }
            return canActivate;
        }));
    }
}
