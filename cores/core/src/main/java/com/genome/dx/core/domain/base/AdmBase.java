package com.genome.dx.core.domain.base;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.genome.dx.core.code.UseCd;
import com.genome.dx.core.model.ModelBase;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.time.ZonedDateTime;

@Getter
@Setter
@MappedSuperclass
@EqualsAndHashCode(callSuper = false)
public class AdmBase extends ModelBase implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ADM_SEQ")
    private Long admSeq;

    @Column(name = "ADM_LGIN_ID")
    private String admLginId;

//    @JsonIgnore
    @Column(name = "ADM_LGIN_PW")
    private String admLginPw;

    @Column(name = "ADM_NM")
    private String admNm;

    @Column(name = "USE_CD")
    @Enumerated(EnumType.STRING)
    private UseCd useCd;

    @Column(name = "HOME_URL")
    private String homeUrl;

    @Column(name = "EMAIL")
    private String email;

    @Column(name = "LGIN_FAIL_CNT")
    private Long lginFailCnt;

    @Column(name = "CORP_GRP_SEQ")
    private Long corpGrpSeq;

    @Column(name = "LGIN_WAIT_DT")
    private ZonedDateTime lginWaitDt;

    @Column(name = "REG_DT")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private ZonedDateTime regDt;

    @PrePersist
    protected void onCreate() {
        if (regDt == null) {
            regDt = ZonedDateTime.now();
        }
    }

}
