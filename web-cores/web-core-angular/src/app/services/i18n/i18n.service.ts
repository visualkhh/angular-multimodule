import {Injectable, ApplicationRef} from '@angular/core';

// import {config} from '@app/core/smartadmin.config';
import {languages} from './languages.model';
import {ApiAction, JsonApiService} from '@web-core-app/services/json-api.service';
import {BehaviorSubject, Subject} from 'rxjs';
// import 'rxjs/add/operator/filter';
// import 'rxjs/add/operator/map';
// import 'rxjs/add/operator/merge';
// import 'rxjs/add/operator/zip';
import {Code} from '@web-core-generate/models';


declare let moment: any;

@Injectable()
export class I18nService {

    public data: {};
    public currentLanguage: any;
    // public state = new BehaviorSubject(this.selectedLocale);
    public state = new BehaviorSubject({});
    // private static instance: I18nService;
    private id = Date.now();

    constructor(private jsonApiService: JsonApiService, private ref: ApplicationRef) {
        this.initLanguage(window.localStorage.getItem('lastLocale') || 'kr');
        this.fetch(this.currentLanguage.param);
        // if (!I18nService.instance) {
        //     this.initLanguage(this.selectedLocale);
        //     this.fetch(this.currentLanguage.param);
        //     I18nService.instance = this;
        // }
    }

    // public static getInstance(jsonApiService?: JsonApiService, ref?: ApplicationRef) {
    //     if (!I18nService.instance) {
    //         I18nService.instance = new I18nService(jsonApiService, ref);
    //     }
    //     return I18nService.instance;
    // }

    private fetch(locale: any) {
        this.jsonApiService.get({
            url: `/anon-web-core/langs?lang=${locale}`,
            success: (data: any) => {
                this.data = data;
                this.state.next(data);
                this.ref.tick();
            }
        } as ApiAction<any>, {successPopup: false});
    }

    private initLanguage(locale: string) {
        const language = languages.find((it) => {
            return it.key === locale;
        });
        if (language) {
            this.currentLanguage = language;
        } else {
            throw new Error(`Incorrect locale used for I18nService: ${locale}`);

        }
    }

    setLanguage(language) {
        this.currentLanguage = language;
        window.localStorage.setItem('lastLocale', language.key);
        this.fetch(language.param);
    }


    subscribe(sub: any, err?: any) {
        return this.state.subscribe(sub, err);
    }

    public getTranslation(phrase: string, agument?: any[]): string {
        let rtn = this.data && this.data[phrase] ? this.data[phrase] : phrase;
        const max: number = agument ? agument.length : 0;
        for (let i = 0; i < max; i++) {
            const r = agument[i] || '';
            rtn = rtn.replace(new RegExp('\\{' + i + '\\}', 'gi'), r);
        }
        return rtn;
    }

    public getCodes(code: string, onlyChild = true) {
        const data = new Array<Code>();
        if (this.data) {
            Object.keys(this.data).filter(it => it.startsWith(code) && (onlyChild ? !it.endsWith('000') : true)).forEach(it => {
                const codeAt = {
                    cd: it,
                    cdNm: this.data[it]
                } as Code;
                data.push(codeAt);
            });
        }
        return data;
    }

    // public dateFormat(date: string | Date, format = "YYYY-MM-DD HH:mm:ss"): string {
    //     // console.log('--this.currentLanguage'+JSON.stringify(this.currentLanguage))
    //     return moment(date).tz(this.currentLanguage.key == 'us' ? 'America/Los_Angeles' : 'Asia/Seoul').format(format);
    // }

}
