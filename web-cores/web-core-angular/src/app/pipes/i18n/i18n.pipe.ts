import {ApplicationRef, Pipe, PipeTransform} from '@angular/core';
import {I18nService} from '../../services/i18n/i18n.service';
import {JsonApiService} from '@web-core/app/services/json-api.service';

@Pipe({
    name: 'i18n',
    pure: false
})
export class I18nPipe implements PipeTransform {

    constructor(private i18n: I18nService, private jsonApiService: JsonApiService, private ref: ApplicationRef) {
    }

    transform(phrase: any, ...agument: any[]): any {
        if (phrase) {
            // return I18nService.getInstance(this.jsonApiService, this.ref).getTranslation(phrase, agument);
            return this.i18n.getTranslation(phrase, agument);
        }
    }

}
