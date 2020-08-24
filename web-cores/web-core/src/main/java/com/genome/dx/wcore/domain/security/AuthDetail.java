package com.genome.dx.wcore.domain.security;

import com.genome.dx.core.code.UseCd;
import com.genome.dx.core.model.ModelBase;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.annotations.Subselect;

import javax.persistence.*;
import java.io.Serializable;

@Slf4j
@IdClass(AuthDetail.AuthId.class)
@Subselect("" +
        " SELECT" +
        "    X.ADM_SEQ, X.ADM_LGIN_ID, X.AUTH_ID, X.AUTH_NM, X.AUTH_URL_SEQ, X.CRUD_TYPE_CD, X.URL_SEQ," +
        "    U.MENU_NM, U.I18N_CD, U.MENU_LVL, U.MENU_ICON, U.MENU_ORD, U.URL,U. URL_XPLN, U.USE_CD, U.HDDN_CD, U.REGEXP_CD, U.PRNT_URL_SEQ" +
        " from   (" +
        "           select  A.ADM_SEQ, A.ADM_LGIN_ID, D.AUTH_ID, D.AUTH_NM, E.AUTH_URL_SEQ, E.CRUD_TYPE_CD, E.URL_SEQ" +
        "           from   (select  ADM_SEQ, ADM_LGIN_ID, CORP_GRP_SEQ" +
        "                   from    T_ADM" +
//		"               #                  where   ADM_LGIN_ID =   'omnifit' "+
        "           ) A" +
        "              , T_CORP_GRP                         B" +
        "              , T_CORP_GRP_AUTH                    C" +
        "              , T_AUTH                             D" +
        "              , T_AUTH_URL                         E" +
        "           where   1 = 1" +
        "             and     A.CORP_GRP_SEQ  =   B.CORP_GRP_SEQ" +
        "             and     A.CORP_GRP_SEQ  =   C.CORP_GRP_SEQ" +
        "             and     C.AUTH_ID       =   D.AUTH_ID" +
        "             and     C.AUTH_ID       =   E.AUTH_ID" +
        "           union" +
        "           select  A.ADM_SEQ, A.ADM_LGIN_ID, C.AUTH_ID, C.AUTH_NM, D.AUTH_URL_SEQ, D.CRUD_TYPE_CD, D.URL_SEQ" +
        "           from   (select  ADM_SEQ, ADM_LGIN_ID, CORP_GRP_SEQ" +
        "                   from    T_ADM" +
//		"               #                  where   ADM_LGIN_ID =   'omnifit' "+
        "           ) A" +
        "              , T_ADM_AUTH                         B" +
        "              , T_AUTH                             C" +
        "              , T_AUTH_URL                         D" +
        "           where   1 = 1" +
        "             and     A.ADM_SEQ       =   B.ADM_SEQ" +
        "             and     B.AUTH_ID       =   C.AUTH_ID" +
        "             and     C.AUTH_ID       =   D.AUTH_ID" +
        "       ) X LEFT JOIN T_URL U ON X.URL_SEQ = U.URL_SEQ WHERE U.USE_CD='USE001'  ORDER BY U.MENU_LVL, U.PRNT_URL_SEQ, U.MENU_ORD" +
        "")
@Getter
@Setter
@NoArgsConstructor
@ToString
@EqualsAndHashCode(callSuper = false)
@Entity
public class AuthDetail extends ModelBase implements Serializable {

    @Getter
    @Setter
    @EqualsAndHashCode(callSuper = false)
    @NoArgsConstructor
    @AllArgsConstructor
    public static class AuthId implements Serializable {
        private Long admSeq;
        private String authId;
        private Long authUrlSeq;
        private Long urlSeq;
    }


    @Id
    @Column(name = "ADM_SEQ")
    Long admSeq;

    @Id
    @Column(name = "AUTH_ID")
    String authId;

    @Id
    @Column(name = "AUTH_URL_SEQ")
    Long authUrlSeq;

    @Id
    @Column(name = "URL_SEQ")
    Long urlSeq;

    @Column(name = "ADM_LGIN_ID")
    String admLginId;

    @Column(name = "AUTH_NM")
    String authNm;

    @Column(name = "CRUD_TYPE_CD")
    String crudTypeCd;

    @Column(name = "MENU_NM")
    String menuNm;

    @Column(name = "I18N_CD")
    String i18nCd;

    @Column(name = "PRNT_URL_SEQ")
    Long prntUrlSeq;

    @Column(name = "MENU_LVL")
    Long menuLvl;

    @Column(name = "MENU_ICON")
    String menuIcon;

    @Column(name = "MENU_ORD")
    Long menuOrd;

    @Column(name = "URL")
    String url;

    @Column(name = "URL_XPLN")
    String urlXpln;

    @Enumerated(EnumType.STRING)
    @Column(name = "USE_CD")
    UseCd useCd;

    @Enumerated(EnumType.STRING)
    @Column(name = "HDDN_CD")
    UseCd hddnCd;

    @Enumerated(EnumType.STRING)
    @Column(name = "REGEXP_CD")
    UseCd regexpCd;

}
