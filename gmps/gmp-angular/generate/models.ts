/* tslint:disable */
/* eslint-disable */
// Generated using typescript-generator version 2.25.695 on 2020-08-24 16:28:15.

export interface Brd extends BrdBase {
}

export interface BrdBase extends ModelBase, Serializable {
    brdSeq: number;
    cateCd: BrdCateCd;
    titl: string;
    cont: string;
    useCd: UseCd;
    regDt: Date;
    admSeq: number;
}

export interface ModelBase {
}

export interface Serializable {
}

export interface HttpClient {

    request<R>(requestConfig: { method: string; url: string; queryParams?: any; data?: any; copyFn?: (data: R) => R; }): RestResponse<R>;
}

export type RestResponse<R> = Promise<R>;

export type BrdCateCd = "BCC001" | "BCC002" | "BCC003" | "BCC004";

export type UseCd = "USE001" | "USE002";

function uriEncoding(template: TemplateStringsArray, ...substitutions: any[]): string {
    let result = "";
    for (let i = 0; i < substitutions.length; i++) {
        result += template[i];
        result += encodeURIComponent(substitutions[i]);
    }
    result += template[template.length - 1];
    return result;
}
