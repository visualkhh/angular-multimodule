/* tslint:disable */
/* eslint-disable */
// Generated using typescript-generator version 2.25.695 on 2020-08-18 15:07:27.

export interface CoreBrd extends BrdBase {
}

export interface CoreCode extends CodeBase, Serializable {
}

export interface AdmAuthBase extends ModelBase, Serializable {
    admAuthSeq: number;
    admSeq: number;
    authId: string;
}

export interface AdmBase extends ModelBase, Serializable {
    admSeq: number;
    admLginId: string;
    admLginPw: string;
    admNm: string;
    useCd: UseCd;
    homeUrl: string;
    email: string;
    salt: string;
    lginFailCnt: number;
    corpGrpSeq: number;
    lginWaitDt: Date;
    regDt: Date;
}

export interface AdmUrlBase extends ModelBase, Serializable {
    authUrlSeq: number;
    crudType: string;
    authId: string;
    urlSeq: number;
    regDt: Date;
}

export interface AuthBase extends ModelBase, Serializable {
    authId: string;
    authNm: string;
    xpln: string;
}

export interface AuthUrlBase extends ModelBase, Serializable {
    authUrlSeq: number;
    authId: string;
    urlSeq: number;
    crudTypeCd: string;
    regDt: Date;
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

export interface CodeBase extends ModelBase, Serializable {
    cd: string;
    cdNm: string;
    cdNmEn: string;
    cdXpln: string;
    cdOrd: string;
    mappingCd: string;
    prntCd: string;
}

export interface UrlBase extends ModelBase, Serializable {
    urlSeq: number;
    menuNm: string;
    menuNmEn: string;
    menuLvl: number;
    menuIcon: string;
    menuOrd: number;
    url: string;
    urlXpln: string;
    urlXplnEn: string;
    prntUrlSeq: string;
    useCd: UseCd;
    hddnCd: UseCd;
    regexp_cd: UseCd;
    regDt: Date;
}

export interface ModelBase {
}

export interface Error<T> extends Msg<T> {
    errors: Error<any>[];
}

export interface FieldError<T> extends Error<T> {
    field: string;
}

export interface Msg<T> {
    code: string;
    message: string;
    data: T;
}

export interface Brd extends BrdBase {
}

export interface WebCoreBrd extends BrdBase {
}

export interface AuthDetail extends Serializable {
    admSeq: number;
    authId: string;
    authUrlSeq: number;
    urlSeq: number;
    admLginId: string;
    authNm: string;
    crudTypeCd: string;
    menuNm: string;
    prntUrlSeq: number;
    menuLvl: number;
    menuIcon: string;
    menuOrd: number;
    url: string;
    urlXpln: string;
    useYn: UseCd;
    hddnYn: UseCd;
    regexpYn: UseCd;
}

export interface AuthId extends Serializable {
    admSeq: number;
    authId: string;
    authUrlSeq: number;
    urlSeq: number;
}

export interface UserDetails extends AdmBase, Serializable, UserDetailsOfSpring {
    auths: AuthDetail[];
    authorities: GrantedObjAuthority<AuthDetail[]>[];
}

export interface UserDetailGrantedObjAuthority<T> extends GrantedObjAuthority<T> {
}

export interface Serializable {
}

export interface GrantedObjAuthority<T> extends GrantedAuthority {
    auth: T;
}

export interface UserDetailsOfSpring extends Serializable {
    enabled: boolean;
    username: string;
    password: string;
    credentialsNonExpired: boolean;
    authorities: GrantedAuthority[];
    accountNonExpired: boolean;
    accountNonLocked: boolean;
}

export interface GrantedAuthority extends Serializable {
    authority: string;
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
